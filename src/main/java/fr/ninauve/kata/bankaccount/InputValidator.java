package fr.ninauve.kata.bankaccount;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class InputValidator {

    private static final Pattern PATTERN_AMOUNT = Pattern.compile("[1-9]\\d{0,15}");

    public boolean isValidAmountInCents(final String amount) {

        return PATTERN_AMOUNT.matcher(amount).matches();
    }
}
