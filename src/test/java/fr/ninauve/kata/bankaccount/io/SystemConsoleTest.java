package fr.ninauve.kata.bankaccount.io;

import java.util.Arrays;

/**
 * Run the main to manually test SystemConsole.
 */
class SystemConsoleTest {

    public static void main(String... args) {

        final SystemConsole console = new SystemConsole();
        console.printLine("What is your name?");
        final String name = console.waitAndGetUserInput();
        System.out.println("hello " + name);
        console.printLines(Arrays.asList("AAAA", "BBBB", "CCCC"));
    }
}