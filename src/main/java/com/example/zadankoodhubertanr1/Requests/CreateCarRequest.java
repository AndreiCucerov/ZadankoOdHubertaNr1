package com.example.zadankoodhubertanr1.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCarRequest {
    private String producer;
    private String color;
    private String owner;
}
