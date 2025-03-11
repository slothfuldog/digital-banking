package com.digibank.bank_service.controller;

import com.digibank.bank_service.dto.BalanceInquiryDTO;
import com.digibank.bank_service.dto.BalanceInquiryRequestDTO;
import com.digibank.bank_service.dto.WithdrawDTO;
import com.digibank.bank_service.dto.WithdrawGetDTO;
import com.digibank.bank_service.service.BalanceService;
import com.digibank.bank_service.service.WithdrawService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController{
    private final WithdrawService withdrawService;

    public TransactionController(WithdrawService withdrawService){
        this.withdrawService = withdrawService;
    }

    @GetMapping("/inquiry")
    public ResponseEntity<String> inquiryUser(){
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawDTO> getBalance(@RequestBody WithdrawGetDTO params){
        WithdrawDTO withdrawDTO = withdrawService.WithdrawBalance(params.getUserName(), params.getAccountNumber(), params.getTrxAmount());
        System.out.println("Current responseCode: " + withdrawDTO.getResponseCode());

        return ResponseEntity.status(withdrawDTO.getResponseCode()).body(withdrawDTO);
    }
}