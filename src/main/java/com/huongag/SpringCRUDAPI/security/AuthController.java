package com.huongag.SpringCRUDAPI.security;

import com.huongag.SpringCRUDAPI.dto.auth.JwtResponse;
import com.huongag.SpringCRUDAPI.dto.auth.LoginRequest;
import com.huongag.SpringCRUDAPI.entity.AccountEntity;
import com.huongag.SpringCRUDAPI.service.auth.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(loginRequest,response));
    }

    @PostMapping("/register")
    public ResponseEntity<?> resgister(@RequestBody AccountEntity account) {
        return ResponseEntity.ok(authService.register(account));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout (@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return ResponseEntity.ok().body("Logged out successfully!");
    }
}
