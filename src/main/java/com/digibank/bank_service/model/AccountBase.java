package com.digibank.bank_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "account_base")
public class AccountBase {
    @Id
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserBase user;

    @ManyToOne
    @JoinColumn(name = "account_type")
    private AccountType accountType;

    private BigDecimal bsBalance;
    private BigDecimal currentBalance;

    @ManyToOne
    @JoinColumn(name = "joint_with", referencedColumnName = "id")
    private UserBase jointWith;

    private Integer status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
}
