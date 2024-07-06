package com.example.Insurance_Aggregator_Project;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class InsuranceAggregatorProjectApplication {

	public static void main(String[] args) throws JsonProcessingException {
		 SpringApplication.run(InsuranceAggregatorProjectApplication.class, args);
//
//		String str =  "    {\r\n        \"policyNumber\": 1,\r\n        \"provider\": \"ACIG\",\r\n        \"type\": \"THIRD_PARTY\",\r\n        \"basePrice\": 1000,\r\n        \"Tax\": 150\r\n    }";
//		 var objectMapper = applicationContext.getBean(ObjectMapper.class);
//
//		final String cleanedJson = str.replaceAll("\\s+,\"\"", "");
//		System.out.println(cleanedJson);
//		System.out.println(objectMapper.readValue(cleanedJson,InsuranceQuote.class));

	}

}
