package com.example.projectJava.mapper;


import com.example.projectJava.dto.ReservationDto;
import com.example.projectJava.model.Book;
import com.example.projectJava.model.Reservation;
import com.example.projectJava.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;



@Component
public class ReservationMapper {

    public ReservationDto mapToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setBookId(reservation.getBook().getId());
        reservationDto.setUserId(reservation.getUser().getId());
        reservationDto.setReservationDate(reservation.getReservationDate());
        reservationDto.setStatus(reservation.getStatus());
        return reservationDto;
    }

    public Reservation mapToReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();

        Book book = new Book();
        book.setId(reservationDto.getBookId());
        
        User user = new User();
        user.setId(reservationDto.getUserId());

        reservation.setBook(book);
        reservation.setUser(user);
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setStatus(reservationDto.getStatus());
        return reservation;
    }

    public List<ReservationDto> mapListToReservationDto(List<Reservation> reservations) {
        return reservations.stream()
                .map(this::mapToReservationDto)
                .collect(Collectors.toList());
    }

    public List<Reservation> mapListToReservation(List<ReservationDto> reservationDtos) {
        return reservationDtos.stream()
                .map(this::mapToReservation)
                .collect(Collectors.toList());
    }
}

