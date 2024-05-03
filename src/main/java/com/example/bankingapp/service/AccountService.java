package com.example.bankingapp.service;

import com.example.bankingapp.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long accountNumber);
    AccountDto deposit(Long accountNumber, double amount);
    AccountDto withdraw(Long accountNumber, double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long accountNumber);
}
