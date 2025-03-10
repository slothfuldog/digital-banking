package com.digibank.bank_service.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class LedgerMasterDTO {
    private Long id;
    private String accountCode;
    private String accountType;
    private BigDecimal creditAmt;
    private BigDecimal debitAmt;
    private BigDecimal totalAmt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
