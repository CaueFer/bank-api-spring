package com.bananapay.bananapay.account.controller;


import com.bananapay.bananapay.account.dto.request.CreateAccountDTO;
import com.bananapay.bananapay.account.dto.response.ResponseMessage;
import com.bananapay.bananapay.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<ResponseMessage> createAccount(@RequestBody @Valid CreateAccountDTO newAcc) {
        String response = this.accountService.createAccount(newAcc);
        return ResponseEntity.ok(new ResponseMessage("User created successfully."));
    }
}
