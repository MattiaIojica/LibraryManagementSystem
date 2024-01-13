package com.example.projectJava.mapper;

import com.example.projectJava.dto.UserDto;
import com.example.projectJava.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final AddressMapper addressMapper;

    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }


    public List<UserDto> mapListToStudentDto(List<User> users){
        return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }

    public List<User> mapListToStudent(List<UserDto> userDtos){
        return userDtos.stream().map(this::mapToUser).collect(Collectors.toList());
    }

    public User mapToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setMemberSince(userDto.getMemberSince());
        user.setAddress(addressMapper.mapToAddress(userDto.getAddress()));

        return user;
    }

    public UserDto mapToUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setMemberSince(user.getMemberSince());
        userDto.setAddress(addressMapper.mapToAddressDto(user.getAddress()));

        return userDto;
    }
}

