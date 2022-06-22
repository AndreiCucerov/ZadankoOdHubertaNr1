package com.example.zadankoodhubertanr1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto {
    private Long id;
    private String producer;
    private String color;
    private String owner;
}
