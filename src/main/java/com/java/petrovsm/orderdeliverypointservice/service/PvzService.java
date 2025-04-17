package com.java.petrovsm.orderdeliverypointservice.service;

import com.java.petrovsm.orderdeliverypointservice.dto.PvzCreateDto;
import com.java.petrovsm.orderdeliverypointservice.dto.PvzResponseDto;
import com.java.petrovsm.orderdeliverypointservice.dto.PvzWithReceptionsDto;
import com.java.petrovsm.orderdeliverypointservice.dto.ReceptionDto;
import com.java.petrovsm.orderdeliverypointservice.entity.Pvz;
import com.java.petrovsm.orderdeliverypointservice.entity.Reception;
import com.java.petrovsm.orderdeliverypointservice.repository.PvzRepository;
import com.java.petrovsm.orderdeliverypointservice.repository.ReceptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PvzService {

    private final PvzRepository pvzRepository;
    private final ReceptionRepository receptionRepository;

    public PvzResponseDto createPvz(PvzCreateDto pvzDto) {
        Pvz pvz = new Pvz();
        pvz.setName(pvzDto.getName());
        pvz.setAddress(pvzDto.getAddress());
        pvz.setStatus(pvzDto.getStatus());
        pvz.setCreatedAt(LocalDateTime.now());
        pvz.setUpdatedAt(LocalDateTime.now());

        Pvz savedPvz = pvzRepository.save(pvz);
        return convertToResponseDto(savedPvz);
    }

    public Page<PvzWithReceptionsDto> getPvzList(LocalDateTime startDate, LocalDateTime endDate, int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<Pvz> pvzPage;

        if (startDate != null && endDate != null) {
            pvzPage = pvzRepository.findAllByCreatedAtBetween(startDate, endDate, pageRequest);
        } else {
            pvzPage = pvzRepository.findAll(pageRequest);
        }

        return pvzPage.map(this::convertToWithReceptionsDto);
    }

    private PvzResponseDto convertToResponseDto(Pvz pvz) {
        PvzResponseDto dto = new PvzResponseDto();
        dto.setId(pvz.getId());
        dto.setName(pvz.getName());
        dto.setAddress(pvz.getAddress());
        dto.setStatus(pvz.getStatus());
        dto.setCreatedAt(pvz.getCreatedAt());
        dto.setUpdatedAt(pvz.getUpdatedAt());
        return dto;
    }

    private PvzWithReceptionsDto convertToWithReceptionsDto(Pvz pvz) {
        PvzWithReceptionsDto dto = new PvzWithReceptionsDto();
        dto.setId(pvz.getId());
        dto.setName(pvz.getName());
        dto.setAddress(pvz.getAddress());
        dto.setStatus(pvz.getStatus());
        dto.setReceptions(receptionRepository.findAllByPvzId(pvz.getId()).stream()  // Передаем UUID
                .map(this::convertToReceptionDto)
                .toList());
        return dto;
    }

    private ReceptionDto convertToReceptionDto(Reception reception) {
        ReceptionDto dto = new ReceptionDto();
        dto.setId(reception.getId());
        dto.setReceptionDate(reception.getReceptionDate());
        dto.setStatus(reception.getStatus());
        dto.setPvzId(reception.getPvzId());
        return dto;
    }
}
