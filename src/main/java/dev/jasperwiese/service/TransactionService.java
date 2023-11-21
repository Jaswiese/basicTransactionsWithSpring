package dev.jasperwiese.service;

import dev.jasperwiese.model.Transaction;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionService {

    //TODO: Need to implement persistent REPO
    List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public List<Transaction> findAll() {
        return transactions;
    }

    public Transaction create(Integer amount, String reference){
        UUID userId = UUID.randomUUID();
        ZonedDateTime timeStamp = ZonedDateTime.now();
        Transaction transaction = new Transaction(userId,amount,timeStamp,reference);
        transactions.add(transaction);
        return transaction;
    }

}
