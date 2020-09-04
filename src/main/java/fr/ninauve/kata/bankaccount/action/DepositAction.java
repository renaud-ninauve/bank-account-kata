package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.Messages;
import fr.ninauve.kata.bankaccount.OperationFormatter;
import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.ZonedDateTime;

import static fr.ninauve.kata.bankaccount.action.ReadValueAction.READ_ACCOUNT_NUMBER;
import static fr.ninauve.kata.bankaccount.action.ReadValueAction.READ_DEPOSIT_AMOUNT;

@Component
public class DepositAction implements Action {

    private final Console console;
    private final OperationFormatter operationFormatter;
    private final Clock clock;
    private final ReadValueAction<String> readAccountNumber;
    private final ReadValueAction<Long> readDepositAmount;

    public DepositAction(Console console, OperationFormatter operationFormatter, Clock clock, ReadValueAction<String> readAccountNumber, ReadValueAction<Long> readDepositAmount) {
        this.console = console;
        this.operationFormatter = operationFormatter;
        this.clock = clock;
        this.readAccountNumber = readAccountNumber;
        this.readDepositAmount = readDepositAmount;
    }

    @Autowired
    public DepositAction(Console console, OperationFormatter operationFormatter, Clock clock) {

        this(console, operationFormatter, clock,
                READ_ACCOUNT_NUMBER, READ_DEPOSIT_AMOUNT);
    }

    public void execute() {

        final String accountNumber = readAccountNumber.readValue(console);
        final Long depositAmount = readDepositAmount.readValue(console);
        console.printLine(Messages.DEPOSIT_DONE);
        final String formattedDeposit = operationFormatter.formatDeposit(ZonedDateTime.now(clock), depositAmount, depositAmount);
        console.printLine(formattedDeposit);
    }
}
