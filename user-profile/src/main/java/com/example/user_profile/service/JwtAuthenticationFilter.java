package com.example.user_profile.service;

import com.example.user_profile.exception.UserNotFoundException;
import com.example.user_profile.model.User;
import com.example.user_profile.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String username;

        //Check if Authorization header is present and starts with "Bearer "
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Extract JWT token from header
        jwtToken = authHeader.substring(7);
        username = jwtService.extractUsername(jwtToken);

        //check if we have a username and no authentication exists yet
        /*if(fullName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //Get the user details from database
            User userDetails = userRepository.findByFullName(fullName)
                    .orElseThrow(() -> new UserNotFoundException("User not found.."));

            //Validate the token
            if(jwtService.isTokenValid(jwtToken, userDetails)) {
                //Create the authentication with user roles
                List<SimpleGrantedAuthority>
            }
        }*/
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            /*var userDetails = userRepository.findByFullName(fullName)
                    .orElseThrow(() -> new UserNotFoundException("User not found.."));*/
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
