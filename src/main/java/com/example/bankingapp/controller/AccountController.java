package com.example.bankingapp.controller;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController{

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount (@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Get Account REST API
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> getAccountByID(@PathVariable Long accountNumber){
        AccountDto accountDto = accountService.getAccountById(accountNumber);
        return ResponseEntity.ok(accountDto);
    }

    // Deposit Account REST API
    @PutMapping("/{accountNumber}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long accountNumber,
                                              @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(accountNumber,amount);
        return ResponseEntity.ok(accountDto);
    }

    // Withdraw Account REST API
    @PutMapping("/{accountNumber}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long accountNumber,
                                               @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(accountNumber,amount);
        return ResponseEntity.ok(accountDto);
    }

    // Get All Accounts
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // Delete Account
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountNumber){
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok("Successfully Deleted the Account");
    }


}
