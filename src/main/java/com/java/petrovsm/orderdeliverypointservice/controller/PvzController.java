package com.java.petrovsm.orderdeliverypointservice.controller;

import com.java.petrovsm.orderdeliverypointservice.dto.PvzCreateDto;
import com.java.petrovsm.orderdeliverypointservice.dto.PvzResponseDto;
import com.java.petrovsm.orderdeliverypointservice.dto.PvzWithReceptionsDto;
import com.java.petrovsm.orderdeliverypointservice.service.PvzService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/pvz")
public class PvzController {
    private final PvzService pvzService;

    public PvzController(PvzService pvzService) {
        this.pvzService = pvzService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ResponseEntity<PvzResponseDto> createPvz(@RequestBody @Valid PvzCreateDto pvzDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pvzService.createPvz(pvzDto));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'MODERATOR')")
    public ResponseEntity<Page<PvzWithReceptionsDto>> getPvzList(
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(pvzService.getPvzList(startDate, endDate, page, limit));
    }
}
