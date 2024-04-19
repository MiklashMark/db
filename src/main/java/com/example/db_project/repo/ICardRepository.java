package com.example.db_project.repo;

import com.example.db_project.repo.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ICardRepository extends JpaRepository<Card, UUID> {
    @Query("SELECT c FROM Card c WHERE c.expirationDate IS NULL AND c.cardNumber IS NULL AND c.cardStatus IS NULL")
    List<Card> findAllUninitializedCards();}
