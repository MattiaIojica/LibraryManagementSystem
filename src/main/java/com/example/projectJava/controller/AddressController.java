package com.example.projectJava.controller;

import com.example.projectJava.dto.AddressDto;
import com.example.projectJava.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable Long id) {
        AddressDto addressDto = addressService.getAddressById(id);
        if (addressDto != null) {
            return new ResponseEntity<>(addressDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        AddressDto createdAddress = addressService.createAddress(addressDto);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id, @RequestBody AddressDto addressDto) {
        AddressDto updatedAddress = addressService.updateAddress(id, addressDto);
        if (updatedAddress != null) {
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        boolean deleted = addressService.deleteAddress(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
