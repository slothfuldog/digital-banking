package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.AccountCodeBase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountCodeBaseRepository extends JpaRepository<AccountCodeBase, String> {
    List<AccountCodeBase> findByCodeType(String codeType);
}
