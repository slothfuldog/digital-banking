package com.digibank.bank_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "account_code_base")
public class AccountCodeBase {
    @Id
    private String accountCode;

    private String contraCode;
    private String codeType;
    private String codeDescription;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
}
