package com.example.projectJava.dto;

import java.math.BigDecimal;

public class FineDto {

    private Long id;
    private Long userId;
    private BigDecimal amount;
    private String reason;
    private String paymentStatus;

    // Constructors, getters, and setters

    // Constructors
    public FineDto() {
    }

    public FineDto(Long id, Long userId, BigDecimal amount, String reason, String paymentStatus) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.reason = reason;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
