package com.easypeasy.EPS_BACKEND.SHOP.repository;

import com.easypeasy.EPS_BACKEND.SHOP.entity.Order;
import com.easypeasy.EPS_BACKEND.SHOP.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByShopId(Long shopId);

    List<Order> findByStudentId(Long studentId);

    List<Order> findByShopIdAndStatus(Long shopId, OrderStatus status);
}