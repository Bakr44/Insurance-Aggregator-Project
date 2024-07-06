package com.example.Insurance_Aggregator_Project.Controller;

import com.example.Insurance_Aggregator_Project.Model.Quote;
import com.example.Insurance_Aggregator_Project.Repository.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quotes")
@AllArgsConstructor
public class QuoteController {

    private final QuoteRepository quoteRepository;


    @GetMapping("/getQuotes")
    public ResponseEntity getQuotes(){
       return ResponseEntity.ok().body(quoteRepository.findAll());
    }

    public ResponseEntity submitQuote(@RequestBody Quote quote){
        quoteRepository.save(quote);
        return ResponseEntity.ok().body("Quote submitted successfully");
    }


}
