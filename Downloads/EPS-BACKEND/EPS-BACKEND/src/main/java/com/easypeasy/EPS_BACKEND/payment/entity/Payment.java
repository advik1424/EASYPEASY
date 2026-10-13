package com.easypeasy.EPS_BACKEND.payment.entity;

import com.easypeasy.EPS_BACKEND.SHOP.entity.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(
            name = "order_id",
            nullable = false,
            unique = true
    )
    private Order order;

    // Amount in paise
    private Long amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String paymentMethod;

    @Column(unique = true)
    private String transactionId;

    // Razorpay Order ID
    @Column(unique = true)
    private String razorpayOrderId;

    // Razorpay Payment ID
    @Column(unique = true)
    private String razorpayPaymentId;
}