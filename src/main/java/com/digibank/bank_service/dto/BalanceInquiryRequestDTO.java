package com.digibank.bank_service.dto;

public class BalanceInquiryRequestDTO {
    private String username;
    private String accountNumber;

    // Constructors
    public BalanceInquiryRequestDTO() {}

    public BalanceInquiryRequestDTO(String username, String accountNumber) {
        this.username = username;
        this.accountNumber = accountNumber;
    }

    // Getters and Setters
    public String getUserName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

