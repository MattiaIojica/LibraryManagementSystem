package com.example.projectJava.repository;

import com.example.projectJava.model.Fine;
import com.example.projectJava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FineRepository extends JpaRepository<Fine, Long> {

    List<Fine> findByUserId(Long userId);

    Optional<Fine> findByIdAndUserId(Long fineId, Long userId);

    List<Fine> findByUser(User user);
}