package com.example.Insurance_Aggregator_Project.Controller;

import com.example.Insurance_Aggregator_Project.integration.insurance.InsuranceIntegrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/insurances")
@AllArgsConstructor
public class GoogleSheetsController {

    private final InsuranceIntegrationService insuranceIntegrationService;

    @GetMapping
    public ResponseEntity<Object> readSheet(){
        return  ResponseEntity.ok(insuranceIntegrationService.getInsuranceQuotes());
    }
}
