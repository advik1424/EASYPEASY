package com.easypeasy.EPS_BACKEND.payment.paymentdto;

import com.easypeasy.EPS_BACKEND.payment.entity.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {

    private Long id;

    private Long orderId;

    private Long amount;

    private PaymentStatus status;

    private String paymentMethod;

    private String transactionId;

    private String razorpayOrderId;

    private String razorpayPaymentId;
    private String razorpayKeyId;
}