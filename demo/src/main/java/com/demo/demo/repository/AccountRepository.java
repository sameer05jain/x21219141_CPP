package com.demo.demo.repository;

import java.util.Set;

import com.demo.demo.models.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    Set<Account> findByUserUserId(Long userId);
}
