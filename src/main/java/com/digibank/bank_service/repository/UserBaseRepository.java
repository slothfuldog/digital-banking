package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.UserBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserBaseRepository extends JpaRepository<UserBase, Long> {
    Optional<UserBase> findByUsername(String username);
    List<UserBase> findByStatus(Integer status);

    @Query(value = "SELECT id FROM user_base WHERE username = :username", nativeQuery = true)
    Optional<Long> findIdByUsername(@Param("username") String username);
}
