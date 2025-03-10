package com.digibank.bank_service.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class LedgerBaseDTO {
    private Long id;
    private String referenceNo;
    private String accountCode;
    private BigDecimal trxAmount;
    private String globalId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
