package com.example.projectJava.service;

import com.example.projectJava.mapper.BookMapper;
import com.example.projectJava.mapper.LoanMapper;
import com.example.projectJava.repository.BookRepository;
import com.example.projectJava.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class LoanServiceUnitTest {
    @Mock
    private LoanRepository repository;
    @InjectMocks
    private LoanService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteAddress_SuccessfulDeletion() {
        Long idToDelete = 1L;

        when(repository.existsById(idToDelete)).thenReturn(true);

        boolean result = service.deleteLoan(idToDelete);

        assertTrue(result); // Deletion should be successful
        verify(repository, times(1)).deleteById(idToDelete);
    }

    @Test
    void testDeleteAddress_AddressNotFound() {
        Long idToDelete = 1L;

        when(repository.existsById(idToDelete)).thenReturn(false);

        boolean result = service.deleteLoan(idToDelete);

        assertFalse(result); // Not found, deletion should be unsuccessful
        verify(repository, never()).deleteById(anyLong());
    }
}
