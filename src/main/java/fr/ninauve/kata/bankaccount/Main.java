package fr.ninauve.kata.bankaccount;

import fr.ninauve.kata.bankaccount.action.MenuItem;
import fr.ninauve.kata.bankaccount.action.ReadValueAction;
import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.ZonedDateTime;

import static fr.ninauve.kata.bankaccount.action.ReadValueAction.*;

@Component
public class Main {

    private final Console console;
    private final OperationFormatter operationFormatter;
    private final Clock clock;
    private final ReadValueAction<MenuItem> readMenu;
    private final ReadValueAction<String> readAccountNumber;
    private final ReadValueAction<Long> readDepositAmount;

    public Main(Console console,
                OperationFormatter operationFormatter,
                Clock clock,
                ReadValueAction<MenuItem> readMenu,
                ReadValueAction<String> readAccountNumber,
                ReadValueAction<Long> readDepositAmount) {

        this.console = console;
        this.operationFormatter = operationFormatter;
        this.clock = clock;
        this.readMenu = readMenu;
        this.readAccountNumber = readAccountNumber;
        this.readDepositAmount = readDepositAmount;
    }

    @Autowired
    public Main(Console console, OperationFormatter operationFormatter, Clock clock) {

        this(console, operationFormatter, clock,
                READ_MENU, READ_ACCOUNT_NUMBER, READ_DEPOSIT_AMOUNT);
    }

    public void execute() {

        while (readMenu.readValue(console) != MenuItem.EXIT) {

            final String accountNumber = readAccountNumber.readValue(console);
            final Long depositAmount = readDepositAmount.readValue(console);
            console.printLine(Messages.DEPOSIT_DONE);
            final String formattedDeposit = operationFormatter.formatDeposit(ZonedDateTime.now(clock), depositAmount, depositAmount);
            console.printLine(formattedDeposit);
        }
    }

    public static void main(String... args) {

        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        final Main main = ctx.getBean(Main.class);
        main.execute();
    }
}
