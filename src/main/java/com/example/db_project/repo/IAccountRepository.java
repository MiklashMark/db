package com.example.db_project.repo;

import com.example.db_project.repo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface IAccountRepository extends JpaRepository<Account, UUID> {
    @Query(value = "SELECT * FROM bank.account WHERE account_type = ?1", nativeQuery = true)
    List<Account> findByAccountType(String accountType);

    @Query(value = "SELECT * FROM bank.account WHERE balance > ?1" +
            " UNION SELECT * FROM bank.account WHERE balance < ?2", nativeQuery = true)
    List<Account> findByBalanceGreaterThanAndLessThan(BigDecimal greaterThanBalance, BigDecimal lessThanBalance);

    @Query(value = "SELECT * FROM bank.account WHERE balance > ?1 INTERSECT SELECT * FROM bank.account WHERE account_status = ?2", nativeQuery = true)
    List<Account> findByBalanceGreaterThanAndAccountStatus(BigDecimal balance, String accountStatus);

    @Query(value = "SELECT * FROM bank.account WHERE balance > ?1 MINUS SELECT * FROM bank.account WHERE account_status = ?2", nativeQuery = true)
    List<Account> findByBalanceGreaterThanMinusAccountStatus(BigDecimal balance, String accountStatus);

    @Query(value = "SELECT SUM(balance) FROM bank.account", nativeQuery = true)
    BigDecimal sumOfBalances();
}
