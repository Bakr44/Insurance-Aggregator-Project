package com.example.Insurance_Aggregator_Project.Controller;

import com.example.Insurance_Aggregator_Project.Model.User;
import com.example.Insurance_Aggregator_Project.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class UserController {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/Register")
    public ResponseEntity register(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok().body("User registered successfully");
    }

    @PostMapping("/Login")
    public ResponseEntity login(@RequestBody User user){
        User existingUser =userRepository.findByEmail(user.getEmail());

        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())){

        }
        return ResponseEntity.ok().body("Invalid email or password");
    }

}
