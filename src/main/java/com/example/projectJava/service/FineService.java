package com.example.projectJava.service;

import com.example.projectJava.dto.FineDto;
import com.example.projectJava.mapper.FineMapper;
import com.example.projectJava.model.Fine;
import com.example.projectJava.repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FineService {

    private final FineRepository fineRepository;
    private final FineMapper fineMapper;

    @Autowired
    public FineService(FineRepository fineRepository,
                       FineMapper fineMapper) {
        this.fineRepository = fineRepository;
        this.fineMapper = fineMapper;
    }

    public List<FineDto> getAllFines() {
        List<Fine> fines = fineRepository.findAll();
        return fines.stream()
                .map(fineMapper::mapToFineDto)
                .collect(Collectors.toList());
    }

    public FineDto getFineById(Long id) {
        Optional<Fine> optionalFine = fineRepository.findById(id);
        return optionalFine.map(fineMapper::mapToFineDto).orElse(null);
    }

    public FineDto createFine(FineDto fineDto) {
        Fine fine = fineMapper.mapToFine(fineDto);
        Fine savedFine = fineRepository.save(fine);
        return fineMapper.mapToFineDto(savedFine);
    }

    public FineDto updateFine(Long id,
                              FineDto fineDto) {
        Optional<Fine> optionalFine = fineRepository.findById(id);
        if (optionalFine.isPresent()) {
            Fine existingFine = optionalFine.get();
            existingFine.setAmount(fineDto.getAmount());
            existingFine.setPaymentStatus(fineDto.getPaymentStatus());

            Fine updatedFine = fineRepository.save(existingFine);
            return fineMapper.mapToFineDto(updatedFine);
        } else {
            return null; // Fine not found
        }
    }

    public boolean deleteFine(Long id) {
        if (fineRepository.existsById(id)) {
            fineRepository.deleteById(id);
            return true; // Deletion successful
        } else {
            return false; // Fine not found
        }
    }
}
