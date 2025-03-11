package com.digibank.bank_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WithdrawGetDTO {
    private String username;
    private String accountNumber;
    private BigDecimal trxAmount;

    // Constructors
    public WithdrawGetDTO() {}

    public WithdrawGetDTO(String username, String accountNumber, BigDecimal trxAmount) {
        this.username = username;
        this.accountNumber = accountNumber;
        this.trxAmount = trxAmount;
    }

    // Getters and Setters
    public String getUserName() {
        return username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getTrxAmount(){
        return trxAmount;
    }


}

