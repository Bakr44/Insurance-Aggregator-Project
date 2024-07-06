package com.example.Insurance_Aggregator_Project.Service;

import com.example.Insurance_Aggregator_Project.Model.User;
import com.example.Insurance_Aggregator_Project.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("email or password is not correct!!"));
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();

    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}
