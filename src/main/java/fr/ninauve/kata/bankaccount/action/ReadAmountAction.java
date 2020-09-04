package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.Messages;
import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static fr.ninauve.kata.bankaccount.action.InputValidator.isValidAmountInCents;

@Component
public class ReadAmountAction implements Action {

    public static ReadValueAction<Long> READ_AMOUNT =
            new ReadValueAction<>(s -> Long.parseLong(s), s -> isValidAmountInCents(s), Messages.WHAT_DEPOSIT_AMOUNT, Messages.BAD_PARAM_AMOUNT);

    private final Console console;
    private final Session session;
    private final ReadValueAction<Long> readAmount;

    @Autowired
    public ReadAmountAction(Console console, Session session) {
        this(console, session, READ_AMOUNT);
    }

    public ReadAmountAction(Console console, Session session, ReadValueAction<Long> readAmount) {
        this.console = console;
        this.session = session;
        this.readAmount = readAmount;
    }

    @Override
    public void execute() {

        final Long amount = readAmount.readValue(console);
        session.setAmontInCents(amount);
    }
}
