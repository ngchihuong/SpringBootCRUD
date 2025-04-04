package com.huongag.SpringCRUDAPI.service.impl;

import com.huongag.SpringCRUDAPI.config.BcryptPassword;
import com.huongag.SpringCRUDAPI.dto.AccountDto;
import com.huongag.SpringCRUDAPI.dto.ApiResponseDto;
import com.huongag.SpringCRUDAPI.entity.AccountEntity;
import com.huongag.SpringCRUDAPI.repo.AccountRepository;
import com.huongag.SpringCRUDAPI.service.AccountService;
import com.huongag.SpringCRUDAPI.utils.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository repo;

    @Autowired
    private BcryptPassword encoder;

    public AccountServiceImpl(AccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public ApiResponseDto<AccountDto> createAccount(AccountEntity account) {
        if (repo.existsByEmail(account.getEmail())) {
            return new ApiResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Email is exists!",
                    null
            );
        }

        AccountEntity newAccount = new AccountEntity();
        newAccount.setName(account.getName());
        newAccount.setAge(account.getAge());
        newAccount.setEmail(account.getEmail());
        newAccount.setPassword(encoder.encode(account.getPassword()));
        newAccount.setAddress(account.getAddress());

        AccountEntity savedAccount = repo.save(newAccount);
        AccountDto accountDto = convertToDto(savedAccount);
        return new ApiResponseDto<>(
                HttpStatus.CREATED.value(),
                "Successful!",
                accountDto
        );
    }

    @Override
    public ApiResponseDto<List<AccountDto>> getAllAccounts(int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;

        if (pageSize <= 0 || pageSize <= 0) {
            ApiResponseDto<List<AccountDto>> res = new ApiResponseDto<>();
            Meta meta = new Meta();

            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("Not Found");
            res.setData(List.of());

            return res;
        }

        List<AccountEntity> accounts = repo.getAccountsWithPaginate(pageSize, offset);
        Long total = repo.getTotalCount();
        int totalPages = (int) Math.ceil((double) total / pageSize);

        ApiResponseDto<List<AccountDto>> res = new ApiResponseDto<>();
        Meta meta = new Meta();

        meta.setCurrentPage(currentPage);
        meta.setPageSize(pageSize);
        meta.setTotalPages(totalPages);
        meta.setTotal(total);
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Accounts");
        res.setMeta(meta);
        res.setData(accounts.stream().map(this::convertToDto).toList());
        return res;
    }


    @Override
    public ApiResponseDto<AccountDto> getAccountById(int id) {
        AccountEntity entity = repo.findById(id).orElse(null);
        if (entity == null) {
            return new ApiResponseDto<>(
                    HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    null
            );
        }
        AccountDto accountDto = convertToDto(entity);
        return new ApiResponseDto<>(
                HttpStatus.OK.value(),
                "Account",
                accountDto);
    }

    @Override
    public ApiResponseDto<AccountDto> deleteAccountById(int id) {
        AccountEntity entity = repo.findById(id).orElse(null);
        if (entity == null) {
            return new ApiResponseDto<>(
                    HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    null
            );
        } else {
            repo.deleteById(id);
            AccountDto accountDto = convertToDto(entity);
            return new ApiResponseDto<>(
                    HttpStatus.OK.value(),
                    "Account",
                    accountDto);
        }
    }


    @Override
    public ApiResponseDto<List<AccountDto>> searchAccounts(int currentPage, int pageSize, String keyword) {
        int offset = (currentPage - 1) * pageSize;
        if (pageSize <= 0 || pageSize <= 0) {
            ApiResponseDto<List<AccountDto>> res = new ApiResponseDto<>();
            Meta meta = new Meta();

            res.setStatusCode(HttpStatus.BAD_REQUEST.value());
            res.setMessage("Bad Request!");
            res.setData(List.of());

            return res;
        }

        List<AccountEntity> accounts = repo.searchProducts(pageSize, offset, keyword);
//        int total = repo.getTotalCount();
        Long total = accounts.stream().count();
        int totalPages = (int) Math.ceil((double) total / pageSize);

        ApiResponseDto<List<AccountDto>> res = new ApiResponseDto<>();
        Meta meta = new Meta();

        meta.setCurrentPage(currentPage);
        meta.setPageSize(pageSize);
        meta.setTotalPages(totalPages);
        meta.setTotal(total);

        res.setMeta(meta);
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Accounts");
        res.setData(accounts.stream().map(this::convertToDto).toList());
        return res;
    }

    @Override
    public ApiResponseDto<AccountDto> updatePatial(int id, AccountEntity account) {
        AccountEntity currentUser = repo.findById(id).orElse(null);
        if (currentUser == null) {
            return new ApiResponseDto<>(
                    HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    null
            );
        }

        currentUser.setName(account.getName());
        currentUser.setAge(account.getAge());
        currentUser.setAddress(account.getAddress());

        AccountEntity savedAccount = repo.save(currentUser);
        AccountDto newInforDto = convertToDto(savedAccount);

        return new ApiResponseDto<>(
                HttpStatus.OK.value(),
                "Successful!",
                newInforDto
        );
    }

    @Override
    public ApiResponseDto<AccountDto> updateCreadential(int id, AccountEntity account) {
        AccountEntity currentUser = repo.findById(id).orElse(null);
        if (currentUser == null) {
            return new ApiResponseDto<>(
                    HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    null
            );
        }
//        && repo.existsByEmail((account.getEmail())) != currentUser.getEmail().toString()
        if (repo.existsByEmail(account.getEmail())) {
            return new ApiResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Email is exists!",
                    null
            );
        }
//        newAccount.setPassword(encoder.encode(account.getPassword()));

        currentUser.setEmail(account.getEmail());
        currentUser.setPassword(encoder.encode(account.getPassword()));

        AccountEntity savedAccount = repo.save(currentUser);
        AccountDto newInforDto = convertToDto(savedAccount);

        return new ApiResponseDto<>(
                HttpStatus.OK.value(),
                "Successful!",
                newInforDto
        );
    }

    private AccountDto convertToDto(AccountEntity entity) {
        return new AccountDto(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getAge(),
                entity.getAddress()
        );
    }
}
