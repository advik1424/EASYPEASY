package com.easypeasy.EPS_BACKEND.payment.paymentservice;

import com.easypeasy.EPS_BACKEND.SHOP.entity.Order;
import com.easypeasy.EPS_BACKEND.SHOP.entity.OrderStatus;
import com.easypeasy.EPS_BACKEND.SHOP.repository.OrderRepository;
import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import com.easypeasy.EPS_BACKEND.payment.paymentdto.PaymentRequestDto;
import com.easypeasy.EPS_BACKEND.payment.paymentdto.PaymentResponseDto;
import com.easypeasy.EPS_BACKEND.payment.entity.Payment;
import com.easypeasy.EPS_BACKEND.payment.entity.PaymentStatus;
import com.easypeasy.EPS_BACKEND.payment.mapper.PaymentMapper;
import com.easypeasy.EPS_BACKEND.payment.paymentrepository.PaymentRepository;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class PaymentService {


    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentMapper paymentMapper;
    private final RazorpayClient razorpayClient;


    public PaymentService(
            PaymentRepository paymentRepository,
            OrderRepository orderRepository,
            PaymentMapper paymentMapper,
            RazorpayClient razorpayClient) {

        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.paymentMapper = paymentMapper;
        this.razorpayClient = razorpayClient;
    }





    public PaymentResponseDto createPayment(PaymentRequestDto dto) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User student = (User) authentication.getPrincipal();

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found with id: " + dto.getOrderId()
                        ));

        if (!order.getStudent().getId().equals(student.getId())) {
            throw new RuntimeException(
                    "You are not allowed to pay for this order"
            );
        }

        if (order.getTotalAmount() == null) {
            throw new RuntimeException(
                    "Order price is not calculated yet"
            );
        }

        if (paymentRepository.findByOrderId(order.getId()).isPresent()) {
            throw new RuntimeException(
                    "Payment already exists for this order"
            );
        }

        long amountInPaise =
                order.getTotalAmount().longValue() * 100;

        try {

            JSONObject razorpayOrderRequest = new JSONObject();

            razorpayOrderRequest.put(
                    "amount",
                    amountInPaise
            );

            razorpayOrderRequest.put(
                    "currency",
                    "INR"
            );

            razorpayOrderRequest.put(
                    "receipt",
                    "order_" + order.getId()
            );

            com.razorpay.Order razorpayOrder =
                    razorpayClient.orders.create(
                            razorpayOrderRequest
                    );

            Payment payment = new Payment();

            payment.setOrder(order);
            payment.setAmount(amountInPaise);
            payment.setStatus(PaymentStatus.PENDING);
            payment.setPaymentMethod(dto.getPaymentMethod());

            payment.setRazorpayOrderId(
                    razorpayOrder.get("id")
            );

            Payment savedPayment =
                    paymentRepository.save(payment);

            PaymentResponseDto response =
                    paymentMapper.toResponse(savedPayment);

            response.setRazorpayKeyId(razorpayKeyId);

            return response;

        } catch (RazorpayException e) {

            throw new RuntimeException(
                    "Unable to create Razorpay order",
                    e
            );
        }
    }

    public PaymentResponseDto markPaymentSuccess(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found with id: " + paymentId));

        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            throw new RuntimeException("Payment is already successful");
        }

        payment.setStatus(PaymentStatus.SUCCESS);

        payment.setTransactionId(
                "TXN_" + System.currentTimeMillis()
        );

        Payment savedPayment = paymentRepository.save(payment);

        // Payment successful → Order becomes PLACED
        Order order = payment.getOrder();
        order.setStatus(OrderStatus
                .Placed);

        orderRepository.save(order);

        return paymentMapper.toResponse(savedPayment);
    }

    public PaymentResponseDto markPaymentFailed(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Payment not found with id: " + paymentId
                        )
                );

        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            throw new RuntimeException(
                    "Successful payment cannot be marked as failed"
            );
        }

        payment.setStatus(PaymentStatus.FAILED);

        Payment savedPayment =
                paymentRepository.save(payment);

        return paymentMapper.toResponse(savedPayment);
    }


}