package com.bananapay.bananapay.account.service;

import com.bananapay.bananapay.account.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import com.bananapay.bananapay.account.repository.AccountRepository;
import com.bananapay.bananapay.account.dto.request.CreateAccountDTO;

import java.util.Optional;

@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    public String createAccount(CreateAccountDTO newAcc) {
        try {
            Optional<Account> existedAcc = this.accountRepository.findByOwnerCpf(newAcc.getOwnerCpf());
            if (existedAcc.isPresent())
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Account with this CPF already exists.");

            Account account = new Account();
            account.setOwnerName(newAcc.getOwnerName());
            account.setOwnerCpf(newAcc.getOwnerCpf());

            this.accountRepository.save(account);
            return "Account created.";
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating account.");
        }
    }

}
