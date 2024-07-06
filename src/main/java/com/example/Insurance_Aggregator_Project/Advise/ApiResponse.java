package com.example.Insurance_Aggregator_Project.Advise;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private T response;
}
