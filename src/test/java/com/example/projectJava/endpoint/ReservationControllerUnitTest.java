package com.example.projectJava.endpoint;

import com.example.projectJava.controller.LoanController;
import com.example.projectJava.controller.ReservationController;
import com.example.projectJava.dto.LoanDto;
import com.example.projectJava.dto.ReservationDto;
import com.example.projectJava.model.Reservation;
import com.example.projectJava.service.LoanService;
import com.example.projectJava.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReservationControllerUnitTest {
    private MockMvc mockMvc;
    @Mock
    private ReservationService service;
    @InjectMocks
    private ReservationController controller;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<ReservationDto> dtoList = getDummyDtos();
        Mockito.when(service.getAllReservations()).thenReturn(dtoList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/reservations"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(dtoList)));
    }

    private List<ReservationDto> getDummyDtos(){
        return new ArrayList<>(Arrays.asList(getDummyDtoOne(), getDummyDtoTwo()));
    }

    private ReservationDto getDummyDtoOne(){
        ReservationDto dto = new ReservationDto();

        dto.setId(1L);
        dto.setUserId(1L);
        dto.setStatus("Active");

        return dto;
    }

    private ReservationDto getDummyDtoTwo(){
        ReservationDto dto = new ReservationDto();

        dto.setId(2L);
        dto.setUserId(1L);
        dto.setStatus("Active");

        return dto;
    }
}
