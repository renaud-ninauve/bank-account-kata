package fr.ninauve.kata.bankaccount;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OperationFormatter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm");
    private static final String OPERATION_DEPOSIT = "deposit";

    public String formatDeposit(ZonedDateTime dateTime, long amount, long balance) {

        final String dateTimeFormatted = dateTime.format(DATE_TIME_FORMATTER);
        return Stream.of(dateTimeFormatted, OPERATION_DEPOSIT, "" + amount, "" + balance)
                .collect(Collectors.joining(";"));
    }
}
