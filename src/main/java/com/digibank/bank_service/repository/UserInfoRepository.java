package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserId(Long userId);
    List<UserInfo> findByOccupation(String occupation);
}
