package fr.ninauve.kata.bankaccount;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public interface Messages {

    String WHAT_ACCOUNT_NUMBER = "Account number?";
    String WHAT_DEPOSIT_AMOUNT = "Deposit amount in cents?";
    String DEPOSIT_DONE = "Deposit done.";
    String BAD_PARAM_AMOUNT = "Amount should be in cents (numbers only, no signs).";
    String BAD_INPUT_MENU = "Should be 1 or 2";
    List<String> MENU = asList("What do you want to do?", "1. Deposit", "2. Exit");
}
