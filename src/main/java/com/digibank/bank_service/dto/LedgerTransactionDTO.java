package com.digibank.bank_service.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class LedgerTransactionDTO {
    private Long id;
    private String accountNumber;
    private BigDecimal beforeBalance;
    private BigDecimal trxAmt;
    private BigDecimal afterBalance;
    private String globalId;
    private String remark;
    private LocalDateTime createdAt;
    private String createdBy;
}
