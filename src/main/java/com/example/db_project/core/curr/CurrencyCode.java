package com.example.db_project.core.curr;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CurrencyCode {
    USD(3),
    GBP(2),
    EUR(1),
    BYN(0);
    private Integer value;

}
