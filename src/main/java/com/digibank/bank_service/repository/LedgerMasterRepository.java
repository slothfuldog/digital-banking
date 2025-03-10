package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.LedgerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LedgerMasterRepository extends JpaRepository<LedgerMaster, Long> {
    List<LedgerMaster> findByAccountCode(String accountCode);
}
