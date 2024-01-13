package com.example.projectJava.controller;


import com.example.projectJava.dto.FineDto;
import com.example.projectJava.dto.LoanDto;
import com.example.projectJava.dto.ReservationDto;
import com.example.projectJava.dto.UserDto;
import com.example.projectJava.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/id/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }


    @PostMapping
    public UserDto save(@RequestBody @Valid UserDto userDto) {
        return userService.save(userDto);
    }

    @GetMapping("/address/street/{streetName}")
    public List<UserDto> getAllStudentsByAddressStreet(@PathVariable String streetName) {
        return userService.getUsersWithAddressStreetName(streetName);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }


    @GetMapping("/{id}/loans")
    public ResponseEntity<List<LoanDto>> getLoansByUserId(@PathVariable Long id) {
        List<LoanDto> loans = userService.getLoansByUserId(id);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationDto>> getReservationsByUserId(@PathVariable Long id) {
        List<ReservationDto> reservations = userService.getReservationsByUserId(id);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}/fines")
    public ResponseEntity<List<FineDto>> getFinesByUserID(@PathVariable Long id) {
        List<FineDto> fines = userService.getFinesByUserId(id);
        return ResponseEntity.ok(fines);
    }
}
