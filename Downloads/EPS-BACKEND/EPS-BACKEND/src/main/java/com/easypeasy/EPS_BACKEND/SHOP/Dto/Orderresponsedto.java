package com.easypeasy.EPS_BACKEND.SHOP.Dto;

import com.easypeasy.EPS_BACKEND.SHOP.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Orderresponsedto {

    private Long id;
    private String shopName;
    private OrderStatus status;
    private Integer totalAmount;
}
