package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.AccountBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountBaseRepository extends JpaRepository<AccountBase, String> {
    Optional<AccountBase> findByAccountNumber(String accountNumber);
    List<AccountBase> findByStatus(Integer status);

    @Query(value = "SELECT current_balance, bs_balance FROM account_base WHERE user_id = :userId AND account_number = :accountNumber", nativeQuery = true)
    Object[] checkBalance(@Param("userId") Long userId, @Param("accountNumber") String accountNumber);
}
