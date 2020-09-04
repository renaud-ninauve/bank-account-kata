package fr.ninauve.kata.bankaccount.action;

import java.util.Objects;

public enum MenuItem {

    DEPOSIT("1"), EXIT("2");

    private final String value;

    MenuItem(String value) {
        this.value = value;
    }

    public static MenuItem fromValueOrNull(final String value) {

        for(MenuItem menuItem: MenuItem.values()) {
            if (Objects.equals(value, menuItem.value)) {
                return menuItem;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
