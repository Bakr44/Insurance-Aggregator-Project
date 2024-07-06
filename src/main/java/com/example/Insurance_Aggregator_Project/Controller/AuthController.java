package com.example.Insurance_Aggregator_Project.Controller;

import com.example.Insurance_Aggregator_Project.DTO.JwtAuthResponse;
import com.example.Insurance_Aggregator_Project.Model.User;
import com.example.Insurance_Aggregator_Project.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        return ResponseEntity.ok(authService.register(user.getEmail(),user.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody User user){

        return ResponseEntity.ok(authService.login(user.getEmail(), user.getPassword()));
    }

}
