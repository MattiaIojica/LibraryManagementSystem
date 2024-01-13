package com.example.projectJava.mapper;

import com.example.projectJava.dto.AddressDto;
import com.example.projectJava.model.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    public Address mapToAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setStreet(addressDto.getStreet());
        address.setStreetNo(addressDto.getStreetNo());
        address.setBuilding(addressDto.getBuilding());
        return address;
    }

    public AddressDto mapToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setStreet(address.getStreet());
        addressDto.setStreetNo(address.getStreetNo());
        addressDto.setBuilding(address.getBuilding());
        return addressDto;
    }

    public List<AddressDto> mapListToAddressDto(List<Address> addresses) {
        return addresses.stream()
                .map(this::mapToAddressDto)
                .collect(Collectors.toList());
    }
}