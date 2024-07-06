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

	}

}
