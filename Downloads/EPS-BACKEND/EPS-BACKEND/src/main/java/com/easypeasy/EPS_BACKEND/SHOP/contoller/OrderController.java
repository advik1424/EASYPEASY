package com.easypeasy.EPS_BACKEND.SHOP.contoller;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.Orderrequestdto;
import com.easypeasy.EPS_BACKEND.SHOP.Dto.Orderresponsedto;
import com.easypeasy.EPS_BACKEND.SHOP.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Student creates order
    @PostMapping
    public Orderresponsedto createOrder(
            @Valid @RequestBody Orderrequestdto dto) {

        return orderService.createOrder(dto);
    }

    // Calculate price automatically from PDF + print options
    @PostMapping("/{orderId}/calculate-price")
    public Orderresponsedto calculatePrice(
            @PathVariable Long orderId) {

        return orderService.calculateAndUpdatePrice(
                orderId
        );
    }

    // Shopkeeper gets orders
    @GetMapping("/shop")
    public List<Orderresponsedto> getOrdersForShop() {

        return orderService.getOrdersForShop();
    }

    // Shopkeeper confirms order
    @PatchMapping("/{orderId}/confirm")
    public Orderresponsedto confirmOrder(
            @PathVariable Long orderId) {

        return orderService.confirmOrder(
                orderId
        );
    }

    // Student gets own orders
    @GetMapping("/my-orders")
    public List<Orderresponsedto> getMyOrders() {

        return orderService.getMyOrders();
    }
}