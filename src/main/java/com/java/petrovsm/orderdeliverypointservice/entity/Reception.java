package com.java.petrovsm.orderdeliverypointservice.entity;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Reception {
    UUID id;
    LocalDateTime receptionDate;
    String status;
    UUID pvzId;
}
