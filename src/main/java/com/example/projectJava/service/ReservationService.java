package com.example.projectJava.service;

import com.example.projectJava.dto.ReservationDto;
import com.example.projectJava.mapper.ReservationMapper;
import com.example.projectJava.model.Reservation;
import com.example.projectJava.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.mapListToReservationDto(reservations);
    }

    public ReservationDto getReservationById(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        return optionalReservation.map(reservationMapper::mapToReservationDto).orElse(null);
    }

    public ReservationDto createReservation(ReservationDto reservationDto) {

        Reservation reservation = reservationMapper.mapToReservation(reservationDto);
        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.mapToReservationDto(savedReservation);
    }

    public ReservationDto updateReservation(Long id, ReservationDto reservationDto) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation existingReservation = optionalReservation.get();

            existingReservation.setReservationDate(reservationDto.getReservationDate());
            existingReservation.setStatus(reservationDto.getStatus());

            Reservation updatedReservation = reservationRepository.save(existingReservation);
            return reservationMapper.mapToReservationDto(updatedReservation);
        } else {
            return null; // Reservation not found
        }
    }

    public boolean deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true; // Deletion successful
        } else {
            return false; // Reservation not found
        }
    }
}
