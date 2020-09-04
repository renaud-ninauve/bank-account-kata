package fr.ninauve.kata.bankaccount;

import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class Main {

    private final Console console;
    private final OperationFormatter operationFormatter;
    private final Clock clock;

    @Autowired
    public Main(Console console, OperationFormatter operationFormatter, Clock clock) {

        this.console = console;
        this.operationFormatter = operationFormatter;
        this.clock = clock;
    }

    public void execute() {

        console.printLine(Messages.WHAT_ACCOUNT_NUMBER);
        console.waitAndGetUserInput();
        console.printLine(Messages.WHAT_DEPOSIT_AMOUNT);
        console.waitAndGetUserInput();
        console.printLine(Messages.DEPOSIT_DONE);
    }

    public static void main(String... args) {

        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        final Main main = ctx.getBean(Main.class);
        main.execute();
    }
}
