package com.example.db_project.repo;

import com.example.db_project.repo.entity.Client;
import com.example.db_project.repo.entity.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IClientAccountRepository extends JpaRepository<ClientAccount, UUID> {
}
