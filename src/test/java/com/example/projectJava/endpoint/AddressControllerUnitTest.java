package com.example.projectJava.endpoint;

import com.example.projectJava.controller.AddressController;
import com.example.projectJava.dto.AddressDto;
import com.example.projectJava.dto.UserDto;
import com.example.projectJava.service.AddressService;
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

public class AddressControllerUnitTest {
    private MockMvc mockMvc;
    @Mock
    private AddressService addressService;
    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
    }

    @Test
    public void testGetAllAddresses() throws Exception {
        List<AddressDto> addressDtoList = getDummyAddressDtos();
        Mockito.when(addressService.getAllAddresses()).thenReturn(addressDtoList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/addresses"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(addressDtoList)));
    }

    private List<AddressDto> getDummyAddressDtos(){
        return new ArrayList<>(Arrays.asList(getDummyAddressDtoOne(), getDummyAddressDtoTwo()));
    }

    private AddressDto getDummyAddressDtoOne(){
        AddressDto addressDto = new AddressDto();

        addressDto.setId(1L);
        addressDto.setBuilding("E1");
        addressDto.setStreet("Bdul Magheru");
        addressDto.setStreetNo(1);
        return addressDto;
    }

    private AddressDto getDummyAddressDtoTwo(){
        AddressDto addressDto = new AddressDto();

        addressDto.setId(2L);
        addressDto.setBuilding("A2");
        addressDto.setStreet("Calea Victoriei");
        addressDto.setStreetNo(12);
        return addressDto;
    }

}
