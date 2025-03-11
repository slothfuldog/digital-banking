package com.digibank.bank_service.repository;

import com.digibank.bank_service.dto.BalanceInquiryGetDTO;
import com.digibank.bank_service.dto.WithdrawGetDTO;
import com.digibank.bank_service.model.AccountBase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountBaseRepository extends JpaRepository<AccountBase, String> {
    Optional<AccountBase> findByAccountNumber(String accountNumber);
    List<AccountBase> findByStatus(Integer status);

    @Query(value = "SELECT current_balance, bs_balance FROM account_base WHERE user_id = :userId AND account_number = :accountNumber", nativeQuery = true)
    BalanceInquiryGetDTO checkBalance(@Param("userId") Long userId, @Param("accountNumber") String accountNumber);

    @Modifying
    @Transactional
    @Query(value = "UPDATE account_base SET current_balance = current_balance - :trxAmount, bs_balance = bs_balance - :trxAmount WHERE user_id = :userId AND account_number = :accountNumber", nativeQuery = true)
    int withdrawBalance(@Param("trxAmount")BigDecimal trxAmount, @Param("userId") Long userId, @Param("accountNumber") String accountNumber);
}
