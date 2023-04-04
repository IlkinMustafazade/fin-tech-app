package com.mustafazada.techapp.repository;

import com.mustafazada.techapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNo(Integer accountNo);
}
