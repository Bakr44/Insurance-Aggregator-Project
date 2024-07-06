package com.example.Insurance_Aggregator_Project.Controller;

import com.example.Insurance_Aggregator_Project.Repository.QuoteRepository;
import com.example.Insurance_Aggregator_Project.Service.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quotes")
@AllArgsConstructor
public class QuoteController {

    private final QuoteRepository quoteRepository;
    private final QuoteService quoteService;


    @GetMapping()
    public ResponseEntity getQuotes(){
        return ResponseEntity.ok().body(quoteRepository.findAll());
    }

    @PostMapping("/assignQuote")
    public ResponseEntity<String> assignQuote(Authentication authentication, @RequestParam int policyNumber) {
        String email = authentication.getName();
        return quoteService.assignQuoteToUser(email, policyNumber);
    }




}