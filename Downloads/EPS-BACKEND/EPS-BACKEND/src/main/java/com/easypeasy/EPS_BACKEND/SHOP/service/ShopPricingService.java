package com.easypeasy.EPS_BACKEND.SHOP.service;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.ShopPricingRequestDto;
import com.easypeasy.EPS_BACKEND.SHOP.Dto.ShopPricingResponseDto;
import com.easypeasy.EPS_BACKEND.SHOP.entity.Shop;
import com.easypeasy.EPS_BACKEND.SHOP.entity.ShopPricing;
import com.easypeasy.EPS_BACKEND.SHOP.mapper.ShopPricingMapper;
import com.easypeasy.EPS_BACKEND.SHOP.repository.ShopPricingRepository;
import com.easypeasy.EPS_BACKEND.SHOP.repository.ShopRepository;
import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ShopPricingService {

    private final ShopPricingRepository shopPricingRepository;
    private final ShopPricingMapper shopPricingMapper;
    private final ShopRepository shopRepository;

    public ShopPricingService(
            ShopPricingRepository shopPricingRepository,
            ShopPricingMapper shopPricingMapper,
            ShopRepository shopRepository) {

        this.shopPricingRepository = shopPricingRepository;
        this.shopPricingMapper = shopPricingMapper;
        this.shopRepository = shopRepository;
    }
    public ShopPricingResponseDto createPricing(
            Long shopId,
            ShopPricingRequestDto dto) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User shopkeeper =
                (User) authentication.getPrincipal();

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Shop not found with id: " + shopId
                        )
                );

        if (!shop.getOwner().getId().equals(shopkeeper.getId())) {
            throw new RuntimeException(
                    "You are not allowed to set pricing for this shop"
            );
        }

        if (shopPricingRepository.findByShopId(shopId).isPresent()) {
            throw new RuntimeException(
                    "Pricing already exists for this shop"
            );
        }

        ShopPricing pricing =
                shopPricingMapper.toEntity(dto);

        pricing.setShop(shop);

        ShopPricing savedPricing =
                shopPricingRepository.save(pricing);

        return shopPricingMapper.toResponse(savedPricing);
    }

    public ShopPricingResponseDto getPricing(Long shopId) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User shopkeeper =
                (User) authentication.getPrincipal();

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Shop not found with id: " + shopId
                        )
                );

        if (!shop.getOwner().getId().equals(shopkeeper.getId())) {
            throw new RuntimeException(
                    "You are not allowed to view pricing of this shop"
            );
        }

        ShopPricing pricing =
                shopPricingRepository.findByShopId(shopId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Pricing not found for this shop"
                                )
                        );

        return shopPricingMapper.toResponse(pricing);
    }
    public ShopPricingResponseDto updatePricing(
            Long shopId,
            ShopPricingRequestDto dto) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User shopkeeper =
                (User) authentication.getPrincipal();

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Shop not found with id: " + shopId
                        )
                );

        if (!shop.getOwner().getId().equals(shopkeeper.getId())) {
            throw new RuntimeException(
                    "You are not allowed to update pricing for this shop"
            );
        }

        ShopPricing pricing =
                shopPricingRepository.findByShopId(shopId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Pricing not found for this shop"
                                )
                        );

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

        ShopPricing updatedPricing =
                shopPricingRepository.save(pricing);

        return shopPricingMapper.toResponse(updatedPricing);
    }
}