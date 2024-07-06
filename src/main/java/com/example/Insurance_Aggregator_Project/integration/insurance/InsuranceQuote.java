package com.example.Insurance_Aggregator_Project.integration.insurance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InsuranceQuote {
    @JsonProperty("policyNumber")
    private int policyNumber;
    @JsonProperty("provider")
    private String provider;
    @JsonProperty("type")
    private String type;
    @JsonProperty("basePrice")
    private float basePrice;
    @JsonProperty("Tax")
    private float tax;
}
