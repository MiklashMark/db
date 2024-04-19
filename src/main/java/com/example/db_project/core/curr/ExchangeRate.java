package com.example.db_project.core.curr;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExchangeRate {
    USD("3.2"),
    GBP("4.1"),
    EUR("3.9"),
    RUB("1");

    private final String rate;
}

