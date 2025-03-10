package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}
