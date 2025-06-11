package com.bananapay.bananapay.transaction.controller;

import com.bananapay.bananapay.account.domain.dto.response.ResponseMessage;
import com.bananapay.bananapay.transaction.domain.dto.request.CreateTransactionDTO;
import com.bananapay.bananapay.transaction.domain.model.Transaction;
import com.bananapay.bananapay.transaction.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@Validated
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccount(@PathVariable
                                                                      @NotNull(message = "Invalid Account Id")
                                                                      UUID accountId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.transactionService.getTransactionsByAccount(accountId));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createTransaction(@RequestBody @Valid CreateTransactionDTO newTransaction) {
        this.transactionService.createTransaction(newTransaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Transaction Created Successfully."));
    }

}
