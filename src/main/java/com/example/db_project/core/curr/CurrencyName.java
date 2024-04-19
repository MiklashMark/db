package com.example.db_project.core.curr;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CurrencyName {
    US_DOLLAR("US Dollar"),
    BRITISH_POUND("British Pound"),
    EURO("Euro"),
    BELARUSIAN_RUBLE("Belarusian Ruble");

    private final String displayName;

}