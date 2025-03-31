package com.huongag.SpringCRUDAPI.service;

import com.huongag.SpringCRUDAPI.dto.AccountDto;
import com.huongag.SpringCRUDAPI.dto.ApiPaginateResponse;
import com.huongag.SpringCRUDAPI.dto.ApiResponseDto;
import com.huongag.SpringCRUDAPI.entity.AccountEntity;

import java.util.List;

public interface AccountService {
    ApiResponseDto<AccountDto> createAccount(AccountEntity account);

    ApiPaginateResponse<List<AccountDto>> getAllAccounts(int currentPage, int pageSize);

    AccountEntity getAccountById(int id);

    void deleteAccount(int id);

    ApiPaginateResponse<List<AccountDto>> searchAccounts(int currentPage, int pageSize, String keyword);

    ApiResponseDto<AccountDto> updatePatial(int id, AccountEntity account);

    ApiResponseDto<AccountDto> updateCreadential(int id, AccountEntity account);
}
