package com.huongag.SpringCRUDAPI.service.auth;

import com.huongag.SpringCRUDAPI.entity.AccountEntity;
import com.huongag.SpringCRUDAPI.repo.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;

    public UserDetailServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AccountEntity> accountOptional = accountRepository.findByEmail(username);

        if (accountOptional.isEmpty()) {
            throw new UsernameNotFoundException("Account not with email: " + username);
        }

        AccountEntity account = accountOptional.get();

        return User.builder()
                .username(account.getEmail())
                .password(account.getPassword())
//                .roles(account.getRole().stream()
//                .map(role -> role.getName().name().replace("ROLE_", "")) // Loại bỏ "ROLE_"
//                    .toArray(String[]::new))) // chua co role
//                .roles("USERS")
                .build();
    }
}
