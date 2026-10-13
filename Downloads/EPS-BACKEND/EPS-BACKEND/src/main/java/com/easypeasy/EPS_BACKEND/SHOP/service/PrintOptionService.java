package com.easypeasy.EPS_BACKEND.SHOP.service;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.PrintOptionRequestDto;
import com.easypeasy.EPS_BACKEND.SHOP.Dto.PrintOptionResponseDto;
import com.easypeasy.EPS_BACKEND.SHOP.entity.Order;
import com.easypeasy.EPS_BACKEND.SHOP.entity.PrintOption;
import com.easypeasy.EPS_BACKEND.SHOP.mapper.PrintOptionMapper;
import com.easypeasy.EPS_BACKEND.SHOP.repository.OrderRepository;
import com.easypeasy.EPS_BACKEND.SHOP.repository.PrintOptionRepository;
import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PrintOptionService {

    private final PrintOptionRepository printOptionRepository;
    private final PrintOptionMapper printOptionMapper;
    private final OrderRepository orderRepository;

    public PrintOptionService(
            PrintOptionRepository printOptionRepository,
            PrintOptionMapper printOptionMapper,
            OrderRepository orderRepository) {

        this.printOptionRepository = printOptionRepository;
        this.printOptionMapper = printOptionMapper;
        this.orderRepository = orderRepository;
    }

    public PrintOptionResponseDto createPrintOption(
            Long orderId,
            PrintOptionRequestDto dto) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User student = (User) authentication.getPrincipal();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found with id: " + orderId
                        )
                );

        if (!order.getStudent().getId().equals(student.getId())) {
            throw new RuntimeException(
                    "You are not allowed to set print options for this order"
            );
        }

        PrintOption option =
                printOptionMapper.toEntity(dto);

        option.setOrder(order);

        PrintOption savedOption =
                printOptionRepository.save(option);

        return printOptionMapper.toResponse(savedOption);
    }
}