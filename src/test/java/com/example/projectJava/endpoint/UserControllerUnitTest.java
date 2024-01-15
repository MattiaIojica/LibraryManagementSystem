package com.example.projectJava.endpoint;

import com.example.projectJava.controller.UserController;
import com.example.projectJava.dto.FineDto;
import com.example.projectJava.dto.LoanDto;
import com.example.projectJava.dto.ReservationDto;
import com.example.projectJava.dto.UserDto;
import com.example.projectJava.model.Loan;
import com.example.projectJava.model.User;
import com.example.projectJava.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserControllerUnitTest {

    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetAll_Success() throws Exception {
        List<UserDto> users = getDummyUserDtos();
        Mockito.when(userService.getAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(users)));
    }

    private List<UserDto> getDummyUserDtos(){
        return new ArrayList<>(Arrays.asList(getDummyUserDtoOne(), getDummyUserDtoTwo()));
    }

    private UserDto getDummyUserDtoOne(){
        UserDto userDto = new UserDto();
        userDto.setId(10L);
        userDto.setFirstName("Mircea");
        userDto.setLastName("Pop");
        userDto.setEmail("mircea@gmail.com");
        return userDto;
    }

    private UserDto getDummyUserDtoTwo(){
        UserDto userDto = new UserDto();
        userDto.setId(11L);
        userDto.setFirstName("Maria");
        userDto.setLastName("Georgescu");
        return userDto;
    }

    @Test
	public void deleteUser() throws Exception {

		mockMvc.perform(delete("/users/{id}", 1).contentType("application/json"))
				.andExpect(content().string("Successfully deleted")).andExpect(status().isOk());
	}

    @Test
    public void getUser() throws Exception {

        UserDto userDto = getDummyUserDtoOne();

        when(userService.getById(any())).thenReturn(userDto);

        mockMvc.perform(get("/users/{id}", userDto.getId())
                        .contentType("application/json")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userDto.getId()))
                .andExpect(jsonPath("$.lastName").value(userDto.getLastName()))
                .andExpect(jsonPath("$.firstName").value(userDto.getFirstName()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()));

    }
    @Test
    public void createUser() throws Exception {
        UserDto userDto = getDummyUserDtoOne();
        userDto.setId(null);
        UserDto savedUserDto = getDummyUserDtoOne();


        when(userService.save(any())).thenReturn(savedUserDto);

        mockMvc.perform(post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.lastName").value(userDto.getLastName()))
                .andExpect(jsonPath("$.firstName").value(userDto.getFirstName()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()));
    }


    @Test
    public void getLoansByUserId_Success() throws Exception {
        Long userId = 1L;
        List<LoanDto> loans = getDummyLoanDtos();

        when(userService.getLoansByUserId(userId)).thenReturn(loans);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/{id}/loans", userId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(loans)));
    }


    private List<LoanDto> getDummyLoanDtos() {
        return new ArrayList<>(Arrays.asList(getDummyLoanDtoOne(), getDummyLoanDtoTwo()));
    }

    private LoanDto getDummyLoanDtoOne(){
        LoanDto loanDto = new LoanDto();
        loanDto.setId(1L);
        loanDto.setUserId(1L);
        loanDto.setBookId(1L);
        loanDto.setStatus("Active");
        return loanDto;
    }
    private LoanDto getDummyLoanDtoTwo(){
        LoanDto loanDto = new LoanDto();
        loanDto.setId(2L);
        loanDto.setUserId(1L);
        loanDto.setBookId(2L);
        loanDto.setStatus("Active");
        return loanDto;
    }


    @Test
    public void getFinesByUserId_Success() throws Exception {
        Long userId = 1L;
        List<FineDto> fines = getDummyFineDtos();

        when(userService.getFinesByUserId(userId)).thenReturn(fines);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/{id}/fines", userId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(fines)));
    }

    private List<FineDto> getDummyFineDtos() {
        return new ArrayList<>(Arrays.asList(new FineDto(), new FineDto()));
    }

    @Test
    public void getReservationsByUserId_Success() throws Exception {
        Long userId = 1L;
        List<ReservationDto> reservationDtos = getDummyReservationDtos();

        when(userService.getReservationsByUserId(userId)).thenReturn(reservationDtos);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/{id}/reservations", userId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(reservationDtos)));
    }

    private List<ReservationDto> getDummyReservationDtos() {
        return new ArrayList<>(Arrays.asList(new ReservationDto(), new ReservationDto()));
    }

    @Test
    public void testUpdateUser_Success() throws Exception {
        UserDto userDto = getDummyUserDtoOne();
        UserDto updatedUser = getUpdatedUser();
        Long userId = userDto.getId();

        when(userService.update(any(), any())).thenReturn(updatedUser);

        mockMvc.perform(put("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(updatedUser)));

    }

    private UserDto getUpdatedUser() {
        UserDto updatedUser = new UserDto();
        updatedUser.setId(10L);
        updatedUser.setFirstName("UpdatedFirstName");
        updatedUser.setLastName("UpdatedLastName");
        updatedUser.setEmail("updated.email@example.com");
        return updatedUser;
    }
}
