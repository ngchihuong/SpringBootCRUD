package com.huongag.SpringCRUDAPI.service.impl;

import com.huongag.SpringCRUDAPI.dto.AccountDto;
import com.huongag.SpringCRUDAPI.dto.ApiPaginateResponse;
import com.huongag.SpringCRUDAPI.dto.ApiResponseDto;
import com.huongag.SpringCRUDAPI.entity.AccountEntity;
import com.huongag.SpringCRUDAPI.repo.AccountRepository;
import com.huongag.SpringCRUDAPI.service.AccountService;
import com.huongag.SpringCRUDAPI.utils.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AccountServiceImpl(AccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public ApiResponseDto<AccountDto> createAccount(AccountEntity account) {
        if (repo.existsByEmail(account.getEmail())) {
            return new ApiResponseDto<>(
                    400,
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
                201,
                "Successful!",
                accountDto
        );
    }

    @Override
    public ApiPaginateResponse<List<AccountDto>> getAllAccounts(int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;

        if (pageSize <= 0 || pageSize <= 0) {
            ApiPaginateResponse<List<AccountDto>> res = new ApiPaginateResponse<>();
            Meta meta = new Meta();
            meta.setCurrentPage(0);
            meta.setPageSize(0);
            meta.setTotal(0);
            meta.setTotalPages(0);

            res.setMeta(meta);
            res.setResult(List.of());

            return res;
        }

        List<AccountEntity> accounts = repo.getAccountsWithPaginate(pageSize, offset);
        int total = repo.getTotalCount();
        int totalPages = (int) Math.ceil((double) total / pageSize);

        ApiPaginateResponse<List<AccountDto>> res = new ApiPaginateResponse<>();
        Meta meta = new Meta();

        meta.setCurrentPage(currentPage);
        meta.setPageSize(pageSize);
        meta.setTotalPages(totalPages);
        meta.setTotal(total);

        res.setMeta(meta);
        res.setResult(accounts.stream().map(this::convertToDto).toList());
        return res;
    }

    @Override
    public AccountEntity getAccountById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteAccount(int id) {
        repo.deleteById(id);
    }

    @Override
    public ApiPaginateResponse<List<AccountDto>> searchAccounts(int currentPage, int pageSize, String keyword) {
        int offset = (currentPage - 1) * pageSize;
        if (pageSize <= 0 || pageSize <= 0) {
            ApiPaginateResponse<List<AccountDto>> res = new ApiPaginateResponse<>();
            Meta meta = new Meta();
            meta.setCurrentPage(0);
            meta.setPageSize(0);
            meta.setTotal(0);
            meta.setTotalPages(0);

            res.setMeta(meta);
            res.setResult(List.of());

            return res;
        }

        List<AccountEntity> accounts = repo.searchProducts(pageSize, offset, keyword);
//        int total = repo.getTotalCount();
        int total = accounts.size();
        int totalPages = (int) Math.ceil((double) total / pageSize);

        ApiPaginateResponse<List<AccountDto>> res = new ApiPaginateResponse<>();
        Meta meta = new Meta();

        meta.setCurrentPage(currentPage);
        meta.setPageSize(pageSize);
        meta.setTotalPages(totalPages);
        meta.setTotal(total);

        res.setMeta(meta);
        res.setResult(accounts.stream().map(this::convertToDto).toList());
        return res;
    }

    @Override
    public ApiResponseDto<AccountDto> updatePatial(int id, AccountEntity account) {
        AccountEntity acc = new AccountEntity();
        acc.setName(account.getName());
        acc.setAge(account.getAge());
        acc.setAddress(account.getAddress());

        AccountEntity currentUser = repo.findById(id).orElse(null);
        if (currentUser == null) {
            return new ApiResponseDto<>(
                    HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    null
            );
        }

        currentUser.setName(acc.getName());
        currentUser.setAge(acc.getAge());
        currentUser.setAddress(acc.getAddress());

        AccountEntity savedAccount = repo.save(currentUser);
        AccountDto newInforDto = convertToDto(savedAccount);

        return new ApiResponseDto<>(
                200,
                "Successful!",
                newInforDto
        );
    }

    @Override
    public ApiResponseDto<AccountDto> updateCreadential(int id, AccountEntity account) {
        AccountEntity acc = new AccountEntity();
        acc.setEmail(account.getEmail());
        acc.setPassword(account.getPassword());

        AccountEntity currentUser = repo.findById(id).orElse(null);
        if (currentUser == null) {
            return new ApiResponseDto<>(
                    HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    null
            );
        }
//        newAccount.setPassword(encoder.encode(account.getPassword()));

        currentUser.setEmail(acc.getEmail());
        currentUser.setPassword(encoder.encode(account.getPassword()));

        AccountEntity savedAccount = repo.save(currentUser);
        AccountDto newInforDto = convertToDto(savedAccount);

        return new ApiResponseDto<>(
                200,
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
