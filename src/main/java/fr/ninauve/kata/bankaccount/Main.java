package fr.ninauve.kata.bankaccount;

import fr.ninauve.kata.bankaccount.action.Action;
import fr.ninauve.kata.bankaccount.action.MenuItem;
import fr.ninauve.kata.bankaccount.action.ReadValueAction;
import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {

    private final Console console;
    private final ReadValueAction<MenuItem> readMenu;
    private final Action depositAction;

    public Main(Console console, ReadValueAction<MenuItem> readMenu, Action depositAction) {
        this.console = console;
        this.readMenu = readMenu;
        this.depositAction = depositAction;
    }

    @Autowired
    public Main(Console console, Action depositAction) {

        this(console, ReadValueAction.READ_MENU, depositAction);
    }

    public void execute() {

        while (readMenu.readValue(console) != MenuItem.EXIT) {

            depositAction.execute();
        }
    }

    public static void main(String... args) {

        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        final Main main = ctx.getBean(Main.class);
        main.execute();
    }
}
