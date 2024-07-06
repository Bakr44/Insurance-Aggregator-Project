package com.example.Insurance_Aggregator_Project.Service;

import com.example.Insurance_Aggregator_Project.Model.Quote;
import com.example.Insurance_Aggregator_Project.Model.User;
import com.example.Insurance_Aggregator_Project.Repository.QuoteRepository;
import com.example.Insurance_Aggregator_Project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserDetailsServiceImp userDetailsServiceImp;

    public ResponseEntity<String> assignQuoteToUser(String  email, int policyNumber) {
        Optional<User> userOptional = userDetailsServiceImp.getUserByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        User user = userOptional.get();
        Quote quote = quoteRepository.findByPolicyNumber(policyNumber);
        if (quote == null) {
            return ResponseEntity.status(404).body("Policy not found");
        }

        user.setQuote(quote);
        userDetailsServiceImp.saveUser(user);

        return ResponseEntity.ok().body("Quote assigned to user successfully");
    }
}
