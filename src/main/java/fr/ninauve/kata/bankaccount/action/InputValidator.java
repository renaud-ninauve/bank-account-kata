package fr.ninauve.kata.bankaccount.action;

import java.util.Objects;
import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern PATTERN_AMOUNT = Pattern.compile("[1-9]\\d{0,15}");

    public static boolean isValidAmountInCents(final String amount) {

        return PATTERN_AMOUNT.matcher(amount).matches();
    }

    public static boolean isValidMenu(final String input) {

        return Objects.equals(input, "1")
                || Objects.equals(input, "2");
    }
}
