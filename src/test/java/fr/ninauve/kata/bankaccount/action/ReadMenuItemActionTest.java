package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.io.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadMenuItemActionTest {

    private static final MenuItem MENU_ITEM = MenuItem.DEPOSIT;

    private ReadMenuItemAction readMenuItemAction;

    @Mock
    private Console console;
    @Mock
    private Session session;
    @Mock
    private ReadValueAction<MenuItem> readMenuItem;

    @BeforeEach
    public void setUp() {

        this.readMenuItemAction = new ReadMenuItemAction(console, session, readMenuItem);
    }

    @Test
    public void should_write_account_number_in_session() {

        when(readMenuItem.readValue(console)).thenReturn(MENU_ITEM);

        readMenuItemAction.execute();

        verify(session).setMenuItem(MENU_ITEM);
    }
}