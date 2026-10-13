package com.easypeasy.EPS_BACKEND.SHOP.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shop_pricing")
public class ShopPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "shop_id", nullable = false, unique = true)
    private Shop shop;

    // Per physical page/sheet
    private Integer bwSingleSidePrice;
    private Integer bwDoubleSidePrice;

    private Integer colorSingleSidePrice;
    private Integer colorDoubleSidePrice;

    // Paper size extra charges
    private Integer a3ExtraPrice;
    private Integer a5ExtraPrice;
    private Integer letterExtraPrice;
    private Integer legalExtraPrice;

    // Paper type extra charges
    private Integer premiumExtraPrice;
    private Integer glossyExtraPrice;
    private Integer photoExtraPrice;

    // Other services
    private Integer staplePrice;
    private Integer spiralPrice;
    private Integer combPrice;
    private Integer laminationPrice;
}