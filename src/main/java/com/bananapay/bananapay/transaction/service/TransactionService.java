package com.bananapay.bananapay.transaction.service;

import com.bananapay.bananapay.account.domain.model.Account;
import com.bananapay.bananapay.account.repository.AccountRepository;
import com.bananapay.bananapay.transaction.domain.model.Transaction;
import com.bananapay.bananapay.transaction.repository.TransactionRepository;
import com.bananapay.bananapay.transaction.domain.dto.request.CreateTransactionDTO;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public void createTransaction(CreateTransactionDTO newTransaction) {
        try {
            Account origin = this.accountRepository.findById(newTransaction.originId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Origin Account not founded."));

            Account receiver = this.accountRepository.findById(newTransaction.receiverId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver Account not founded"));

            if (origin.getBalance() < newTransaction.quantity()) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Insufficient funds in origin account.");
            }

            origin.setBalance(origin.getBalance() - newTransaction.quantity());
            receiver.setBalance(receiver.getBalance() + newTransaction.quantity());
            this.accountRepository.save(origin);
            this.accountRepository.save(receiver);

            Transaction transaction = new Transaction();
            transaction.setOrigin(origin);
            transaction.setReceiver(receiver);
            transaction.setQuantity(newTransaction.quantity());
            transaction.setPaymentType(newTransaction.paymentType());

            this.transactionRepository.save(transaction);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro no servidor.");
        }
    }

    public List<Transaction> getTransactionsByAccount(UUID accountId) {
        try {
            return this.transactionRepository.findAllByOriginIdOrReceiverId(accountId, accountId);
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro no servidor.");
        }
    }
}
