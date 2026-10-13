package com.easypeasy.EPS_BACKEND.SHOP.contoller;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.ShopPricingRequestDto;
import com.easypeasy.EPS_BACKEND.SHOP.Dto.ShopPricingResponseDto;
import com.easypeasy.EPS_BACKEND.SHOP.service.ShopPricingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopPricingController {

    private final ShopPricingService shopPricingService;

    public ShopPricingController(
            ShopPricingService shopPricingService) {

        this.shopPricingService = shopPricingService;
    }

    @PostMapping("/{shopId}/pricing")
    public ShopPricingResponseDto createPricing(
            @PathVariable Long shopId,
            @Valid @RequestBody ShopPricingRequestDto dto) {

        return shopPricingService.createPricing(shopId, dto);
    }

    @GetMapping("/{shopId}/pricing")
    public ShopPricingResponseDto getPricing(
            @PathVariable Long shopId) {

        return shopPricingService.getPricing(shopId);
    }

    @PutMapping("/{shopId}/pricing")
    public ShopPricingResponseDto updatePricing(
            @PathVariable Long shopId,
            @Valid @RequestBody ShopPricingRequestDto dto) {

        return shopPricingService.updatePricing(shopId, dto);
    }
}