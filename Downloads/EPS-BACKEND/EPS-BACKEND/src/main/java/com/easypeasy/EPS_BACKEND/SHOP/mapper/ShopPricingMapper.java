package com.easypeasy.EPS_BACKEND.SHOP.mapper;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.ShopPricingRequestDto;
import com.easypeasy.EPS_BACKEND.SHOP.Dto.ShopPricingResponseDto;
import com.easypeasy.EPS_BACKEND.SHOP.entity.ShopPricing;
import org.springframework.stereotype.Component;

@Component
public class ShopPricingMapper {

    public ShopPricing toEntity(ShopPricingRequestDto dto) {

        ShopPricing pricing = new ShopPricing();

        pricing.setBwSingleSidePrice(dto.getBwSingleSidePrice());
        pricing.setBwDoubleSidePrice(dto.getBwDoubleSidePrice());

        pricing.setColorSingleSidePrice(dto.getColorSingleSidePrice());
        pricing.setColorDoubleSidePrice(dto.getColorDoubleSidePrice());

        pricing.setA3ExtraPrice(dto.getA3ExtraPrice());
        pricing.setA5ExtraPrice(dto.getA5ExtraPrice());
        pricing.setLetterExtraPrice(dto.getLetterExtraPrice());
        pricing.setLegalExtraPrice(dto.getLegalExtraPrice());

        pricing.setPremiumExtraPrice(dto.getPremiumExtraPrice());
        pricing.setGlossyExtraPrice(dto.getGlossyExtraPrice());
        pricing.setPhotoExtraPrice(dto.getPhotoExtraPrice());

        pricing.setStaplePrice(dto.getStaplePrice());
        pricing.setSpiralPrice(dto.getSpiralPrice());
        pricing.setCombPrice(dto.getCombPrice());
        pricing.setLaminationPrice(dto.getLaminationPrice());

        return pricing;
    }

    public ShopPricingResponseDto toResponse(ShopPricing pricing) {

        ShopPricingResponseDto response = new ShopPricingResponseDto();

        response.setId(pricing.getId());

        response.setBwSingleSidePrice(
                pricing.getBwSingleSidePrice());

        response.setBwDoubleSidePrice(
                pricing.getBwDoubleSidePrice());

        response.setColorSingleSidePrice(
                pricing.getColorSingleSidePrice());

        response.setColorDoubleSidePrice(
                pricing.getColorDoubleSidePrice());

        response.setA3ExtraPrice(
                pricing.getA3ExtraPrice());

        response.setA5ExtraPrice(
                pricing.getA5ExtraPrice());

        response.setLetterExtraPrice(
                pricing.getLetterExtraPrice());

        response.setLegalExtraPrice(
                pricing.getLegalExtraPrice());

        response.setPremiumExtraPrice(
                pricing.getPremiumExtraPrice());

        response.setGlossyExtraPrice(
                pricing.getGlossyExtraPrice());

        response.setPhotoExtraPrice(
                pricing.getPhotoExtraPrice());

        response.setStaplePrice(
                pricing.getStaplePrice());

        response.setSpiralPrice(
                pricing.getSpiralPrice());

        response.setCombPrice(
                pricing.getCombPrice());

        response.setLaminationPrice(
                pricing.getLaminationPrice());

        return response;
    }
}