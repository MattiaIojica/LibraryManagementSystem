package com.example.projectJava.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class UserDto {

    private Long id;

    @NotNull(message = "First name is mandatory.")
    @NotBlank(message = "First Name must have a value.")
    private String firstName;

    @NotNull(message = "Last name is mandatory.")
    @NotBlank(message = "Last Name must have a value.")
    private String lastName;

    @NotNull(message = "Email is mandatory.")
    @Email(message = "Email should be valid.")
    private String email;

    private LocalDate memberSince;

    private AddressDto address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
