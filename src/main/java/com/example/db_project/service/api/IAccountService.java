package com.example.db_project.service.api;

import com.example.db_project.core.AccountDTO;

import java.util.List;
import java.util.UUID;

public interface IAccountService {
    List<AccountDTO> getAllAccounts();

    AccountDTO getAccountById(UUID id);

    AccountDTO createAccount(AccountDTO account);

    AccountDTO updateAccount(UUID id, AccountDTO account);

    void deleteAccount(UUID id);
}
