package fr.ninauve.kata.bankaccount;

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
    @ValueSource(strings = {"", "abcd", "-12", "3.14", "0123"})
    public void should_return_false_when_invalid_amount(final String amount) {

        final boolean actual = inputValidator.isValidAmountInCents(amount);

        assertFalse(actual);
    }

    @ParameterizedTest(name = "valid amounts")
    @ValueSource(strings = {"1234"})
    public void should_return_true_when_valid_amount(final String amount) {

        final boolean actual = inputValidator.isValidAmountInCents(amount);

        assertTrue(actual);
    }
}