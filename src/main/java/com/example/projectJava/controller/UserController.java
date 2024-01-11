package com.example.projectJava.controller;


import com.example.projectJava.dto.UserDto;
import com.example.projectJava.mapper.UserMapper;
import com.example.projectJava.model.User;
import com.example.projectJava.service.ReservationService;
import com.example.projectJava.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    private UserService userService;
    private ReservationService reservationService;


    public UserController(UserService userService,
                          ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
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
}
