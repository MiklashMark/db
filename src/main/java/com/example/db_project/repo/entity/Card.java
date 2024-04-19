package com.example.db_project.repo.entity;


import com.example.db_project.core.CardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "bank", name = "card")
@NoArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue
    private UUID uuid;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Card(String cardNumber, LocalDateTime expirationDate, CardStatus cardStatus, List<Transaction> transactions) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardStatus = cardStatus;
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(uuid, card.uuid) && Objects.equals(cardNumber, card.cardNumber) && Objects.equals(expirationDate, card.expirationDate) && cardStatus == card.cardStatus && Objects.equals(transactions, card.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, cardNumber, expirationDate, cardStatus, transactions);
    }

    @Override
    public String toString() {
        return "Card{" +
                "uuid=" + uuid +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", cardStatus=" + cardStatus +
                '}';
    }
}
