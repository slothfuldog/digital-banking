package com.digibank.bank_service.service;

import com.digibank.bank_service.dto.BalanceInquiryDTO;
import com.digibank.bank_service.dto.BalanceInquiryGetDTO;
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

    @Autowired
    public BalanceService(AccountBaseRepository accountBaseRepository, UserBaseRepository userBaseRepository){
        this.accountBaseRepository = accountBaseRepository;
        this.userBaseRepository = userBaseRepository;
    }

    public BalanceInquiryDTO balanceInquiry(String username, String accountNumber){
        System.out.println("================== Balance Inquiry Service Start ===================");
        System.out.println("username           = [" + username + "]"     );
        System.out.println("accountNumber      = [" + accountNumber + "]");
        Optional<Long> userIdOptional = userBaseRepository.findIdByUsername(username);
        if(userIdOptional.isEmpty()){
            System.out.println("ERROR BalanceService:userIdOptional: NOT FOUND!");
            System.out.println("==================  Balance Inquiry Service End  ===================");

            return new BalanceInquiryDTO(new BigDecimal(0), new BigDecimal(0), "Data Not Found", 404);
        }

        Long userId = userIdOptional.get();

        System.out.println("userId             = [" + userId + "]"     );

        BalanceInquiryGetDTO balanceFetch = accountBaseRepository.checkBalance(userId, accountNumber);

        if(balanceFetch == null){
            System.out.println("ERROR BalanceService:BalanceFetch: NOT FOUND!");
            System.out.println("==================  Balance Inquiry Service End  ===================");
            return new BalanceInquiryDTO(new BigDecimal(0), new BigDecimal(0), "Data Not Found", 404);
        }

        BigDecimal bsBalance = (BigDecimal) balanceFetch.getBsBalance();
        BigDecimal currentBalance = (BigDecimal) balanceFetch.getCurrentBalance();

        System.out.println("currentBalance =    [" + currentBalance + "]");
        System.out.println("bsBalance      =    [" + bsBalance + "]");

        System.out.println("==================  Balance Inquiry Service End  ===================");

        return new BalanceInquiryDTO(currentBalance, bsBalance, "", 200);
    }
}
