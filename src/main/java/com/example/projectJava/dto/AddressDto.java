package com.example.projectJava.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

public class AddressDto {
    @Schema(accessMode = READ_ONLY)
    private Long id;
    @NotNull
    @NotBlank
    @Schema(name = "street", example = "Calea Victoriei")
    private String street;
    @NotNull
    @NotBlank
    @Schema(name = "streetNo", example = "4")
    @Min(value = 1)
    private Integer streetNo;
    @NotNull
    @NotBlank
    @Schema(name = "building", example = "A1")
    private String building;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(Integer streetNo) {
        this.streetNo = streetNo;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
