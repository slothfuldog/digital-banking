package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.LedgerTransaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface LedgerTransactionRepository extends JpaRepository<LedgerTransaction, Long> {
    List<LedgerTransaction> findByAccountNumber(String accountNumber);
    List<LedgerTransaction> findByGlobalId(String globalId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ledger_transaction(\n"
            + "id, account_number, before_balance, trx_amt,\n"
            + "after_balance, global_id, remark,\n"
            + "created_at, created_by)\n"
            + "VALUES(\n"
            + "(SELECT count(1) + 1 FROM ledger_transaction),:accountNumber, :beforeBalance, :trxAmount, :afterBalance, :globalId, 'Withdrawal', CURRENT_TIMESTAMP, :createdBy\n"
            + ");",
            nativeQuery = true)
    int insertWithdrawLedgerTransaction(@Param("accountNumber") String accountNumber, @Param("beforeBalance")BigDecimal beforeBalance,
                                        @Param("trxAmount") BigDecimal trxAmount, @Param("afterBalance") BigDecimal afterBalance, @Param("globalId") String globalId,
                                        @Param("createdBy") String createdBy);
}
