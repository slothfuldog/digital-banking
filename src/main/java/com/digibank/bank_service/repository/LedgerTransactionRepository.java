package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.LedgerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LedgerTransactionRepository extends JpaRepository<LedgerTransaction, Long> {
    List<LedgerTransaction> findByAccountNumber(String accountNumber);
    List<LedgerTransaction> findByGlobalId(String globalId);
}
