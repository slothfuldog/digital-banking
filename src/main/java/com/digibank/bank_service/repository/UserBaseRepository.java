package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.UserBase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserBaseRepository extends JpaRepository<UserBase, Long> {
    Optional<UserBase> findByUsername(String username);
    List<UserBase> findByStatus(Integer status);
}
