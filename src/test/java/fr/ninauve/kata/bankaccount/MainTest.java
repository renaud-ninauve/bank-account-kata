package fr.ninauve.kata.bankaccount;

import fr.ninauve.kata.bankaccount.action.Action;
import fr.ninauve.kata.bankaccount.action.MenuItem;
import fr.ninauve.kata.bankaccount.action.ReadValueAction;
import fr.ninauve.kata.bankaccount.io.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainTest {

    private Main main;
    @Mock
    private Console console;
    @Mock
    private ReadValueAction<MenuItem> readMenu;
    @Mock
    private Action depositAction;

    @BeforeEach
    public void setUp() {

        this.main = new Main(console, readMenu, depositAction);
    }

    @Test
    public void should_execute_deposit_if_chosen() {

        when(readMenu.readValue(any())).thenReturn(MenuItem.DEPOSIT, MenuItem.EXIT);

        main.execute();

        verify(depositAction).execute();
    }

    @Test
    public void should_do_another_deposit_if_chosen() {

        when(readMenu.readValue(any())).thenReturn(MenuItem.DEPOSIT, MenuItem.DEPOSIT, MenuItem.EXIT);

        main.execute();

        verify(depositAction, times(2)).execute();
    }
}