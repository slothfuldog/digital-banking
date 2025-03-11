package com.digibank.bank_service.service;

import com.digibank.bank_service.dto.BalanceInquiryDTO;
import com.digibank.bank_service.dto.BalanceInquiryGetDTO;
import com.digibank.bank_service.dto.WithdrawDTO;
import com.digibank.bank_service.repository.*;
import com.digibank.bank_service.util.CommonUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WithdrawService {
    private final AccountBaseRepository accountBaseRepository;
    private final UserBaseRepository userBaseRepository;
    private final LedgerBaseRepository ledgerBaseRepository;
    private final LedgerMasterRepository ledgerMasterRepository;
    private final LedgerTransactionRepository ledgerTransactionRepository;

    @Autowired
    public WithdrawService(AccountBaseRepository accountBaseRepository, UserBaseRepository userBaseRepository,
                           LedgerBaseRepository ledgerBaseRepository, LedgerMasterRepository ledgerMasterRepository,
                           LedgerTransactionRepository ledgerTransactionRepository){
        this.accountBaseRepository = accountBaseRepository;
        this.userBaseRepository = userBaseRepository;
        this.ledgerBaseRepository = ledgerBaseRepository;
        this.ledgerMasterRepository = ledgerMasterRepository;
        this.ledgerTransactionRepository = ledgerTransactionRepository;
    }

    public WithdrawDTO WithdrawBalance(String username, String accountNumber, BigDecimal trxAmount){
        System.out.println("================== Withdraw Service Start ===================");
        System.out.println("username           = [" + username + "]"     );
        System.out.println("accountNumber      = [" + accountNumber + "]");
        System.out.println("trxAmount          = [" + trxAmount + "]"    );

        if(trxAmount.compareTo(new BigDecimal(10000)) < 0){
            System.out.println("ERROR WithdrawService:CompareTrxAmount: minimum withdrawal is 10.000!");
            System.out.println("==================  Balance Inquiry Service End  ===================");

            return new WithdrawDTO(new BigDecimal(0), new BigDecimal(0), "Minimum withdrawal is 10.000!", 402);
        }

        Optional<Long> userIdOptional = userBaseRepository.findIdByUsername(username);

        if(userIdOptional.isEmpty()){
            System.out.println("ERROR WithdrawService:userIdOptional: NOT FOUND!");
            System.out.println("==================  Balance Inquiry Service End  ===================");

            return new WithdrawDTO(new BigDecimal(0), new BigDecimal(0), "Data Not Found", 404);
        }

        Long userId = userIdOptional.get();

        System.out.println("userId             = [" + userId + "]"     );

        BalanceInquiryGetDTO balanceFetch = accountBaseRepository.checkBalance(userId, accountNumber);

        if(balanceFetch == null){
            System.out.println("ERROR WithdrawService:BalanceFetch: NOT FOUND!");
            System.out.println("==================  Withdraw Service End  ===================");
            return new WithdrawDTO(new BigDecimal(0), new BigDecimal(0), "Data Not Found", 404);
        }

        BigDecimal bsBalance = (BigDecimal) balanceFetch.getBsBalance();
        BigDecimal currentBalance = (BigDecimal) balanceFetch.getCurrentBalance();
        BigDecimal fee = new BigDecimal(7500);
        BigDecimal trxAmountWithFee = fee.add(trxAmount);
        BigDecimal total = currentBalance.subtract(trxAmountWithFee);

        System.out.println("currentBalance =    [" + currentBalance + "]"  );
        System.out.println("bsBalance      =    [" + bsBalance + "]"       );
        System.out.println("fee            =    [" + fee   + "]"           );
        System.out.println("trxAmountWFee  -    [" + trxAmountWithFee + "]");
        System.out.println("total          =    [" + total + "]"           );

        if (total.compareTo(BigDecimal.ZERO) < 0){
            System.out.println("ERROR WithdrawService:CompareTotal: Trx amount greater than balance!!");
            System.out.println("==================  Withdraw Service End  ===================");
            return new WithdrawDTO(new BigDecimal(0), new BigDecimal(0), "Insufficient Balance", 402);
        }

        System.out.println("account_base balance adjustment---------------");

        int rowAffected = accountBaseRepository.withdrawBalance(trxAmountWithFee, userId, accountNumber);
        if(rowAffected < 1){
            System.out.println("ERROR WithdrawService:UpdateBalance: UpdateBalance Error!!");
            System.out.println("==================  Withdraw Service End  ===================");
            return new WithdrawDTO(new BigDecimal(0), new BigDecimal(0), "Internal Server Error", 500);
        }

        String globalId = CommonUtil.generateShortId();
        String accountCodeSaving     = "24033123";

        System.out.println("ledger_base insert data---------------");

        int isExist = ledgerBaseRepository.checkIsExist(accountCodeSaving, accountNumber);
        System.out.println("isExist: " + isExist);
        if(isExist > 0){
            rowAffected = ledgerBaseRepository.updateWithdrawBase(trxAmount, accountCodeSaving, accountNumber);
            if(rowAffected < 1){
                System.out.println("ERROR WithdrawService:InsertLedger: Update Ledger Error!!");
                System.out.println("==================  Withdraw Service End  ===================");
                return new WithdrawDTO(new BigDecimal(0), new BigDecimal(0), "Internal Server Error", 500);
            }
        }else{
            rowAffected = ledgerBaseRepository.insertWithdrawBase(accountNumber, accountCodeSaving,
                    trxAmountWithFee, globalId);
            if(rowAffected < 1){
                System.out.println("ERROR WithdrawService:InsertLedger: Insert Ledger Error!!");
                System.out.println("==================  Withdraw Service End  ===================");
                return new WithdrawDTO(new BigDecimal(0), new BigDecimal(0), "Internal Server Error", 500);
            }
        }

        System.out.println("ledger_transaction insert data---------------");

        rowAffected = ledgerTransactionRepository.insertWithdrawLedgerTransaction(accountNumber,currentBalance,trxAmountWithFee,total,globalId,username);
        if(rowAffected < 1){
            System.out.println("ERROR WithdrawService:InsertLedgerTransaction: Insert Ledger Transaction Fee Error!!");
            System.out.println("==================  Withdraw Service End  ===================");
            return new WithdrawDTO(new BigDecimal(0), new BigDecimal(0), "Internal Server Error", 500);
        }

        System.out.println("ledger_master data adjustment---------------");
        rowAffected = ledgerMasterRepository.updateWithdrawLedgerMaster(trxAmount);
        if(rowAffected < 1){
            System.out.println("ERROR WithdrawService:InsertLedgerMaster: update Ledger Master Without Fee Error!!");
            System.out.println("==================  Withdraw Service End  ===================");
            return new WithdrawDTO(new BigDecimal(0), new BigDecimal(0), "Internal Server Error", 500);
        }
        rowAffected = ledgerMasterRepository.updateWithdrawFee(fee);
        if(rowAffected < 1){
            System.out.println("ERROR WithdrawService:InsertLedgerMaster: update Ledger Master With Fee Error!!");
            System.out.println("==================  Withdraw Service End  ===================");
            return new WithdrawDTO(new BigDecimal(0), new BigDecimal(0), "Internal Server Error", 500);
        }

        System.out.println("==================  Withdraw Service End  ===================");

        return new WithdrawDTO(total, total, "Withdraw Success!", 200);
    }
}
