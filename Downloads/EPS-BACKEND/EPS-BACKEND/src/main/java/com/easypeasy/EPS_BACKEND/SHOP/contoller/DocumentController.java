package com.easypeasy.EPS_BACKEND.SHOP.contoller;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.DocumentResponseDto;
import com.easypeasy.EPS_BACKEND.SHOP.entity.Document;
import com.easypeasy.EPS_BACKEND.SHOP.service.DocumentService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping(
            value = "/upload/{orderId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public DocumentResponseDto uploadDocument(
            @PathVariable Long orderId,
            @RequestParam("file") MultipartFile file)
            throws IOException {

        return documentService.uploadDocument(file, orderId);
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<Resource> getDocument(
            @PathVariable Long documentId) throws Exception {

        Document document =
                documentService.getDocument(documentId);

        Path filePath =
                Paths.get(
                        document.getFileUrl()
                                .replace("/uploads/", "uploads/")
                );

        Resource resource =
                new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            throw new RuntimeException("File not found");
        }

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" +
                                document.getFileName() +
                                "\""
                )
                .header(
                        HttpHeaders.CONTENT_TYPE,
                        "application/pdf"
                )
                .body(resource);
    }
}