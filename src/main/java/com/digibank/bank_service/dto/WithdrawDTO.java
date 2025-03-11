package com.digibank.bank_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class WithdrawDTO {
    private final BigDecimal currentBalance;
    private final BigDecimal bsBalance;
    private final String errorMessage;
    private final int responseCode;

    public WithdrawDTO(BigDecimal currentBalance, BigDecimal bsBalance,
                             String errorMessage, int responseCode) {
        this.currentBalance = currentBalance;
        this.bsBalance = bsBalance;
        this.errorMessage = errorMessage;
        this.responseCode = responseCode;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public BigDecimal getBsBalance() {
        return bsBalance;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
    public int getResponseCode(){
        return responseCode;
    }
}

