package fr.ninauve.kata.bankaccount;

import fr.ninauve.kata.bankaccount.io.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainTest {

    private static final String ACCOUNT_NUMBER = "1234";
    private static final String PROMPT_ACCOUNT_NUMBER = "Account Number?";
    private static final String PROMPT_DEPOSIT_AMOUNT = "Deposit Amount?";

    private Main main;

    @Mock
    private Console console;

    @BeforeEach
    public void setUp() {

        this.main = new Main(console);
    }

    @Test
    public void should_ask_account_number_then_amount() {

        main.execute();

        final InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine(PROMPT_ACCOUNT_NUMBER);
        inOrder.verify(console).printLine(PROMPT_DEPOSIT_AMOUNT);
    }

    @Test
    public void should_acknowledge_deposit() {

        when(console.waitAndGetUserInput())
                .thenReturn(ACCOUNT_NUMBER, "4200");

        main.execute();

        verify(console, times(1)).printLine("Deposit done.");
    }
}