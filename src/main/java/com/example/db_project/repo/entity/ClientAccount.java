package com.example.db_project.repo.entity;

import com.example.db_project.core.AccountRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(schema = "bank", name = "client_account")
@RequiredArgsConstructor
public class ClientAccount {

    @Id
    @GeneratedValue
    private UUID uuid;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    @Column(name = "account_role")
    @Enumerated(EnumType.STRING)
    private AccountRole role;

    public ClientAccount(Client client, Account account, AccountRole role) {
        this.client = client;
        this.account = account;
        this.role = role;
    }
}