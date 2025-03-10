package com.digibank.bank_service.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController{
    @GetMapping("/inquiry")
    public ResponseEntity<String> inquiryUser(){
        return ResponseEntity.ok("OKe");
    }
}