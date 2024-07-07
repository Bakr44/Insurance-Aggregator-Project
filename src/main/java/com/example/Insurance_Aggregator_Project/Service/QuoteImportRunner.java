package com.example.Insurance_Aggregator_Project.Service;

import com.example.Insurance_Aggregator_Project.integration.insurance.MockInsuranceIntegrationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class QuoteImportRunner implements CommandLineRunner {


    private final MockInsuranceIntegrationService mockInsuranceIntegrationService;

    public QuoteImportRunner(MockInsuranceIntegrationService mockInsuranceIntegrationService) {
        this.mockInsuranceIntegrationService = mockInsuranceIntegrationService;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            mockInsuranceIntegrationService.saveQuotesFromGoogleSheet();
            System.out.println("Quotes imported successfully");
        } catch (Exception e) {
            System.err.println("Error importing quotes: " + e.getMessage());
        }
    }
}