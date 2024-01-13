package com.example.projectJava.repository;

import com.example.projectJava.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(nativeQuery = true, value = "select * from reservations rez where rez.book_id = :id")
    List<Reservation> findAllByBookId(Long id);

    Optional<Reservation> findById(Long id);
}
