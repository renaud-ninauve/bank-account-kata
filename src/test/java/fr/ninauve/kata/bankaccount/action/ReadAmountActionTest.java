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
class ReadAmountActionTest {

    private static final long AMOUNT = 4200l;

    private ReadAmountAction readAmountAction;

    @Mock
    private Console console;
    @Mock
    private Session session;
    @Mock
    private ReadValueAction<Long> readAmount;

    @BeforeEach
    public void setUp() {

        this.readAmountAction = new ReadAmountAction(console, session, readAmount);
    }

    @Test
    public void should_write_amount_in_session() {

        when(readAmount.readValue(console)).thenReturn(AMOUNT);

        readAmountAction.execute();

        verify(session).setAmontInCents(AMOUNT);
    }
}