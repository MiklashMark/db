package com.example.db_project.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountType {
    SAVINGS("SAVINGS"),
    CHECKING("CHECKING"),
    CREDIT("CREDIT"),
    INVESTMENT("INVESTMENT");

    private final String displayName;
}
