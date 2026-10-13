package com.easypeasy.EPS_BACKEND.SHOP.entity;


import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String StudentName;

    private String ShopName;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Integer TotalAmount;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToOne(mappedBy = "order")
    private Document document;








}
