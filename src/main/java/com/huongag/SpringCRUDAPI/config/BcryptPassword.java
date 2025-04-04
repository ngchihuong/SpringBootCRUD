package com.huongag.SpringCRUDAPI.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptPassword {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String encode(String password) {
        return encoder.encode(password);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
