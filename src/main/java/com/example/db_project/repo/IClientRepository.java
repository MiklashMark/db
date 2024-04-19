package com.example.db_project.repo;

import com.example.db_project.repo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IClientRepository extends JpaRepository<Client, UUID> {
}
