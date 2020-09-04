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
class ReadAccountNumberActionTest {

    private static final String ACCOUNT_NUMBER = "1234";

    private ReadAccountNumberAction readAccountNumberAction;

    @Mock
    private Console console;
    @Mock
    private Session session;
    @Mock
    private ReadValueAction<String> readAccountNumber;

    @BeforeEach
    public void setUp() {

        this.readAccountNumberAction = new ReadAccountNumberAction(console, session, readAccountNumber);
    }

    @Test
    public void should_write_account_number_in_session() {

        when(readAccountNumber.readValue(console)).thenReturn(ACCOUNT_NUMBER);

        readAccountNumberAction.execute();

        verify(session).setAccountNumber(ACCOUNT_NUMBER);
    }
}