package com.example.projectJava.endpoint;

import com.example.projectJava.controller.BookController;
import com.example.projectJava.controller.UserController;
import com.example.projectJava.dto.BookDto;
import com.example.projectJava.dto.UserDto;
import com.example.projectJava.service.BookService;
import com.example.projectJava.service.UserService;
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

public class BookControllerUnitTest {

    private MockMvc mockMvc;
    @Mock
    private BookService service;
    @InjectMocks
    private BookController controller;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<BookDto> dtoList = getDummyDtos();
        Mockito.when(service.getAllBooks()).thenReturn(dtoList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(dtoList)));
    }

    private List<BookDto> getDummyDtos(){
        return new ArrayList<>(Arrays.asList(getDummyDtoOne(), getDummyDtoTwo()));
    }

    private BookDto getDummyDtoOne(){
        BookDto dto = new BookDto();

        dto.setId(1L);
        dto.setAuthor("Steven");
        dto.setGenre("Romantic");

        return dto;
    }

    private BookDto getDummyDtoTwo(){
        BookDto dto = new BookDto();

        dto.setId(2L);
        dto.setAuthor("Steven");
        dto.setGenre("Romantic");

        return dto;
    }
}
