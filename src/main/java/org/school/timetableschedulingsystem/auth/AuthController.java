package org.school.timetableschedulingsystem.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/")
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "login/")
    public ResponseEntity<AuthResponse> login(AuthRequest authRequest) {
        return new ResponseEntity<>(authService.login(authRequest), HttpStatus.OK);
    }
}

