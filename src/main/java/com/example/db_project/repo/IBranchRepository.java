package com.example.db_project.repo;

import com.example.db_project.repo.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IBranchRepository extends JpaRepository<Branch, UUID> {
}
