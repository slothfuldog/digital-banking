package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.AccountCodeBase;
import com.digibank.bank_service.model.LedgerMaster;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface LedgerMasterRepository extends JpaRepository<LedgerMaster, Long> {
    List<LedgerMaster> findByAccountCode(String accountCode);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ledger_master\n"
            + "SET\n"
            + "account_type = CASE\n"
            + "WHEN account_code LIKE '1%' OR account_code LIKE '2%' THEN 'B'\n"
            + "WHEN account_code LIKE '3%' OR account_code LIKE '4%' OR account_code LIKE '5%' THEN 'P'\n"
            + "END,\n"
            + "debit_amt = CASE\n"
            + "WHEN account_code LIKE '2%' OR account_code LIKE '3%' OR account_code LIKE '4%' THEN debit_amt + :trxAmount\n"
            + "ELSE debit_amt\n"
            + "END,\n"
            + "credit_amt = CASE\n"
            + "WHEN account_code LIKE '1%' OR account_code LIKE '5%' THEN credit_amt + :trxAmount\n"
            + "ELSE credit_amt\n"
            + "END,"
            + "total_amt = total_amt - :trxAmount,\n"
            + "updated_at = CURRENT_TIMESTAMP\n"
            + "WHERE account_code IN ('10908213', '24033123');",
            nativeQuery = true)
    int updateWithdrawLedgerMaster(@Param("trxAmount")BigDecimal trxAmount);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ledger_master\n"
            + "SET\n"
            + "account_type = CASE\n"
            + "WHEN account_code LIKE '1%' OR account_code LIKE '2%' THEN 'B'\n"
            + "WHEN account_code LIKE '3%' OR account_code LIKE '4%' OR account_code LIKE '5%' THEN 'P'\n"
            + "END,\n"
            + "debit_amt = CASE\n"
            + "WHEN account_code = '24033123' THEN debit_amt + :trxAmount\n"
            + "ELSE debit_amt\n"
            + "END,\n"
            + "credit_amt = CASE\n"
            + "WHEN account_code = '28942001' THEN credit_amt + :trxAmount\n"
            + "ELSE credit_amt\n"
            + "END,"
            + "total_amt = CASE\n"
            + "WHEN account_code = '28942001' THEN total_amt + :trxAmount\n"
            + "WHEN account_code = '24033123' THEN total_amt - :trxAmount\n"
            + "END,"
            + "updated_at = CURRENT_TIMESTAMP\n"
            + "WHERE account_code IN ('28942001', '24033123');",
            nativeQuery = true)
    int updateWithdrawFee(@Param("trxAmount")BigDecimal trxAmount);
}
