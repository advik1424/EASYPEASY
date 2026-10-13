package com.easypeasy.EPS_BACKEND.SHOP.service;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.DocumentResponseDto;
import com.easypeasy.EPS_BACKEND.SHOP.entity.Document;
import com.easypeasy.EPS_BACKEND.SHOP.entity.Order;
import com.easypeasy.EPS_BACKEND.SHOP.mapper.DocumentMapper;
import com.easypeasy.EPS_BACKEND.SHOP.repository.DocumentRepository;
import com.easypeasy.EPS_BACKEND.SHOP.repository.OrderRepository;
import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final OrderRepository orderRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public DocumentService(
            DocumentRepository documentRepository,
            DocumentMapper documentMapper,
            OrderRepository orderRepository) {

        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.orderRepository = orderRepository;
    }

    public DocumentResponseDto uploadDocument(
            MultipartFile file,
            Long orderId) throws IOException {

        // ==========================================
        // 1. Validate file
        // ==========================================

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null ||
                !fileName.toLowerCase().endsWith(".pdf")) {

            throw new RuntimeException(
                    "Only PDF files are allowed"
            );
        }

        // ==========================================
        // 2. Find Order
        // ==========================================

        Order order =
                orderRepository.findById(orderId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order not found with id: "
                                                + orderId
                                )
                        );

        // ==========================================
        // 3. Get logged-in student
        // ==========================================

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User student =
                (User) authentication.getPrincipal();

        // ==========================================
        // 4. Check order ownership
        // ==========================================

        if (!order.getStudent()
                .getId()
                .equals(student.getId())) {

            throw new RuntimeException(
                    "You are not allowed to upload document "
                            + "for this order"
            );
        }

        // ==========================================
        // 5. Create upload directory
        // ==========================================

        Path uploadPath =
                Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // ==========================================
        // 6. Create file path
        // ==========================================

        Path filePath =
                uploadPath.resolve(fileName);

        // ==========================================
        // 7. Save PDF
        // ==========================================

        Files.copy(
                file.getInputStream(),
                filePath
        );

        // ==========================================
        // 8. Count PDF pages automatically
        // ==========================================

        int pageCount;

        try (PDDocument pdfDocument =
                     Loader.loadPDF(filePath.toFile())) {

            pageCount =
                    pdfDocument.getNumberOfPages();
        }

        // ==========================================
        // 9. Create Document entity
        // ==========================================

        Document document =
                new Document();

        document.setFileName(fileName);

        document.setFileUrl(
                "/uploads/" + fileName
        );

        document.setPageCount(pageCount);

        document.setOrder(order);

        // ==========================================
        // 10. Save Document
        // ==========================================

        Document savedDocument =
                documentRepository.save(document);

        // ==========================================
        // 11. Entity → Response DTO
        // ==========================================

        return documentMapper.toResponse(
                savedDocument
        );
    }


    // ==========================================
    // Get Document
    // ==========================================

    public Document getDocument(Long documentId) {

        Document document =
                documentRepository.findById(documentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Document not found with id: "
                                                + documentId
                                )
                        );

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User currentUser =
                (User) authentication.getPrincipal();

        Order order =
                document.getOrder();

        boolean isStudent =
                order.getStudent()
                        .getId()
                        .equals(currentUser.getId());

        boolean isShopkeeper =
                order.getShop()
                        .getOwner()
                        .getId()
                        .equals(currentUser.getId());

        if (!isStudent && !isShopkeeper) {

            throw new RuntimeException(
                    "You are not allowed to access this document"
            );
        }

        return document;
    }
}