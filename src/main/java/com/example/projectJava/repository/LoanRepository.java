package com.example.projectJava.repository;

import com.example.projectJava.model.Loan;
import com.example.projectJava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByUserId(Long userId);

    List<Loan> findAllByBookId(Long bookId);

    List<Loan> findByUser(User user);
}
