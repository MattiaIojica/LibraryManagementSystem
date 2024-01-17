package com.example.projectJava.controller;


import com.example.projectJava.dto.FineDto;
import com.example.projectJava.dto.LoanDto;
import com.example.projectJava.dto.ReservationDto;
import com.example.projectJava.dto.UserDto;
import com.example.projectJava.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "Get all Users", description = "Returns the users from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @Operation(summary = "Get a User by id", description = "Returns a user as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The User was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable @Parameter(example = "1") Long id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }


    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Create a User", description = "Returns the new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Saved"),
            @ApiResponse(responseCode = "400", description = "Field Validation Error"),
            @ApiResponse(responseCode = "500", description = "Bad Request")
    })
    public ResponseEntity<UserDto> save(@RequestBody @Valid UserDto userDto) {
        UserDto newUser = userService.save(userDto);
        return ResponseEntity.created(URI.create("/users/" + newUser.getId())).body(newUser);
    }


    @DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Delete a user", description = "Delete a user by id from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - The User was not found")
    })
    public ResponseEntity<String> delete(@PathVariable @Parameter(example = "1") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().body("Successfully deleted");
    }


    @GetMapping("/{id}/loans")
    @Operation(summary = "Get loans", description = "Returns the list of loans of the user with the specific id")
    public ResponseEntity<List<LoanDto>> getLoansByUserId(@PathVariable @Parameter(example = "1") Long id) {
        List<LoanDto> loans = userService.getLoansByUserId(id);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}/reservations")
    @Operation(summary = "Get reservations", description = "Returns the list of reservations of the user with the specific id")
    public ResponseEntity<List<ReservationDto>> getReservationsByUserId(@PathVariable @Parameter(example = "1") Long id) {
        List<ReservationDto> reservations = userService.getReservationsByUserId(id);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}/fines")
    @Operation(summary = "Get fines", description = "Returns the list of fines of the user with the specific id")
    public ResponseEntity<List<FineDto>> getFinesByUserID(@PathVariable @Parameter(example = "1") Long id) {
        List<FineDto> fines = userService.getFinesByUserId(id);
        return ResponseEntity.ok(fines);
    }

    @PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Update a user", description = "Update a user's information based on the provided user ID")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid @Parameter(example = "1") UserDto updatedUserDto) {
        UserDto updatedUser = userService.update(id, updatedUserDto);
        return ResponseEntity.ok(updatedUser);
    }

}
