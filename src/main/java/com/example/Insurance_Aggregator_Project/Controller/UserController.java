package com.example.Insurance_Aggregator_Project.Controller;

import com.example.Insurance_Aggregator_Project.Model.User;
import com.example.Insurance_Aggregator_Project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body( userRepository.findAll());
    }

}
