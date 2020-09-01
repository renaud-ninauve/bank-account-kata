package fr.ninauve.kata.bankaccount.io;

/**
 * Run the main to manually test SystemConsole.
 */
class SystemConsoleTest {

    public static void main(String... args) {

        final SystemConsole console = new SystemConsole();
        console.printLine("What is your name?");
        final String name = console.waitAndGetUserInput();
        System.out.println("hello " + name);
    }
}