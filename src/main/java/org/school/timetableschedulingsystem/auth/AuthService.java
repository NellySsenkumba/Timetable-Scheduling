package org.school.timetableschedulingsystem.auth;

import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.config.JwtService;
import org.school.timetableschedulingsystem.models.database.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest authRequest) {
         authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.username(),
                        authRequest.password()
                )
        );
        String username = authRequest.username();
        UserDetails user = userDetailsService.loadUserByUsername(username);
        return new AuthResponse(jwtService.generateToken(user));
    }
}
