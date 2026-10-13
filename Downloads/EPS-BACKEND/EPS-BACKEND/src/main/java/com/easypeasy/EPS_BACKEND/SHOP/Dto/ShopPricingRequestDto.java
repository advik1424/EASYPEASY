package com.easypeasy.EPS_BACKEND.SHOP.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopPricingRequestDto {

    private Integer bwSingleSidePrice;
    private Integer bwDoubleSidePrice;

    private Integer colorSingleSidePrice;
    private Integer colorDoubleSidePrice;

    private Integer a3ExtraPrice;
    private Integer a5ExtraPrice;
    private Integer letterExtraPrice;
    private Integer legalExtraPrice;

    private Integer premiumExtraPrice;
    private Integer glossyExtraPrice;
    private Integer photoExtraPrice;

    private Integer staplePrice;
    private Integer spiralPrice;
    private Integer combPrice;
    private Integer laminationPrice;
}