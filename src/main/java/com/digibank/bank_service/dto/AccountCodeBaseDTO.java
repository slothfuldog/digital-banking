package com.digibank.bank_service.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountCodeBaseDTO {
    private String accountCode;
    private String contraCode;
    private String codeType;
    private String codeDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
