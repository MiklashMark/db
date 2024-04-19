package com.example.db_project.repo.entity;

import com.example.db_project.core.AccountStatus;
import com.example.db_project.core.AccountType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "bank", name = "account")
@RequiredArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue
    private UUID uuid;
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(precision = 15, scale = 2)
    private BigDecimal balance;
    @Column(name = "open_date")
    private LocalDateTime openDt;
    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Card> cards;
    @OneToOne(cascade = CascadeType.ALL)
    private Currency currency;

    public Account(AccountType accountType, BigDecimal balance, LocalDateTime openDt,
                   AccountStatus accountStatus, Currency currency, List<Card> cards) {
        this.accountType = accountType;
        this.balance = balance;
        this.openDt = openDt;
        this.accountStatus = accountStatus;
        this.currency = currency;
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(uuid, account.uuid) && accountType == account.accountType && Objects.equals(balance, account.balance) && Objects.equals(openDt, account.openDt) && accountStatus == account.accountStatus && Objects.equals(cards, account.cards) && Objects.equals(currency, account.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, accountType, balance, openDt, accountStatus, cards, currency);
    }

    @Override
    public String toString() {
        return "Account{" +
                "uuid=" + uuid +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", openDt=" + openDt +
                ", accountStatus=" + accountStatus +
                '}';
    }
}
