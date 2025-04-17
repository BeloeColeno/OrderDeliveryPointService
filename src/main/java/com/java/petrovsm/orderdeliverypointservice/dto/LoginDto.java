package com.java.petrovsm.orderdeliverypointservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "Логин не может быть пустым")
    String username;

    @NotBlank(message = "Пароль не может быть пустым")
    String password;
}