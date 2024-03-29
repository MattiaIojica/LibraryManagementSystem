package com.example.projectJava.service;

import com.example.projectJava.dto.LoanDto;
import com.example.projectJava.mapper.LoanMapper;
import com.example.projectJava.model.Loan;
import com.example.projectJava.repository.LoanRepository;
import com.example.projectJava.repository.ReservationRepository;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    @Autowired
    public LoanService(
            LoanRepository loanRepository,
            LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
    }

    public List<LoanDto> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loanMapper.mapListToLoanDto(loans);
    }

    public LoanDto getLoanById(Long id) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        return optionalLoan.map(loanMapper::mapToLoanDto).orElse(null);
    }

    public LoanDto createLoan(LoanDto loanDto) {
        Loan loan = loanMapper.mapToLoan(loanDto);
        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.mapToLoanDto(savedLoan);
    }

    public LoanDto updateLoan(Long id,
                              LoanDto loanDto) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (optionalLoan.isPresent()) {
            Loan existingLoan = optionalLoan.get();

            existingLoan.setDueDate(loanDto.getDueDate());
            existingLoan.setReturnDate(loanDto.getReturnDate());
            existingLoan.setStatus(loanDto.getStatus());

            Loan updatedLoan = loanRepository.save(existingLoan);
            return loanMapper.mapToLoanDto(updatedLoan);
        } else {
            return null; // Loan not found
        }
    }

    public boolean deleteLoan(Long id) {
        if (loanRepository.existsById(id)) {
            loanRepository.deleteById(id);
            return true; // Deletion successful
        } else {
            return false; // Loan not found
        }
    }
}
