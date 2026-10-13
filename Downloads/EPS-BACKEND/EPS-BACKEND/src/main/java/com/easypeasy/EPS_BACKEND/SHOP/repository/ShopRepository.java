package com.easypeasy.EPS_BACKEND.SHOP.repository;

import com.easypeasy.EPS_BACKEND.SHOP.entity.Shop;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository  extends JpaRepository<Shop,Long>

{




    List<Shop> findByOwnerId(Long ownerId);


}
