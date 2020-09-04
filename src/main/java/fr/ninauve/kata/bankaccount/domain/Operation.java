package fr.ninauve.kata.bankaccount.domain;

import java.time.ZonedDateTime;

public class Operation {

    private final ZonedDateTime dateTime;
    private final long amount;
    private final long balance;

    public Operation(ZonedDateTime dateTime, long amount, long balance) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.balance = balance;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public long getAmount() {
        return amount;
    }

    public long getBalance() {
        return balance;
    }
}
