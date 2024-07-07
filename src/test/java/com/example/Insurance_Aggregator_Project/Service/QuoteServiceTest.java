package com.example.Insurance_Aggregator_Project.Service;

import com.example.Insurance_Aggregator_Project.Model.Quote;
import com.example.Insurance_Aggregator_Project.Model.User;
import com.example.Insurance_Aggregator_Project.Repository.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class QuoteServiceTest {
    @Mock
    private QuoteRepository quoteRepository;

    @Mock
    private UserDetailsServiceImp userDetailsServiceImp;

    @InjectMocks
    private QuoteService quoteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void assignQuoteToUser_UserNotFound() {
        when(userDetailsServiceImp.getUserByEmail(anyString())).thenReturn(Optional.empty());

        ResponseEntity<String> response = quoteService.assignQuoteToUser("test@example.com", 1);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("User not found", response.getBody());
    }

    @Test
    void assignQuoteToUser_QuoteNotFound() {
        User user = new User();
        when(userDetailsServiceImp.getUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(quoteRepository.findByPolicyNumber(anyInt())).thenReturn(null);

        ResponseEntity<String> response = quoteService.assignQuoteToUser("test@example.com", 1);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Policy not found", response.getBody());
    }

    @Test
    void assignQuoteToUser_Success() {
        User user = new User();
        Quote quote = new Quote();
        when(userDetailsServiceImp.getUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(quoteRepository.findByPolicyNumber(anyInt())).thenReturn(quote);

        ResponseEntity<String> response = quoteService.assignQuoteToUser("test@example.com", 1);

        verify(userDetailsServiceImp, times(1)).saveUser(user);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Quote assigned to user successfully", response.getBody());
    }
}
