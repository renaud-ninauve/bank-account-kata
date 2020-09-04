package fr.ninauve.kata.bankaccount.io;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class SystemConsole implements Console {

    @Override
    public void printLine(String line) {

        System.out.println(line);
    }

    @Override
    public void printLines(List<String> lines) {

        for(String line: lines) {
            printLine(line);
        }
    }

    @Override
    public String waitAndGetUserInput() {

        return new Scanner(System.in).next();
    }
}
