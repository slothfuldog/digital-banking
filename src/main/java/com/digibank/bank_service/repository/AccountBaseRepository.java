package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.AccountBase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AccountBaseRepository extends JpaRepository<AccountBase, String> {
    Optional<AccountBase> findByUserId(Long userId);
    List<AccountBase> findByStatus(Integer status);
}
