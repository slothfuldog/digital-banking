package com.digibank.bank_service.controller;

import com.digibank.bank_service.dto.BalanceInquiryDTO;
import com.digibank.bank_service.dto.BalanceInquiryRequestDTO;
import com.digibank.bank_service.service.BalanceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController{
    private final BalanceService balanceService;

    public UserController(BalanceService balanceService){
        this.balanceService = balanceService;
    }

    @GetMapping("/inquiry")
    public ResponseEntity<String> inquiryUser(){
        return ResponseEntity.ok("OKe");
    }

    @GetMapping("/balance-inquiry")
    public ResponseEntity<BalanceInquiryDTO> getBalance(@RequestBody BalanceInquiryRequestDTO requestDTO){
        BalanceInquiryDTO balanceDTO = balanceService.balanceInquiry(requestDTO.getUserName(), requestDTO.getUserName());

        if(balanceDTO == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(balanceDTO);
    }
}