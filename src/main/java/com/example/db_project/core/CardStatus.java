package com.example.db_project.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CardStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    BLOCKED("Blocked"),
    LOST("Lost"),
    EXPIRED("Expired");

    private final String displayName;

}
