package com.easypeasy.EPS_BACKEND.payment.mapper;

import com.easypeasy.EPS_BACKEND.payment.paymentdto.PaymentResponseDto;
import com.easypeasy.EPS_BACKEND.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponseDto toResponse(Payment payment) {

        PaymentResponseDto response = new PaymentResponseDto();

        response.setId(payment.getId());
        response.setOrderId(payment.getOrder().getId());
        response.setAmount(payment.getAmount());
        response.setStatus(payment.getStatus());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setTransactionId(payment.getTransactionId());
        response.setRazorpayOrderId(payment.getRazorpayOrderId());
        response.setRazorpayPaymentId(payment.getRazorpayPaymentId());

        return response;
    }
}