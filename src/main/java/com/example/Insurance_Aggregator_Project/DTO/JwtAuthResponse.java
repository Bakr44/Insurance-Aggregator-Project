package com.example.Insurance_Aggregator_Project.DTO;

import lombok.Data;

@Data
public class JwtAuthResponse {

    private String token;

    public JwtAuthResponse(String token) {
        this.token = token;
    }
}
