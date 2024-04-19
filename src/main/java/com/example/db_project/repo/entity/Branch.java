package com.example.db_project.repo.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(schema = "bank", name = "branch")
@ToString(exclude = "account")
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String name;
    private String address;
    @Column(name = "working_hours")
    private String workingHours;

    public Branch(String name, String address,
                  String workingHours) {
        this.name = name;
        this.address = address;
        this.workingHours = workingHours;
    }
}
