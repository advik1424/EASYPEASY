package com.easypeasy.EPS_BACKEND.SHOP.mapper;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.Orderrequestdto;
import com.easypeasy.EPS_BACKEND.SHOP.Dto.Orderresponsedto;
import com.easypeasy.EPS_BACKEND.SHOP.entity.Order;
import com.easypeasy.EPS_BACKEND.SHOP.entity.OrderStatus;
import com.easypeasy.EPS_BACKEND.SHOP.entity.Shop;
import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(
            Orderrequestdto dto,
            User student,
            Shop shop) {

        Order order = new Order();

        order.setStudent(student);
        order.setShop(shop);
        order.setStatus(OrderStatus.PAYMENT_PENDING);

        return order;
    }

    public Orderresponsedto toResponse(Order order) {

        Orderresponsedto response = new Orderresponsedto();

        response.setId(order.getId());
        response.setShopName(order.getShop().getShopName());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotalAmount());

        return response;
    }
}