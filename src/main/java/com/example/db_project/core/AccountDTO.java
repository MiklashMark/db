package com.example.db_project.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {
    private UUID uuid;
    private BigDecimal balance;
    private LocalDateTime openDate;
    private AccountStatus accountStatus;
    private AccountType accountType;
}
