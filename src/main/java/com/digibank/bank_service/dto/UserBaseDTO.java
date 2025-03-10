package com.digibank.bank_service.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserBaseDTO {
    private Long id;
    private String username;
    private Integer userType;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
