package com.digibank.bank_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserBase user;

    private String name;
    private String gender;
    private LocalDate birthDate;
    private String address;
    private String occupation;
    private String jobPlace;
    private String emailAddress;
    private String phoneNumber;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private String createdBy;
    private LocalDateTime updatedAt = LocalDateTime.now();
    private String updatedBy;
}

