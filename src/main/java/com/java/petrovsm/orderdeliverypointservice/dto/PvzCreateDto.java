package com.java.petrovsm.orderdeliverypointservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PvzCreateDto {
    @NotBlank(message = "Название не может быть пустым")
    String name;

    @NotBlank(message = "Адрес не может быть пустым")
    String address;

    @NotNull(message = "Статус не может быть пустым")
    String status;
}
