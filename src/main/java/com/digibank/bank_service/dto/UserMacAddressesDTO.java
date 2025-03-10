package com.digibank.bank_service.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserMacAddressesDTO {
    private Long id;
    private UserBaseDTO user;
    private String macAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
