package com.easypeasy.EPS_BACKEND.SHOP.service;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.Orderrequestdto;
import com.easypeasy.EPS_BACKEND.SHOP.Dto.Orderresponsedto;
import com.easypeasy.EPS_BACKEND.SHOP.entity.Order;
import com.easypeasy.EPS_BACKEND.SHOP.entity.OrderStatus;
import com.easypeasy.EPS_BACKEND.SHOP.entity.Shop;
import com.easypeasy.EPS_BACKEND.SHOP.mapper.OrderMapper;
import com.easypeasy.EPS_BACKEND.SHOP.repository.OrderRepository;
import com.easypeasy.EPS_BACKEND.SHOP.repository.PrintOptionRepository;
import com.easypeasy.EPS_BACKEND.SHOP.repository.ShopRepository;
import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import com.easypeasy.EPS_BACKEND.payment.entity.Payment;
import com.easypeasy.EPS_BACKEND.payment.entity.PaymentStatus;
import com.easypeasy.EPS_BACKEND.payment.paymentrepository.PaymentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ShopRepository shopRepository;
    private final PricingService pricingService;
    private final PrintOptionRepository printOptionRepository;
    private final PaymentRepository paymentRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderMapper orderMapper,
            ShopRepository shopRepository,
            PricingService pricingService,
            PrintOptionRepository printOptionRepository,
            PaymentRepository paymentRepository) {

        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.shopRepository = shopRepository;
        this.pricingService = pricingService;
        this.printOptionRepository = printOptionRepository;
        this.paymentRepository = paymentRepository;
    }

    // ==========================================
    // Student creates an order
    // ==========================================

    public Orderresponsedto createOrder(
            Orderrequestdto dto) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User student =
                (User) authentication.getPrincipal();

        Shop shop =
                shopRepository.findById(dto.getShopId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Shop not found with id: "
                                                + dto.getShopId()
                                )
                        );

        Order order =
                orderMapper.toEntity(
                        dto,
                        student,
                        shop
                );

        Order savedOrder =
                orderRepository.save(order);

        return orderMapper.toResponse(savedOrder);
    }

    // ==========================================
    // Calculate and update order price
    // ==========================================

    public Orderresponsedto calculateAndUpdatePrice(
            Long orderId) {

        Order order =
                orderRepository.findById(orderId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order not found with id: "
                                                + orderId
                                )
                        );

        var printOption =
                printOptionRepository
                        .findByOrderId(orderId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Print options not found for order: "
                                                + orderId
                                )
                        );

        Integer totalAmount =
                pricingService.calculatePrintCost(
                        printOption
                );

        order.setTotalAmount(totalAmount);

        Order updatedOrder =
                orderRepository.save(order);

        return orderMapper.toResponse(updatedOrder);
    }

    // ==========================================
    // Shopkeeper confirms order
    // ==========================================

    public Orderresponsedto confirmOrder(
            Long orderId) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User shopkeeper =
                (User) authentication.getPrincipal();

        Order order =
                orderRepository.findById(orderId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order not found with id: "
                                                + orderId
                                )
                        );

        Shop shop =
                order.getShop();

        // Check shop ownership
        if (!shop.getOwner()
                .getId()
                .equals(shopkeeper.getId())) {

            throw new RuntimeException(
                    "You are not allowed to confirm this order"
            );
        }

        // Check payment
        Payment payment =
                paymentRepository.findByOrderId(orderId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment not found for this order"
                                )
                        );

        // Payment must be successful
        if (payment.getStatus() != PaymentStatus.SUCCESS) {

            throw new RuntimeException(
                    "Order cannot be confirmed because payment is not successful"
            );
        }

        // Order must be PLACED
        if (order.getStatus() != OrderStatus.Placed) {

            throw new RuntimeException(
                    "Only placed orders can be confirmed"
            );
        }

        // Move order to PREPARING
        order.setStatus(
                OrderStatus.PREPARING
        );

        Order updatedOrder =
                orderRepository.save(order);

        return orderMapper.toResponse(
                updatedOrder
        );
    }

    // ==========================================
    // Student gets own orders
    // ==========================================

    public List<Orderresponsedto> getMyOrders() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User student =
                (User) authentication.getPrincipal();

        List<Order> orders =
                orderRepository.findByStudentId(
                        student.getId()
                );

        return orders.stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    // ==========================================
    // Shopkeeper gets placed orders
    // ==========================================

    public List<Orderresponsedto> getOrdersForShop() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User shopkeeper =
                (User) authentication.getPrincipal();

        List<Shop> shops =
                shopRepository.findByOwnerId(
                        shopkeeper.getId()
                );

        if (shops.isEmpty()) {
            throw new RuntimeException(
                    "Shop not found"
            );
        }

        Shop shop =
                shops.get(0);

        // Only successfully paid / PLACED orders
        List<Order> orders =
                orderRepository.findByShopIdAndStatus(
                        shop.getId(),
                        OrderStatus.Placed
                );

        return orders.stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}