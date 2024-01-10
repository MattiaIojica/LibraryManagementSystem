package com.example.projectJava.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;
    private String name;
    private String email;
    private String address;

    @Column(name = "member_since")
    private LocalDate memberSince;

    public User() {
    }

    public User(Long id, String userId, String name, String email, String address, LocalDate memberSince) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.memberSince = memberSince;
    }

    public User(String userId, String name, String email, String address, LocalDate memberSince) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.memberSince = memberSince;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }
}

