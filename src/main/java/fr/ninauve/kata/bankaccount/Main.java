package fr.ninauve.kata.bankaccount;

import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.ZonedDateTime;

@Component
public class Main {

    private final Console console;
    private final OperationFormatter operationFormatter;
    private final Clock clock;
    private final InputValidator inputValidator;

    @Autowired
    public Main(Console console, OperationFormatter operationFormatter, Clock clock, InputValidator inputValidator) {

        this.console = console;
        this.operationFormatter = operationFormatter;
        this.clock = clock;
        this.inputValidator = inputValidator;
    }

    public void execute() {

        console.printLine(Messages.WHAT_ACCOUNT_NUMBER);
        console.waitAndGetUserInput();

        boolean amountInputIsValid = false;
        String amountInput = null;
        while (!amountInputIsValid) {

            console.printLine(Messages.WHAT_DEPOSIT_AMOUNT);
            amountInput = console.waitAndGetUserInput();
            amountInputIsValid = inputValidator.isValidAmountInCents(amountInput);

            if (!amountInputIsValid) {
                console.printLine(Messages.BAD_PARAM_AMOUNT);
            }
        }

        final long amount = Long.parseLong(amountInput);
        console.printLine(Messages.DEPOSIT_DONE);
        final String formattedDeposit = operationFormatter.formatDeposit(ZonedDateTime.now(clock), amount, amount);
        console.printLine(formattedDeposit);
    }

    public static void main(String... args) {

        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        final Main main = ctx.getBean(Main.class);
        main.execute();
    }
}
