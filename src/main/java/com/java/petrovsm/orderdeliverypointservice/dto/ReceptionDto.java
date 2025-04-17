package com.java.petrovsm.orderdeliverypointservice.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReceptionDto {
    UUID id;
    LocalDateTime receptionDate;
    String status;
    UUID pvzId;
}
