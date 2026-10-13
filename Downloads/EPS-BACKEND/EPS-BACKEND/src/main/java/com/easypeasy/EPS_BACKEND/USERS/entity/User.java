package com.easypeasy.EPS_BACKEND.USERS.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


@Entity
@Table(name="UserTable")
public class User {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true, nullable = false)
    private String email;



    @Column(nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;













}
