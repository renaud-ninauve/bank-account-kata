package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.io.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReadValueActionTest {

    private static final String INPUT = "1234";
    private static final String INVALID_INPUT = "bad";
    private static final String PROMPT = "What value?";
    private static final String VALID_FORMAT_LABEL = "should be an int";

    private ReadValueAction<Long> readValueAction;

    @Mock
    private Console console;
    @Mock
    private Function<String, Long> converter;
    @Mock
    private Predicate<String> validator;

    @BeforeEach
    public void setUp() {

        readValueAction = new ReadValueAction<>(
                converter, validator, PROMPT, VALID_FORMAT_LABEL
        );
    }

    @Test
    public void should_read_valid_value() {

        when(console.waitAndGetUserInput()).thenReturn(INPUT);
        when(converter.apply(INPUT)).thenReturn(42l);
        when(validator.test(INPUT)).thenReturn(true);

        final Long actual = readValueAction.readValue(console);

        assertEquals(42l, actual);
        verify(converter).apply(INPUT);
        verify(validator).test(INPUT);
        verify(console).printLines(singletonList(PROMPT));
        verify(console).waitAndGetUserInput();
    }

    @Test
    public void should_re_read_value_when_invalid() {

        when(console.waitAndGetUserInput()).thenReturn(INVALID_INPUT, INPUT);
        when(converter.apply(INPUT)).thenReturn(42l);
        when(validator.test(INVALID_INPUT)).thenReturn(false);
        when(validator.test(INPUT)).thenReturn(true);

        final Long actual = readValueAction.readValue(console);

        assertEquals(42l, actual);
        verify(validator).test(INVALID_INPUT);
        verify(validator).test(INPUT);

        verify(converter).apply(INPUT);
        verifyNoMoreInteractions(converter);

        verify(console, times(2)).printLines(singletonList(PROMPT));
        verify(console, times(2)).waitAndGetUserInput();
        verify(console).printLine(VALID_FORMAT_LABEL);
    }
}