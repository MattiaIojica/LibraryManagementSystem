package com.example.projectJava.service;

import com.example.projectJava.dto.LoanDto;
import com.example.projectJava.dto.ReservationDto;
import com.example.projectJava.dto.UserDto;
import com.example.projectJava.exception.DuplicateUserException;
import com.example.projectJava.exception.UserNotFoundException;
import com.example.projectJava.mapper.LoanMapper;
import com.example.projectJava.mapper.ReservationMapper;
import com.example.projectJava.mapper.UserMapper;
import com.example.projectJava.model.Loan;
import com.example.projectJava.model.Reservation;
import com.example.projectJava.model.User;
import com.example.projectJava.repository.ReservationRepository;
import com.example.projectJava.repository.LoanRepository;
import com.example.projectJava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;


    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       LoanRepository loanRepository,
                       LoanMapper loanMapper,
                       ReservationRepository reservationRepository,
                       ReservationMapper reservationMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public List<UserDto> getAll(){
        List<User> allStudents = userRepository.findAll();
        return userMapper.mapListToStudentDto(allStudents);
    }

    public UserDto save(UserDto userDto){
        User dbUser = userRepository.save(userMapper.mapToUser(userDto));
        return userMapper.mapToUserDto(dbUser);
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
        return userMapper.mapToUserDto(optionalUser.get());
    }

    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public List<LoanDto> getLoansByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            List<Loan> loans = loanRepository.findByUser(optionalUser.get());
            return loanMapper.mapListToLoanDto(loans);
        }
        return Collections.emptyList();
    }

    public List<ReservationDto> getReservationsByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            List<Reservation> reservations = reservationRepository.findByUser(optionalUser.get());
            return reservationMapper.mapListToReservationDto(reservations);
        }
        return Collections.emptyList();
    }
}
