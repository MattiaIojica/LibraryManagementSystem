package com.example.projectJava.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

public class FineDto {

    @Schema(accessMode = READ_ONLY)
    private Long id;
    @NotNull
    @Min(value = 1)
    private Long userId;
    @NotNull
    @Min(value = 1)
    private BigDecimal amount;
    @NotNull
    @NotBlank
    private String reason;
    @NotNull
    @NotBlank
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
