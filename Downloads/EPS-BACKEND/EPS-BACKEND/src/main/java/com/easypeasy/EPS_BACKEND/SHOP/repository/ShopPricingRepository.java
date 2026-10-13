package com.easypeasy.EPS_BACKEND.SHOP.repository;

import com.easypeasy.EPS_BACKEND.SHOP.entity.ShopPricing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopPricingRepository
        extends JpaRepository<ShopPricing, Long> {

    Optional<ShopPricing> findByShopId(Long shopId);
}