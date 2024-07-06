package com.example.Insurance_Aggregator_Project.integration.insurance;

import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@ConditionalOnProperty(name="integration.insurance.isMock",havingValue = "false", matchIfMissing = false)
@Service
@Transactional
public class InsuranceIntegrationServiceImpl implements InsuranceIntegrationService {
    @Override
    public List<InsuranceQuote> getInsuranceQuotes() {
        return null;
    }
}
