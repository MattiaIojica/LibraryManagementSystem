package com.example.projectJava.service;

import com.example.projectJava.dto.AddressDto;
import com.example.projectJava.mapper.AddressMapper;
import com.example.projectJava.model.Address;
import com.example.projectJava.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {


    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository,
                          AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addressMapper.mapListToAddressDto(addresses);
    }

    public AddressDto getAddressById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.map(addressMapper::mapToAddressDto).orElse(null);
    }

    public AddressDto createAddress(AddressDto addressDto) {
        Address address = addressMapper.mapToAddress(addressDto);
        Address savedAddress = addressRepository.save(address);
        return addressMapper.mapToAddressDto(savedAddress);
    }

    public AddressDto updateAddress(Long id,
                                    AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address existingAddress = optionalAddress.get();
            existingAddress.setStreet(addressDto.getStreet());
            existingAddress.setStreetNo(addressDto.getStreetNo());
            existingAddress.setBuilding(addressDto.getBuilding());

            Address updatedAddress = addressRepository.save(existingAddress);
            return addressMapper.mapToAddressDto(updatedAddress);
        } else {
            return null; // Address not found
        }
    }

    public boolean deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true; // Deletion successful
        } else {
            return false; // Address not found
        }
    }
}
