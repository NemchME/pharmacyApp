package org.example.command.validator;

public class Validator {

    public static String requireNotBlank(String arg, String message) {
        if (arg == null || arg.isBlank()) {
            throw new IllegalArgumentException(message);
        }
        return arg.trim();
    }
}
