package dev.jasperwiese.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private Integer amount;
    private ZonedDateTime timestamp;
    private String reference;

    public Transaction() {
    }

    public Transaction(UUID id, Integer amount, ZonedDateTime timestamp, String reference) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
        this.reference = reference;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", reference='" + reference + '\'' +
                '}';
    }
}
