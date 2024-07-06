package com.example.Insurance_Aggregator_Project.Controller;

import com.example.Insurance_Aggregator_Project.Model.User;
import com.example.Insurance_Aggregator_Project.Repository.UserRepository;
import com.example.Insurance_Aggregator_Project.Service.AuthService;
import com.example.Insurance_Aggregator_Project.Service.QuoteService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final QuoteService quoteService;

    @GetMapping()
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body( userRepository.findAll());
    }

}
