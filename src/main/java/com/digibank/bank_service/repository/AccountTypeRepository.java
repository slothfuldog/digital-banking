package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    Optional<AccountType> findById(Long id);
}
