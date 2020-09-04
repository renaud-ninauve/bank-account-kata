package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.MessagesTest;
import fr.ninauve.kata.bankaccount.OperationFormatter;
import fr.ninauve.kata.bankaccount.io.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepositActionTest {

    private static final String ACCOUNT_NUMBER = "1234";
    public static final ZonedDateTime ZONED_DATE_TIME =
            ZonedDateTime.of(LocalDateTime.of(2020, Month.SEPTEMBER, 12, 11, 43), ZoneId.of("Europe/Paris"));
    private static final Clock CLOCK =
            Clock.fixed(ZONED_DATE_TIME.toInstant(), ZONED_DATE_TIME.getZone());
    private static final String FORMATTED_DEPOSIT = "2020-09-12 11:43;deposit;4200;4200";

    private DepositAction depositAction;
    @Mock
    private Console console;
    @Mock
    private OperationFormatter operationFormatter;
    @Mock
    private ReadValueAction<String> readAccountNumber;
    @Mock
    private ReadValueAction<Long> readDepositAmount;

    @BeforeEach
    public void setUp() {

        this.depositAction = new DepositAction(console, operationFormatter, CLOCK, readAccountNumber, readDepositAmount);
    }

    @Test
    public void should_ask_account_number_then_amount() {

        when(readAccountNumber.readValue(any()))
                .thenReturn(ACCOUNT_NUMBER);
        when(readDepositAmount.readValue(any()))
                .thenReturn(4200l);

        depositAction.execute();

        final InOrder inOrder = inOrder(readAccountNumber, readDepositAmount);
        inOrder.verify(readAccountNumber).readValue(console);
        inOrder.verify(readDepositAmount).readValue(console);
    }

    @Test
    public void should_acknowledge_deposit() {

        when(readAccountNumber.readValue(any()))
                .thenReturn(ACCOUNT_NUMBER);
        when(readDepositAmount.readValue(any()))
                .thenReturn(4200l);

        depositAction.execute();

        verify(console, times(1)).printLine(MessagesTest.DEPOSIT_DONE);
    }

    @Test
    public void should_print_deposit() {

        when(readAccountNumber.readValue(any()))
                .thenReturn(ACCOUNT_NUMBER);
        when(readDepositAmount.readValue(any()))
                .thenReturn(4200l);
        when(operationFormatter.formatDeposit(any(), anyLong(), anyLong()))
                .thenReturn(FORMATTED_DEPOSIT);

        depositAction.execute();

        verify(console).printLine(MessagesTest.DEPOSIT_DONE);
        verify(operationFormatter).formatDeposit(eq(ZonedDateTime.now(CLOCK)), eq(4200l), eq(4200l));
        verify(console).printLine(FORMATTED_DEPOSIT);
    }
}