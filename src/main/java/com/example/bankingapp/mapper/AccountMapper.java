package com.example.bankingapp.mapper;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getAccountNumber(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public  static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getAccountNumber(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }

}

