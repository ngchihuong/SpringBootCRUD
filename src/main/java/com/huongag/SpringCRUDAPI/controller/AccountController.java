package com.huongag.SpringCRUDAPI.controller;

import com.huongag.SpringCRUDAPI.dto.AccountDto;
import com.huongag.SpringCRUDAPI.dto.ApiPaginateResponse;
import com.huongag.SpringCRUDAPI.dto.ApiResponseDto;
import com.huongag.SpringCRUDAPI.entity.AccountEntity;
import com.huongag.SpringCRUDAPI.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ApiPaginateResponse<List<AccountDto>> getAllAccount(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return accountService.getAllAccounts(currentPage, pageSize);
    }

    @GetMapping("/account/{id}")
    public ApiResponseDto<AccountDto> getAccountById(@PathVariable int id) {
        AccountEntity entity = accountService.getAccountById(id);

        AccountDto accountDto = new AccountDto(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getAge(),
                entity.getAddress()
        );
        if (accountDto != null) {
            return new ApiResponseDto<>(
                    200,
                    "Account"
                    ,
                    accountDto);
        } else {
            return new ApiResponseDto<>(
                    404,
                    "Not Found",
                    null
            );
        }
    }

    @DeleteMapping("/account/{id}")
    public ApiResponseDto<AccountDto> deleteAccount(@PathVariable int id) {
        AccountEntity entity = accountService.getAccountById(id);
        if (entity != null) {
            accountService.deleteAccount(id);
            AccountDto accountDto = new AccountDto(
                    entity.getId(),
                    entity.getName(),
                    entity.getEmail(),
                    entity.getAge(),
                    entity.getAddress()
            );
            return new ApiResponseDto<>(HttpStatus.OK.value(), "Deleted!", accountDto);
        } else {
            return new ApiResponseDto<>(HttpStatus.NOT_FOUND.value(), "Failed to delete!", null);
        }
    }

    @GetMapping("/search")
    public ApiPaginateResponse<List<AccountDto>> searchProducts(
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
