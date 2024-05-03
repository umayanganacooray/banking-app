package com.example.bankingapp.service.Impl;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.mapper.AccountMapper;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount); //we have to pass savedAccount otherwise it is wrong because the accountNumber is added when we save the account in accountRepository.
    }

    @Override
    public AccountDto getAccountById(Long accountNumber) {
        Account account = accountRepository
                .findById(accountNumber)
                .orElseThrow(()->new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long accountNumber, double amount) {
        Account account = accountRepository
                .findById(accountNumber)
                .orElseThrow(()->new RuntimeException("Account does not exist"));
        account.setBalance(account.getBalance()+amount);
        return AccountMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public AccountDto withdraw(Long accountNumber, double amount) {
        Account account = accountRepository
                .findById(accountNumber)
                .orElseThrow(()-> new RuntimeException("Account does not exit"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount");
        }
        account.setBalance(account.getBalance()-amount);
        return AccountMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts =  accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).
                collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long accountNumber) {
        Account account = accountRepository
                .findById(accountNumber)
                .orElseThrow(()-> new RuntimeException("Account does not exit"));
        accountRepository.deleteById(accountNumber);
    }

}