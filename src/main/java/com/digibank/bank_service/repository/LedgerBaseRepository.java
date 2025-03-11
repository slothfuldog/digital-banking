package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.AccountCodeBase;
import com.digibank.bank_service.model.LedgerBase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface LedgerBaseRepository extends JpaRepository<LedgerBase, Long> {
    List<LedgerBase> findByReferenceNo(String referenceNo);
    List<LedgerBase> findByAccountCode(String accountCode);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ledger_base (id, reference_no,account_code, trx_amount, global_id, created_at, updated_at) \n"
            + "VALUES ((SELECT count(1) + 1 FROM ledger_base), :accountNumber,:accountCode, :trxAmount, :globalId, CURRENT_DATE, CURRENT_DATE)", nativeQuery = true)
    int insertWithdrawBase(@Param("accountNumber") String accountNumber, @Param("accountCode") String accountCode, @Param("trxAmount")BigDecimal trxAmount
    , @Param("globalId") String globalId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ledger_base\n"
            + "SET updated_at = CURRENT_DATE, trx_amount = trx_amount - :trxAmount\n"
            + "WHERE account_code = :accountCode\n"
            + "AND reference_no   = :accountNumber;",
            nativeQuery = true)
    int updateWithdrawBase(@Param("trxAmount") BigDecimal trxAmount,@Param("accountCode") String accountCode, @Param("accountNumber") String accountNumber);

    @Query(value = "SELECT count(1) FROM ledger_base WHERE account_code = :accountCode AND reference_no = :accountNumber", nativeQuery = true)
    int checkIsExist(@Param("accountCode") String accountCode, @Param("accountNumber") String accountNumber);
}
