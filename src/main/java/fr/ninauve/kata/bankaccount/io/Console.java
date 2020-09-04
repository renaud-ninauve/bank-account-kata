package fr.ninauve.kata.bankaccount.io;

import java.util.List;

public interface Console {

    void printLine(String line);

    void printLines(List<String> lines);

    String waitAndGetUserInput();
}
