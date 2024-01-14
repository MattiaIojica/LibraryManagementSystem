package com.example.projectJava.service;

import com.example.projectJava.mapper.LoanMapper;
import com.example.projectJava.mapper.ReservationMapper;
import com.example.projectJava.repository.LoanRepository;
import com.example.projectJava.repository.ReservationRepository;
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

public class ReservationServiceUnitTest {
    @Mock
    private ReservationRepository repository;
    @InjectMocks
    private ReservationService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteAddress_SuccessfulDeletion() {
        Long idToDelete = 1L;

        when(repository.existsById(idToDelete)).thenReturn(true);

        boolean result = service.deleteReservation(idToDelete);

        assertTrue(result); // Deletion should be successful
        verify(repository, times(1)).deleteById(idToDelete);
    }

    @Test
    void testDeleteAddress_AddressNotFound() {
        Long idToDelete = 1L;

        when(repository.existsById(idToDelete)).thenReturn(false);

        boolean result = service.deleteReservation(idToDelete);

        assertFalse(result); // Not found, deletion should be unsuccessful
        verify(repository, never()).deleteById(anyLong());
    }
}
