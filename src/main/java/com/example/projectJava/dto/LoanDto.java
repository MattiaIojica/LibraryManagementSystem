package com.example.projectJava.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class LoanDto {

    private Long id;
    private Long bookId;
    private Long userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate dueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate returnDate;

    private String status;

    public LoanDto() {
    }

    public LoanDto(Long id, Long bookId, Long userId, LocalDate dueDate, LocalDate returnDate, String status) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
