package dev.jasperwiese.service;

import dev.jasperwiese.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
@Service
public class TransactionService {

    private final String bankSlogan;

    //TODO: Need to implement persistent REPO
    List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public TransactionService(@Value("${bank.slogan}")String bankSlogan) {
        this.bankSlogan = bankSlogan;
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public Transaction create(Integer amount, String reference){
        UUID userId = UUID.randomUUID();
        ZonedDateTime timeStamp = ZonedDateTime.now();
        Transaction transaction = new Transaction(userId,amount,timeStamp,reference, bankSlogan);
        System.out.println("serivce call: " + transaction);
        transactions.add(transaction);
        return transaction;
    }

}
