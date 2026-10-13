package com.easypeasy.EPS_BACKEND.SHOP.mapper;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.DocumentResponseDto;
import com.easypeasy.EPS_BACKEND.SHOP.entity.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public DocumentResponseDto toResponse(Document document) {

        DocumentResponseDto response = new DocumentResponseDto();

        response.setId(document.getId());
        response.setFileName(document.getFileName());
        response.setFileUrl(document.getFileUrl());

        return response;
    }
}