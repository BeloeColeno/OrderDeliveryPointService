package com.java.petrovsm.orderdeliverypointservice.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PvzResponseDto {
    UUID id;
    String name;
    String address;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
