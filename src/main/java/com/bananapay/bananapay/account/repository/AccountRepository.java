package com.bananapay.bananapay.account.repository;

import com.bananapay.bananapay.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
