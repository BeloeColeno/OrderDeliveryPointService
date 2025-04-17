package com.java.petrovsm.orderdeliverypointservice.dto;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class PvzWithReceptionsDto {
    UUID id;
    String name;
    String address;
    String status;
    List<ReceptionDto> receptions;
}
