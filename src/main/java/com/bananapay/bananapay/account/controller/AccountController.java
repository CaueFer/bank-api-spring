package com.bananapay.bananapay.account.controller;


import com.bananapay.bananapay.account.domain.dto.request.CreateAccountDTO;
import com.bananapay.bananapay.account.domain.dto.response.AccountResponse;
import com.bananapay.bananapay.account.domain.dto.response.ResponseMessage;
import com.bananapay.bananapay.account.service.AccountService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/account")
@Validated
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createAccount(@RequestBody @Valid CreateAccountDTO newAcc) {
        this.accountService.createAccount(newAcc);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("User created successfully."));
    }

    @GetMapping("/{ownerCpf}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable
                                                      @NotBlank(message = "Invalid CPF")
                                                      @Size(min = 11, max = 11, message = "CPF must have 11 digits.")
                                                      String ownerCpf) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findAccountByCpf(ownerCpf));
    }
}
