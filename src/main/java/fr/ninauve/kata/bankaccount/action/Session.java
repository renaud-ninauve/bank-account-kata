package fr.ninauve.kata.bankaccount.action;

import org.springframework.stereotype.Component;

@Component
public class Session {
    private MenuItem menuItem;
    private String accountNumber;
    private long amontInCents;

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAmontInCents() {
        return amontInCents;
    }

    public void setAmontInCents(long amontInCents) {
        this.amontInCents = amontInCents;
    }
}
