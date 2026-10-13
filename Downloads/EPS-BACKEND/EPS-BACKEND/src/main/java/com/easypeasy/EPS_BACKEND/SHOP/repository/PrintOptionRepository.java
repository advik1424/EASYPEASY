package com.easypeasy.EPS_BACKEND.SHOP.repository;

import com.easypeasy.EPS_BACKEND.SHOP.entity.PrintOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrintOptionRepository
        extends JpaRepository<PrintOption, Long> {

    Optional<PrintOption> findByOrderId(Long orderId);
}
