package com.example.Insurance_Aggregator_Project.Service;

import com.example.Insurance_Aggregator_Project.Advise.ApiException;
import com.example.Insurance_Aggregator_Project.DTO.JwtAuthResponse;
import com.example.Insurance_Aggregator_Project.Model.Quote;
import com.example.Insurance_Aggregator_Project.Model.User;
import com.example.Insurance_Aggregator_Project.Repository.QuoteRepository;
import com.example.Insurance_Aggregator_Project.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final QuoteRepository quoteRepository;

    private final JwtService jwtService;

    public String register(String email, String password){
        User existingUser= userRepository.findByEmail(email).orElse(null);

        if (existingUser != null) {
            throw new ApiException("Email already in use");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(newUser);

        return "User registered successfully";

    }

    public JwtAuthResponse login(String email, String password){
      UserDetails userDetails = userDetailsService.loadUserByUsername(email);
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));

        String token = jwtService.generateToken(userDetails);
        return new JwtAuthResponse(token);
    }


}
