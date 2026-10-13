package com.easypeasy.EPS_BACKEND.SHOP.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileUrl;

    private Integer pageCount;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public static class PrintOption {
    }
}