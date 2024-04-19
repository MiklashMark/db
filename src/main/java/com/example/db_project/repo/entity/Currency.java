package com.example.db_project.repo.entity;

import com.example.db_project.core.curr.CurrencyCode;
import com.example.db_project.core.curr.CurrencyName;
import com.example.db_project.core.curr.ExchangeRate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "bank", name = "currency")
@NoArgsConstructor
@Getter
@Setter
public class Currency {
    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(name = "currency_code", unique = true)
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(name = "currency_name", unique = true)
    @Enumerated(EnumType.STRING)
    private CurrencyName currencyName;

    @Column(name = "exchange_rate", unique = true)
    private BigDecimal exchangeRate;

    public Currency(CurrencyCode currencyCode, CurrencyName currencyName, BigDecimal exchangeRate) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(uuid, currency.uuid) && currencyCode == currency.currencyCode && currencyName == currency.currencyName && exchangeRate == currency.exchangeRate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, currencyCode, currencyName, exchangeRate);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "uuid=" + uuid +
                ", currencyCode=" + currencyCode +
                ", currencyName=" + currencyName +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
