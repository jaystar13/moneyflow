package com.jaystar.moneyflow.account.repository;

import com.jaystar.moneyflow.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
