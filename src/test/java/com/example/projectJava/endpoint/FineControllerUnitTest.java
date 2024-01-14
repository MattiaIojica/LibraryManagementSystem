package com.example.projectJava.endpoint;

import com.example.projectJava.controller.BookController;
import com.example.projectJava.controller.FineController;
import com.example.projectJava.dto.BookDto;
import com.example.projectJava.dto.FineDto;
import com.example.projectJava.service.BookService;
import com.example.projectJava.service.FineService;
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

public class FineControllerUnitTest {
    private MockMvc mockMvc;
    @Mock
    private FineService service;
    @InjectMocks
    private FineController controller;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<FineDto> dtoList = getDummyDtos();
        Mockito.when(service.getAllFines()).thenReturn(dtoList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fines"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(dtoList)));
    }

    private List<FineDto> getDummyDtos(){
        return new ArrayList<>(Arrays.asList(getDummyDtoOne(), getDummyDtoTwo()));
    }

    private FineDto getDummyDtoOne(){
        FineDto dto = new FineDto();

        dto.setId(1L);
        dto.setUserId(1L);
        dto.setReason("Late Return");

        return dto;
    }

    private FineDto getDummyDtoTwo(){
        FineDto dto = new FineDto();

        dto.setId(2L);
        dto.setUserId(1L);
        dto.setReason("Late Return");

        return dto;
    }
}
