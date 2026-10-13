package com.easypeasy.EPS_BACKEND.payment.paymentdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {

    private Long orderId;

    private String paymentMethod;
}