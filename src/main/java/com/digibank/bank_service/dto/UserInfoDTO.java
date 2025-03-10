package com.digibank.bank_service.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserInfoDTO {
    private Long id;
    private UserBaseDTO user;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String address;
    private String occupation;
    private String jobPlace;
    private String emailAddress;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
