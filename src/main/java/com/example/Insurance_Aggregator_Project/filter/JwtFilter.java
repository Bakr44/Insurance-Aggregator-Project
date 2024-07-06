package com.example.Insurance_Aggregator_Project.filter;

import com.example.Insurance_Aggregator_Project.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        final String bearer = "Bearer ";
        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith(bearer)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwtToken = authHeader.substring(bearer.length());
        final String userEmail = jwtService.extractUserName(jwtToken);

        if(org.apache.commons.lang3.StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(jwtToken,userDetails)){
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                      userDetails, null, userDetails.getAuthorities()
                );

                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }

            filterChain.doFilter(request, response);

    }
}
