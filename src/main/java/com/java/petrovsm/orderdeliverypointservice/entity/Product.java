package com.java.petrovsm.orderdeliverypointservice.entity;

import com.java.petrovsm.orderdeliverypointservice.model.ProductType;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    UUID id;
    LocalDateTime dateTime;
    ProductType type;
    UUID receptionId;
}
