package fr.ninauve.kata.bankaccount;

import fr.ninauve.kata.bankaccount.io.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainTest {

    private static final String ACCOUNT_NUMBER = "1234";
    public static final ZonedDateTime ZONED_DATE_TIME =
            ZonedDateTime.of(LocalDateTime.of(2020, Month.SEPTEMBER, 12, 11, 43), ZoneId.of("Europe/Paris"));
    private static final Clock CLOCK =
            Clock.fixed(ZONED_DATE_TIME.toInstant(), ZONED_DATE_TIME.getZone());
    private static final String FORMATTED_DEPOSIT = "2020-09-12 11:43;deposit;4200;4200";

    private Main main;
    @Mock
    private Console console;
    @Mock
    private OperationFormatter operationFormatter;

    @BeforeEach
    public void setUp() {

        this.main = new Main(console, operationFormatter, CLOCK);
    }

    @Test
    public void should_ask_account_number_then_amount() {

        main.execute();

        final InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine(MessagesTest.WHAT_ACCOUNT_NUMBER);
        inOrder.verify(console).printLine(MessagesTest.WHAT_DEPOSIT_AMOUNT);
    }

    @Test
    public void should_acknowledge_deposit() {

        when(console.waitAndGetUserInput())
                .thenReturn(ACCOUNT_NUMBER, "4200");

        main.execute();

        verify(console, times(1)).printLine(MessagesTest.DEPOSIT_DONE);
    }

    @Test
    public void should_print_deposit() {

        when(console.waitAndGetUserInput())
                .thenReturn(ACCOUNT_NUMBER, "4200");
        when(operationFormatter.formatDeposit(any(), anyLong(), anyLong()))
                .thenReturn(FORMATTED_DEPOSIT);

        main.execute();

        verify(console).printLine(MessagesTest.DEPOSIT_DONE);
        verify(operationFormatter).formatDeposit(eq(ZonedDateTime.now(CLOCK)), eq(4200l), eq(4200l));
        verify(console).printLine(FORMATTED_DEPOSIT);
    }
}