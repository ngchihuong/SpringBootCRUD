package com.huongag.SpringCRUDAPI.controller;

import com.huongag.SpringCRUDAPI.dto.AccountDto;
import com.huongag.SpringCRUDAPI.dto.ApiResponseDto;
import com.huongag.SpringCRUDAPI.entity.AccountEntity;
import com.huongag.SpringCRUDAPI.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        super();
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ApiResponseDto<AccountDto> createAccount(@RequestBody AccountEntity account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/accounts")
    public ApiResponseDto<List<AccountDto>> getAllAccount(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return accountService.getAllAccounts(currentPage, pageSize);
    }

    @GetMapping("/{id}")
    public ApiResponseDto<AccountDto> getAccountById(@PathVariable int id) {
        return accountService.getAccountById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponseDto<AccountDto> deleteAccountById(@PathVariable int id) {
        return accountService.deleteAccountById(id);
    }

    @GetMapping("/search")
    public ApiResponseDto<List<AccountDto>> searchProducts(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "10") int pageSize,
            String keyword) {
        return accountService.searchAccounts(currentPage, pageSize, keyword);
    }

    @PutMapping("/account-patial/{id}")
    public ApiResponseDto<AccountDto> updatePatial(@PathVariable int id,
                                                   @RequestBody AccountEntity account) {
        return accountService.updatePatial(id, account);
    }

    @PutMapping("/account-credential/{id}")
    public ApiResponseDto<AccountDto> updateCreadential(@PathVariable int id,
                                                        @RequestBody AccountEntity account
    ) {
        return accountService.updateCreadential(id, account);
    }
}
