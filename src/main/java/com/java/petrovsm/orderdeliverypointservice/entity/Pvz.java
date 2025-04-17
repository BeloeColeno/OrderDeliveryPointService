package com.java.petrovsm.orderdeliverypointservice.entity;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Pvz {
    UUID id;
    String name;
    String address;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
