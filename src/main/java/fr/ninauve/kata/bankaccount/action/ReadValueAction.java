package fr.ninauve.kata.bankaccount.action;

import fr.ninauve.kata.bankaccount.Messages;
import fr.ninauve.kata.bankaccount.io.Console;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static fr.ninauve.kata.bankaccount.action.InputValidator.isValidAmountInCents;

public class ReadValueAction<T> {

    private final Function<String, T> converter;
    private final Predicate<String> validator;
    private final List<String> prompt;
    private final String validFormatLabel;

    public static ReadValueAction<String> READ_ACCOUNT_NUMBER =
            new ReadValueAction<>(s -> s, s -> true, Messages.WHAT_ACCOUNT_NUMBER, "");
    public static ReadValueAction<Long> READ_DEPOSIT_AMOUNT =
            new ReadValueAction<>(s -> Long.parseLong(s), s -> isValidAmountInCents(s), Messages.WHAT_DEPOSIT_AMOUNT, Messages.BAD_PARAM_AMOUNT);

    public ReadValueAction(Function<String, T> converter, Predicate<String> validator, List<String> prompt, String validFormatLabel) {

        this.converter = converter;
        this.validator = validator;
        this.prompt = prompt;
        this.validFormatLabel = validFormatLabel;
    }

    public ReadValueAction(Function<String, T> converter, Predicate<String> validator, String prompt, String validFormatLabel) {

        this(converter, validator, Collections.singletonList(prompt), validFormatLabel);
    }

    public T readValue(Console console) {

        while (true) {
            console.printLines(prompt);
            final String input = console.waitAndGetUserInput();
            if (validator.test(input)) {
                return converter.apply(input);
            } else {
                console.printLine(validFormatLabel);
            }
        }
    }
}
