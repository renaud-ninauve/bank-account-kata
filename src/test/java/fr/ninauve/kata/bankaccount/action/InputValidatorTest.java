package fr.ninauve.kata.bankaccount.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputValidatorTest {

    private InputValidator inputValidator;

    @BeforeEach
    public void setUp() {

        this.inputValidator = new InputValidator();
    }

    @ParameterizedTest(name = "invalid amounts")
    @ValueSource(strings = {"", "abcd", "-12", "3.14", "0123", "12345678901234567"})
    public void should_return_false_when_invalid_amount(final String amount) {

        final boolean actual = inputValidator.isValidAmountInCents(amount);

        assertFalse(actual);
    }

    @ParameterizedTest(name = "valid amounts")
    @ValueSource(strings = {"1234", "1234567890123456"})
    public void should_return_true_when_valid_amount(final String amount) {

        final boolean actual = inputValidator.isValidAmountInCents(amount);

        assertTrue(actual);
    }

    @ParameterizedTest(name = "invalid menu")
    @ValueSource(strings = {"", "abcd", "-12", "3.14", "3"})
    public void should_return_false_when_invalid_menu(final String input) {

        final boolean actual = inputValidator.isValidMenu(input);

        assertFalse(actual);
    }

    @ParameterizedTest(name = "valid menu")
    @ValueSource(strings = {"1", "2"})
    public void should_return_true_when_valid_menu(final String input) {

        final boolean actual = inputValidator.isValidMenu(input);

        assertTrue(actual);
    }
}