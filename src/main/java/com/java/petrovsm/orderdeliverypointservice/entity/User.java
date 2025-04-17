package com.java.petrovsm.orderdeliverypointservice.entity;

import com.java.petrovsm.orderdeliverypointservice.model.UserRole;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    UUID id;
    String email;
    String password;
    UserRole role;
}
