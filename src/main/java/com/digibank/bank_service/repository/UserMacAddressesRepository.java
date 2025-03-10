package com.digibank.bank_service.repository;

import com.digibank.bank_service.model.UserMacAddresses;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserMacAddressesRepository extends JpaRepository<UserMacAddresses, Long> {
    List<UserMacAddresses> findByUserId(Long userId);
    List<UserMacAddresses> findByMacAddress(String macAddress);
}
