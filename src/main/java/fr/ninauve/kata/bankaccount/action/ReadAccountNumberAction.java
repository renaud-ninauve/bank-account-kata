package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.Messages;
import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static fr.ninauve.kata.bankaccount.action.InputValidator.isValidAmountInCents;

@Component
public class ReadAccountNumberAction implements Action {

    public static ReadValueAction<String> READ_ACCOUNT_NUMBER =
            new ReadValueAction<>(s -> s, s -> true, Messages.WHAT_ACCOUNT_NUMBER, "");

    private final Console console;
    private final Session session;
    private final ReadValueAction<String> readAccountNumber;

    @Autowired
    public ReadAccountNumberAction(Console console, Session session) {
        this(console, session, READ_ACCOUNT_NUMBER);
    }

    public ReadAccountNumberAction(Console console, Session session, ReadValueAction<String> readAccountNumber) {
        this.console = console;
        this.session = session;
        this.readAccountNumber = readAccountNumber;
    }

    @Override
    public void execute() {

        final String account = readAccountNumber.readValue(console);
        session.setAccountNumber(account);
    }
}
