package com.digibank.bank_service.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountBaseDTO {
    private String accountNumber;
    private UserBaseDTO user;
    private Integer accountType;
    private BigDecimal bsBalance;
    private BigDecimal currentBalance;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
