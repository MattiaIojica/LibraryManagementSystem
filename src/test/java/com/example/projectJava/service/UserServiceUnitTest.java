package com.example.projectJava.service;

import com.example.projectJava.dto.FineDto;
import com.example.projectJava.dto.ReservationDto;
import com.example.projectJava.dto.UserDto;
import com.example.projectJava.dto.LoanDto;
import com.example.projectJava.exception.UserNotFoundException;
import com.example.projectJava.mapper.FineMapper;
import com.example.projectJava.mapper.LoanMapper;
import com.example.projectJava.mapper.ReservationMapper;
import com.example.projectJava.mapper.UserMapper;
import com.example.projectJava.model.Fine;
import com.example.projectJava.model.Reservation;
import com.example.projectJava.model.User;
import com.example.projectJava.model.Loan;
import com.example.projectJava.repository.FineRepository;
import com.example.projectJava.repository.LoanRepository;
import com.example.projectJava.repository.ReservationRepository;
import com.example.projectJava.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private FineRepository fineRepository;
    @Mock
    private FineMapper fineMapper;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private ReservationMapper reservationMapper;
    @Mock
    private LoanRepository loanRepository;
    @Mock
    private LoanMapper loanMapper;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetById_success(){
        User user = getDummyUser();

        UserDto userDto = getDummyUserDto();

        Mockito.when(userRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(user));
        Mockito.when(userMapper.mapToUserDto(user))
                .thenReturn(userDto);

        UserDto returnedUser = userService.getById(10l);

        assertEquals(returnedUser.getFirstName(), userDto.getFirstName());
        assertEquals(returnedUser.getLastName(), userDto.getLastName());
        assertEquals(returnedUser.getEmail(), userDto.getEmail());
    }

    @Test
    public void testGetById_exception(){
        Mockito.when(userRepository.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getById(12L));
    }

    private User getDummyUser(){
        User user = new User();
        user.setId(10L);
        user.setFirstName("Mircea");
        user.setLastName("Pop");
        user.setEmail("mircea@gmail.com");
        return user;
    }

    private UserDto getDummyUserDto(){
        UserDto userDto = new UserDto();
        userDto.setId(10L);
        userDto.setFirstName("Mircea");
        userDto.setLastName("Pop");
        userDto.setEmail("mircea@gmail.com");
        return userDto;
    }

    private User getAnotherDummyUser(){
        User user = new User();
        user.setId(11L);
        user.setFirstName("Maria");
        user.setLastName("Georgescu");
        user.setEmail("maria@gmail.com");
        return user;
    }

    private UserDto getAnotherDummyUserDto(){
        UserDto userDto = new UserDto();
        userDto.setId(11L);
        userDto.setFirstName("Maria");
        userDto.setLastName("Georgescu");
        userDto.setEmail("maria@gmail.com");
        return userDto;
    }

    @Test
    public void testGetAll_Success(){
        List<User> userList = Arrays.asList(getDummyUser(), getAnotherDummyUser());
        List<UserDto> userDtoList = Arrays.asList(getDummyUserDto(), getAnotherDummyUserDto());

        Mockito.when(userRepository.findAll())
                .thenReturn(userList);
        Mockito.when(userMapper.mapListToStudentDto(userList))
                .thenReturn(userDtoList);

        List<UserDto> returnedUsers = userService.getAll();

        assertEquals(returnedUsers.size(), userDtoList.size());
        assertEquals(returnedUsers.get(0).getFirstName(), userDtoList.get(0).getFirstName());
        assertEquals(returnedUsers.get(1).getFirstName(), userDtoList.get(1).getFirstName());
    }

    @Test
    void testSaveUser() {
        UserDto userDto = getDummyUserDto();
        userDto.setId(null);

        when(userMapper.mapToUserDto(Mockito.any())).thenReturn(userDto);

        User savedUser = getDummyUser();

        when(userMapper.mapToUser(userDto)).thenReturn(savedUser);
        when(userRepository.save(savedUser)).thenReturn(savedUser);

        UserDto actualSavedUserDto = userService.save(userDto);


        assertEquals(userDto.getId(), actualSavedUserDto.getId());
        assertEquals(userDto.getFirstName(), actualSavedUserDto.getFirstName());
        assertEquals(userDto.getLastName(), actualSavedUserDto.getLastName());
        assertEquals(userDto.getEmail(), actualSavedUserDto.getEmail());
    }


    @Test
    void testDeleteUser() {
        Long userIdToDelete = 1L;

        userService.delete(userIdToDelete);

        verify(userRepository).deleteById(userIdToDelete);
    }


    @Test
    void testFindUserById() {
        User user = getDummyUser();
        Long userIdToFind = user.getId();

        when(userMapper.mapToUser(Mockito.any())).thenReturn(user);
        when(userRepository.findById(userIdToFind)).thenReturn(Optional.of(user));

        User actualUser = userService.findById(userIdToFind);

        assertEquals(user, actualUser);
    }

    @Test
    void testGetLoansByUserId() {
        User user = getDummyUser();
        Long userId = user.getId();
        List<Loan> expectedLoans = getDummyLoans();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(loanRepository.findByUser(user)).thenReturn(expectedLoans);

        List<LoanDto> expectedLoansDto = getDummyLoansDto();

        when(loanMapper.mapListToLoanDto(expectedLoans)).thenReturn(expectedLoansDto);

        List<LoanDto> actualLoansDto = userService.getLoansByUserId(userId);

        assertEquals(expectedLoansDto, actualLoansDto);
    }

    private List<Loan> getDummyLoans() {
        return new ArrayList<>(Arrays.asList(getDummyLoanOne()));
    }

    private List<LoanDto> getDummyLoansDto() {
        return new ArrayList<>(Arrays.asList(getDummyLoanDto()));
    }

    private Loan getDummyLoanOne(){
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setUser(getDummyUser());
        loan.setStatus("Active");
        return loan;
    }

    private LoanDto getDummyLoanDto(){
        LoanDto loanDto = new LoanDto();
        loanDto.setId(1L);
        loanDto.setUserId(getDummyUser().getId());
        loanDto.setStatus("Active");
        return loanDto;
    }

    private List<Fine> getDummyFines() {
        return Collections.singletonList(new Fine());
    }

    private List<FineDto> getDummyFinesDto() {
        return Collections.singletonList(new FineDto());
    }

    @Test
    void testGetFinesByUserId() {
        Long userId = 1L;
        User user = getDummyUser();
        List<Fine> expectedFines = getDummyFines();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(fineRepository.findByUser(user)).thenReturn(expectedFines);

        List<FineDto> expectedFinesDto = getDummyFinesDto();

        when(fineMapper.mapListToFineDto(expectedFines)).thenReturn(expectedFinesDto);

        List<FineDto> actualFinesDto = userService.getFinesByUserId(userId);

        assertEquals(expectedFinesDto, actualFinesDto);
    }

    @Test
    void testGetReservationsByUserId() {
        Long userId = 10L;
        User user = getDummyUser();
        List<Reservation> expectedReservations = getDummyReservations();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(reservationRepository.findByUser(user)).thenReturn(expectedReservations);

        List<ReservationDto> expectedReservationsDto = getDummyReservationsDto();

        when(reservationMapper.mapListToReservationDto(expectedReservations)).thenReturn(expectedReservationsDto);

        List<ReservationDto> actualReservationsDto = userService.getReservationsByUserId(userId);

        assertEquals(expectedReservationsDto, actualReservationsDto);
    }

    private List<Reservation> getDummyReservations() {
        return Collections.singletonList(new Reservation());
    }

    private List<ReservationDto> getDummyReservationsDto() {
        return Collections.singletonList(new ReservationDto());
    }

    @Test
    void testGetLoansByUserIdEmptyList() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        List<LoanDto> actualLoansDto = userService.getLoansByUserId(userId);

        assertEquals(Collections.emptyList(), actualLoansDto);
    }

    @Test
    void testGetFinesByUserIdEmptyList() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        List<FineDto> actualFinessDto = userService.getFinesByUserId(userId);

        assertEquals(Collections.emptyList(), actualFinessDto);
    }

    @Test
    void testGetReservationsByUserIdEmptyList() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        List<ReservationDto> actualReservationsDto = userService.getReservationsByUserId(userId);

        assertEquals(Collections.emptyList(), actualReservationsDto);
    }
}
