package com.easypeasy.EPS_BACKEND.SHOP.Dto;

import com.easypeasy.EPS_BACKEND.SHOP.entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrintOptionRequestDto {

    private ColorMode colorMode;

    private PrintSide printSide;

    private PagesSelection pageSelection;

    private String customPageRange;

    private PaperSize paperSize;

    private PaperType paperType;

    private Orientation orientation;

    private PagesPerSheet pagesPerSheet;

    private Integer copies;

    private BindingType bindingType;

    private FinishingType finishingType;
}