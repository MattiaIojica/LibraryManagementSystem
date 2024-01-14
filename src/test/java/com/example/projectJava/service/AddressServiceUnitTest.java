package com.example.projectJava.service;

import com.example.projectJava.mapper.AddressMapper;
import com.example.projectJava.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AddressServiceUnitTest {

    @Mock
    private AddressRepository repository;
    @InjectMocks
    private AddressService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteAddress_SuccessfulDeletion() {
        Long addressId = 1L;

        when(repository.existsById(addressId)).thenReturn(true);

        boolean result = service.deleteAddress(addressId);

        assertTrue(result); // Deletion should be successful
        verify(repository, times(1)).deleteById(addressId);
    }

    @Test
    void testDeleteAddress_AddressNotFound() {
        Long addressId = 1L;

        when(repository.existsById(addressId)).thenReturn(false);

        boolean result = service.deleteAddress(addressId);

        assertFalse(result); // Address not found, deletion should be unsuccessful
        verify(repository, never()).deleteById(anyLong());
    }
}
