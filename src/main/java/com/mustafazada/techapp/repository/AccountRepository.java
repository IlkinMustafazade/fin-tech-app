package com.mustafazada.techapp.repository;

import com.mustafazada.techapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
