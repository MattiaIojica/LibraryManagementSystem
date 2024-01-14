package com.example.projectJava.endpoint;

import com.example.projectJava.controller.FineController;
import com.example.projectJava.controller.LoanController;
import com.example.projectJava.dto.FineDto;
import com.example.projectJava.dto.LoanDto;
import com.example.projectJava.service.FineService;
import com.example.projectJava.service.LoanService;
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

public class LoanControllerUnitTest {
    private MockMvc mockMvc;
    @Mock
    private LoanService service;
    @InjectMocks
    private LoanController controller;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<LoanDto> dtoList = getDummyDtos();
        Mockito.when(service.getAllLoans()).thenReturn(dtoList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/loans"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(dtoList)));
    }

    private List<LoanDto> getDummyDtos(){
        return new ArrayList<>(Arrays.asList(getDummyDtoOne(), getDummyDtoTwo()));
    }

    private LoanDto getDummyDtoOne(){
        LoanDto dto = new LoanDto();

        dto.setId(1L);
        dto.setUserId(1L);
        dto.setStatus("Active");
        return dto;
    }

    private LoanDto getDummyDtoTwo(){
        LoanDto dto = new LoanDto();

        dto.setId(2L);
        dto.setUserId(1L);
        dto.setStatus("Active");

        return dto;
    }
}
