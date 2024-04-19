package com.example.db_project.core;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AccountRole {
    OWNER("owner"),
    JOINT_OWNER("joint owner");

    private final String value;
}
