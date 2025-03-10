package com.digibank.bank_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class BalanceInquiryDTO {
    private final BigDecimal currentBalance;
    private final BigDecimal bsBalance;

    public BalanceInquiryDTO(BigDecimal currentBalance, BigDecimal bsBalance) {
        this.currentBalance = currentBalance;
        this.bsBalance = bsBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public BigDecimal getBsBalance() {
        return bsBalance;
    }
}

