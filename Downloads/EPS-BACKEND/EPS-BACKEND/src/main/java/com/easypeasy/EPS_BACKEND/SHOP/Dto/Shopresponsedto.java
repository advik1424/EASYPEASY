package com.easypeasy.EPS_BACKEND.SHOP.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Shopresponsedto {


    private Long id;




    private String shopName;


    private String address;
}
