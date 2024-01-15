package com.example.projectJava.controller;

import com.example.projectJava.dto.LoanDto;
import com.example.projectJava.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;


    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    @Operation(summary = "Get all loans", description = "Returns the loans from the database")
    public ResponseEntity<List<LoanDto>> getAllLoans() {
        List<LoanDto> loans = loanService.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a loan by id", description = "Returns a loan as per the id")
    public ResponseEntity<LoanDto> getLoanById(@PathVariable Long id) {
        LoanDto loanDto = loanService.getLoanById(id);
        if (loanDto != null) {
            return new ResponseEntity<>(loanDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Create a loan", description = "Returns the new loan")
    public ResponseEntity<LoanDto> createLoan(@RequestBody @Valid LoanDto loanDto) {
        LoanDto createdLoan = loanService.createLoan(loanDto);

        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a loan",
            description = "Update a loan's information based on the provided loan ID")
    public ResponseEntity<LoanDto> updateLoan(@PathVariable Long id,
                                              @RequestBody @Valid LoanDto loanDto) {
        LoanDto updatedLoan = loanService.updateLoan(id, loanDto);
        if (updatedLoan != null) {
            return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a loan",
            description = "Delete a loan by id from the database")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        if (loanService.deleteLoan(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
