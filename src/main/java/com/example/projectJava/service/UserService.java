package com.example.projectJava.service;

import com.example.projectJava.dto.UserDto;
import com.example.projectJava.exception.DuplicateUserException;
import com.example.projectJava.exception.UserNotFoundException;
import com.example.projectJava.mapper.UserMapper;
import com.example.projectJava.model.User;
import com.example.projectJava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAll(){
        List<User> allStudents = userRepository.findAll();
        return userMapper.mapListToStudentDto(allStudents);
    }

    public UserDto save(UserDto userDto){
        User dbUser = userRepository.save(userMapper.map(userDto));
        return userMapper.map(dbUser);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public List<UserDto> getUsersWithAddressStreetName(String streetName){
        return userMapper.mapListToStudentDto(userRepository.findAllByAddress_Street(streetName));
    }

    public UserDto getById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(!optionalUser.isPresent()){
            throw new UserNotFoundException(id);
        }
        return userMapper.map(optionalUser.get());
    }
}
