package com.easypeasy.EPS_BACKEND.payment.controller;

import com.easypeasy.EPS_BACKEND.payment.paymentdto.PaymentRequestDto;
import com.easypeasy.EPS_BACKEND.payment.paymentdto.PaymentResponseDto;
import com.easypeasy.EPS_BACKEND.payment.paymentservice.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponseDto createPayment(
            @Valid @RequestBody PaymentRequestDto dto) {

        return paymentService.createPayment(dto);
    }

    @PatchMapping("/{paymentId}/success")
    public PaymentResponseDto markPaymentSuccess(
            @PathVariable Long paymentId) {

        return paymentService.markPaymentSuccess(paymentId);
    }

    @PatchMapping("/{paymentId}/failed")
    public PaymentResponseDto markPaymentFailed(
            @PathVariable Long paymentId) {

        return paymentService.markPaymentFailed(paymentId);
    }
}