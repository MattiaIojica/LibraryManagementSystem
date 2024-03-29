package com.example.projectJava.controller;

import com.example.projectJava.dto.FineDto;
import com.example.projectJava.service.FineService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fines")
public class FineController {

    private final FineService fineService;

    @Autowired
    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping
    @Operation(summary = "Get all fines",
            description = "Returns the fines from the database")
    public ResponseEntity<List<FineDto>> getAllFines() {
        List<FineDto> fines = fineService.getAllFines();
        return ResponseEntity.ok(fines);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a fine by id",
            description = "Returns a fine as per the id")
    public ResponseEntity<FineDto> getFineById(@PathVariable Long id) {
        FineDto fine = fineService.getFineById(id);
        return fine != null
                ? ResponseEntity.ok(fine)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create a fine",
            description = "Returns the new fine")
    public ResponseEntity<FineDto> createFine(@RequestBody FineDto fineDto) {
        FineDto createdFine = fineService.createFine(fineDto);
        return ResponseEntity.ok(createdFine);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a fine",
            description = "Update a fine's information based on the provided fine ID")
    public ResponseEntity<FineDto> updateFine(@PathVariable Long id,
                                              @RequestBody FineDto fineDto) {
        FineDto updatedFine = fineService.updateFine(id, fineDto);
        return updatedFine != null
                ? ResponseEntity.ok(updatedFine)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a fine",
            description = "Delete a fine by id from the database")
    public ResponseEntity<Void> deleteFine(@PathVariable Long id) {
        boolean deleted = fineService.deleteFine(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
