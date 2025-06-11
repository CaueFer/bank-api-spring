package com.bananapay.bananapay.transaction.repository;

import com.bananapay.bananapay.transaction.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findAllByOriginIdOrReceiverId(UUID originId, UUID receiverId);
}
