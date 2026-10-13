package com.easypeasy.EPS_BACKEND.SHOP.contoller;

import com.easypeasy.EPS_BACKEND.SHOP.Dto.PrintOptionRequestDto;
import com.easypeasy.EPS_BACKEND.SHOP.Dto.PrintOptionResponseDto;
import com.easypeasy.EPS_BACKEND.SHOP.service.PrintOptionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class PrintOptionController {

    private final PrintOptionService printOptionService;

    public PrintOptionController(PrintOptionService printOptionService) {
        this.printOptionService = printOptionService;
    }

    @PostMapping("/{orderId}/print-options")
    public PrintOptionResponseDto createPrintOption(
            @PathVariable Long orderId,
            @Valid @RequestBody PrintOptionRequestDto dto) {

        return printOptionService.createPrintOption(orderId, dto);
    }
}