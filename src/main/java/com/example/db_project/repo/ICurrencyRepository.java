package com.example.db_project.repo;

import com.example.db_project.repo.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICurrencyRepository extends JpaRepository<Currency, UUID> {
}
