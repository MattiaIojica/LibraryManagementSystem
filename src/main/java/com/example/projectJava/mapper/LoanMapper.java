package com.example.projectJava.mapper;

import com.example.projectJava.dto.LoanDto;
import com.example.projectJava.model.Loan;
import com.example.projectJava.service.BookService;
import com.example.projectJava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanMapper {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    public LoanDto mapToLoanDto(Loan loan) {
        LoanDto loanDto = new LoanDto();
        loanDto.setId(loan.getId());
        loanDto.setBookId(loan.getBook().getId());
        loanDto.setUserId(loan.getUser().getId());
        loanDto.setDueDate(loan.getDueDate());
        loanDto.setReturnDate(loan.getReturnDate());
        loanDto.setStatus(loan.getStatus());
        return loanDto;
    }

    public Loan mapToLoan(LoanDto loanDto) {
        Loan loan = new Loan();
        loan.setId(loanDto.getId());
        loan.setBook(bookService.findById(loanDto.getBookId()));
        loan.setUser(userService.findById(loanDto.getUserId()));
        loan.setDueDate(loanDto.getDueDate());
        loan.setReturnDate(loanDto.getReturnDate());
        loan.setStatus(loanDto.getStatus());
        return loan;
    }

    public List<LoanDto> mapListToLoanDto(List<Loan> loans) {
        return loans.stream()
                .map(this::mapToLoanDto)
                .collect(Collectors.toList());
    }
}

