package com.easypeasy.EPS_BACKEND.SHOP.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "print_options")
public class PrintOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ColorMode colorMode;

    @Enumerated(EnumType.STRING)
    private PrintSide printSide;

    @Enumerated(EnumType.STRING)
    private PagesSelection pageSelection;

    private String customPageRange;

    @Enumerated(EnumType.STRING)
    private PaperSize paperSize;

    @Enumerated(EnumType.STRING)
    private PaperType paperType;

    @Enumerated(EnumType.STRING)
    private Orientation orientation;

    @Enumerated(EnumType.STRING)
    private PagesPerSheet pagesPerSheet;

    private Integer copies;

    @Enumerated(EnumType.STRING)
    private BindingType bindingType;

    @Enumerated(EnumType.STRING)
    private FinishingType finishingType;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}