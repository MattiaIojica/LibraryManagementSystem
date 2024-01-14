package com.example.projectJava.controller;


import com.example.projectJava.dto.FineDto;
import com.example.projectJava.dto.LoanDto;
import com.example.projectJava.dto.ReservationDto;
import com.example.projectJava.dto.UserDto;
import com.example.projectJava.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "/users", tags = "Users")
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

    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Get details for a user", notes = "Get the details for a user based on the information from the database and the user's id")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }


    @PostMapping
    @ApiOperation(value = "Create a user")
    public ResponseEntity<UserDto> save(@RequestBody @Valid UserDto userDto) {
        UserDto newUser = userService.save(userDto);
        return ResponseEntity.created(URI.create("/users/" + newUser.getId())).body(newUser);
    }


    @DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Delete a user", notes = "Delete a user by id from the database and it's reservations")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().body("Succesfully deleted");
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

    @PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Update a user", notes = "Update a user's information based on the provided user ID")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserDto updatedUserDto) {
        UserDto updatedUser = userService.update(id, updatedUserDto);
        return ResponseEntity.ok(updatedUser);
    }

}
