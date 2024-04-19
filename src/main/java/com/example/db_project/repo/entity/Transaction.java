package com.example.db_project.repo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "bank", name = "transaction")
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue
    private UUID uuid;
    @ManyToOne(cascade = CascadeType.ALL)
    private Card receiverCard;
    @ManyToOne(cascade = CascadeType.ALL)
    private Card senderCard;
    private BigDecimal amount;
    private LocalDateTime time;

    public Transaction(Card receiverCard, Card senderCard, BigDecimal amount, LocalDateTime time) {
        this.receiverCard = receiverCard;
        this.senderCard = senderCard;
        this.amount = amount;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(receiverCard, that.receiverCard) && Objects.equals(senderCard, that.senderCard) && Objects.equals(amount, that.amount) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, receiverCard, senderCard, amount, time);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "uuid=" + uuid +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}
