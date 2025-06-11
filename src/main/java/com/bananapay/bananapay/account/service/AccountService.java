package com.bananapay.bananapay.account.service;

import com.bananapay.bananapay.account.domain.dto.response.AccountResponse;
import com.bananapay.bananapay.account.domain.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import com.bananapay.bananapay.account.repository.AccountRepository;
import com.bananapay.bananapay.account.domain.dto.request.CreateAccountDTO;

import java.util.Optional;

@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    public void createAccount(CreateAccountDTO newAcc) {
        try {
            Optional<Account> existedAcc = this.accountRepository.findByOwnerCpf(newAcc.getOwnerCpf());
            if (existedAcc.isPresent())
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Account with this CPF already exists.");

            Account account = new Account();
            account.setOwnerName(newAcc.getOwnerName());
            account.setOwnerCpf(newAcc.getOwnerCpf());

            this.accountRepository.save(account);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating account.");
        }
    }

    public AccountResponse findAccountByCpf(String ownerCpf) {
        try {
            Account account = this.accountRepository.findByOwnerCpf(ownerCpf).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CPF not founded."));

            return new AccountResponse(account.getId(), account.getOwnerName(), account.getOwnerCpf());
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error finding account.");
        }
    }

}
