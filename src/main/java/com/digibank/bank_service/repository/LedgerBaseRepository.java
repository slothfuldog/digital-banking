package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.LedgerBase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LedgerBaseRepository extends JpaRepository<LedgerBase, Long> {
    List<LedgerBase> findByReferenceNo(String referenceNo);
    List<LedgerBase> findByAccountCode(String accountCode);
}
