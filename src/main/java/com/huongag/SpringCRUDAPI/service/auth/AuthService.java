package com.huongag.SpringCRUDAPI.service.auth;

import com.huongag.SpringCRUDAPI.dto.AccountDto;
import com.huongag.SpringCRUDAPI.dto.auth.JwtResponse;
import com.huongag.SpringCRUDAPI.dto.auth.LoginRequest;
import com.huongag.SpringCRUDAPI.entity.AccountEntity;
import com.huongag.SpringCRUDAPI.repo.AccountRepository;
import com.huongag.SpringCRUDAPI.security.JwtUtils;
import com.huongag.SpringCRUDAPI.service.AccountService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AccountService accountService;

    public JwtResponse jwtResponse;

    public JwtResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        Cookie cookie = new Cookie("token", jwt.toString().trim());

        //add a cookie to the response
        response.addCookie(cookie);

        AccountEntity account = accountRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //get user roles
//        Set<String> roles = account.getRole()
        return new JwtResponse(jwt, account);
    }

    @Transactional
    public AccountDto register(AccountEntity account) {
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new RuntimeException("Username is already taken!");
        }
        AccountEntity accountConvert = new AccountEntity();
        accountConvert.setName(account.getName());
        accountConvert.setEmail(account.getEmail());
        accountConvert.setPassword(passwordEncoder.encode(account.getPassword()));
        accountConvert.setAge(account.getAge());
        accountConvert.setAddress(account.getAddress());
        accountConvert.setRole(account.getRole());

        AccountEntity savedAccount = accountRepository.save(accountConvert);

        return accountService.getAccountById(savedAccount.getId()).getData();
    }
    public void logout(String token ) {
        String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        jwtUtils.invalidateToken(actualToken);
        SecurityContextHolder.clearContext();
    }
}
