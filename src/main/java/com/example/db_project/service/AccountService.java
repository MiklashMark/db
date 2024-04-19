package com.example.db_project.service;

import com.example.db_project.core.AccountDTO;
import com.example.db_project.repo.IAccountRepository;
import com.example.db_project.repo.entity.Account;
import com.example.db_project.service.api.IAccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountService::convertToDto)
                .collect(Collectors.toList());
    }


    public AccountDTO getAccountById(UUID id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            return convertToDto(optionalAccount.get());
        } else throw new IllegalStateException();
    }

    @Transactional
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = convertToEntity(accountDTO);
        accountRepository.save(account);
        return convertToDto(account);
    }

    @Transactional
    public AccountDTO updateAccount(UUID id, AccountDTO accountDTO) {
        Account account = convertToEntity(accountDTO);
        if (accountRepository.existsById(id)) {
            accountRepository.save(account);
            return convertToDto(account);
        } else {
            return null;
        }
    }

    public void deleteAccount(UUID id) {
        if (accountRepository.existsById(id)) {
            // Дополнительная логика по удалению счета
            accountRepository.deleteById(id);
        }
    }

    public static AccountDTO convertToDto(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setUuid(account.getUuid());
        dto.setAccountType(account.getAccountType());
        dto.setBalance(account.getBalance());
        dto.setOpenDate(account.getOpenDt());
        dto.setAccountStatus(account.getAccountStatus());
        return dto;
    }

    public Account convertToEntity(AccountDTO dto) {
        Account account = new Account();
        account.setUuid(dto.getUuid());
        account.setAccountType(dto.getAccountType());
        account.setBalance(dto.getBalance());
        account.setOpenDt(dto.getOpenDate());
        account.setAccountStatus(dto.getAccountStatus());
        return account;
    }
}