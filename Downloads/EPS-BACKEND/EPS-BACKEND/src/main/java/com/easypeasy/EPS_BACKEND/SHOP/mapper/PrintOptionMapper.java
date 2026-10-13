package com.easypeasy.EPS_BACKEND.SHOP.mapper;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.PrintOptionRequestDto;
import com.easypeasy.EPS_BACKEND.SHOP.Dto.PrintOptionResponseDto;
import com.easypeasy.EPS_BACKEND.SHOP.entity.PrintOption;
import org.springframework.stereotype.Component;

@Component
public class PrintOptionMapper {

    public PrintOption toEntity(PrintOptionRequestDto dto) {

        PrintOption option = new PrintOption();

        option.setColorMode(dto.getColorMode());
        option.setPrintSide(dto.getPrintSide());
        option.setPageSelection(dto.getPageSelection());
        option.setCustomPageRange(dto.getCustomPageRange());
        option.setPaperSize(dto.getPaperSize());
        option.setPaperType(dto.getPaperType());
        option.setOrientation(dto.getOrientation());
        option.setPagesPerSheet(dto.getPagesPerSheet());
        option.setCopies(dto.getCopies());
        option.setBindingType(dto.getBindingType());
        option.setFinishingType(dto.getFinishingType());

        return option;
    }

    public PrintOptionResponseDto toResponse(PrintOption option) {

        PrintOptionResponseDto response =
                new PrintOptionResponseDto();

        response.setId(option.getId());
        response.setColorMode(option.getColorMode());
        response.setPrintSide(option.getPrintSide());
        response.setPageSelection(option.getPageSelection());
        response.setCustomPageRange(option.getCustomPageRange());
        response.setPaperSize(option.getPaperSize());
        response.setPaperType(option.getPaperType());
        response.setOrientation(option.getOrientation());
        response.setPagesPerSheet(option.getPagesPerSheet());
        response.setCopies(option.getCopies());
        response.setBindingType(option.getBindingType());
        response.setFinishingType(option.getFinishingType());

        return response;
    }
}