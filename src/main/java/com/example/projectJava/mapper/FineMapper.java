package com.example.projectJava.mapper;

import com.example.projectJava.dto.FineDto;
import com.example.projectJava.model.Fine;
import com.example.projectJava.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FineMapper {

    public FineDto mapToFineDto(Fine fine) {
        FineDto fineDto = new FineDto();
        fineDto.setId(fine.getId());
        fineDto.setUserId(fine.getUser().getId());
        fineDto.setAmount(fine.getAmount());
        fineDto.setReason(fine.getReason());
        fineDto.setPaymentStatus(fine.getPaymentStatus());
        return fineDto;
    }

    public Fine mapToFine(FineDto fineDto) {
        Fine fine = new Fine();
        fine.setId(fineDto.getId());
        User user = new User();
        user.setId(fineDto.getUserId());
        fine.setUser(user);
        fine.setAmount(fineDto.getAmount());
        fine.setReason(fineDto.getReason());
        fine.setPaymentStatus(fineDto.getPaymentStatus());
        return fine;
    }

    public List<FineDto> mapListToFineDto(List<Fine> fines) {
        return fines.stream()
                .map(this::mapToFineDto)
                .collect(Collectors.toList());
    }
}
