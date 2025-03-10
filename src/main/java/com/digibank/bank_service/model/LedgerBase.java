package com.digibank.bank_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ledger_base")
public class LedgerBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String referenceNo;

    @ManyToOne
    @JoinColumn(name = "account_code", referencedColumnName = "accountCode")
    private AccountCodeBase accountCode;

    private BigDecimal trxAmount;
    private String globalId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
}
