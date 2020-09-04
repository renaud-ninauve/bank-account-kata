package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.Messages;
import fr.ninauve.kata.bankaccount.io.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static fr.ninauve.kata.bankaccount.action.InputValidator.isValidMenu;

@Component
public class ReadMenuItemAction implements Action {

    public static ReadValueAction<MenuItem> READ_MENU =
            new ReadValueAction<>(s -> MenuItem.fromValueOrNull(s), s -> isValidMenu(s), Messages.MENU, Messages.BAD_INPUT_MENU);

    private final Console console;
    private final Session session;
    private final ReadValueAction<MenuItem> readMenuItem;

    @Autowired
    public ReadMenuItemAction(Console console, Session session) {
        this(console, session, READ_MENU);
    }

    public ReadMenuItemAction(Console console, Session session, ReadValueAction<MenuItem> readMenuItem) {
        this.console = console;
        this.session = session;
        this.readMenuItem = readMenuItem;
    }

    @Override
    public void execute() {

    }
}
