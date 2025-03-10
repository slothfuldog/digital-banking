package com.digibank.bank_service.service;

import com.digibank.bank_service.dto.BalanceInquiryDTO;
import com.digibank.bank_service.repository.AccountBaseRepository;
import com.digibank.bank_service.repository.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BalanceService {
    private final AccountBaseRepository accountBaseRepository;
    private final UserBaseRepository userBaseRepository;
    private final BigDecimal currentBalance;
    private final BigDecimal bsBalance;

    @Autowired
    public BalanceService(AccountBaseRepository accountBaseRepository, UserBaseRepository userBaseRepository, BigDecimal currentBalance,
    BigDecimal bsBalance){
        this.accountBaseRepository = accountBaseRepository;
        this.userBaseRepository = userBaseRepository;
        this.currentBalance = currentBalance;
        this.bsBalance = bsBalance;
    }

    public BalanceInquiryDTO balanceInquiry(String username, String accountNumber){
        Optional<Long> userIdOptional = userBaseRepository.findIdByUsername(username);
        if(userIdOptional.isEmpty()){
            return null;
        }

        Long userId = userIdOptional.get();

        Object[] balanceFetch = accountBaseRepository.checkBalance(userId, accountNumber);

        if(balanceFetch == null || balanceFetch.length < 2){
            return null;
        }

        BigDecimal currentBalance = (BigDecimal) balanceFetch[0];
        BigDecimal bsBalance = (BigDecimal) balanceFetch[1];

        return new BalanceInquiryDTO(currentBalance, bsBalance);
    }
}
