package com.easypeasy.EPS_BACKEND.SHOP.entity;

import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String shopName;

    @NotBlank
    private String address;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}