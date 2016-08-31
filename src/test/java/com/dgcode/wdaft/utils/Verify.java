package com.dgcode.wdaft.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by dgjorgievski on 29.08.2016.
 */
public class Verify {
    public static class Argument {

        public static void isNotNull(Object argument, String argumentName) {
            if (argument == null)
                throw new IllegalArgumentException(argumentName + " cannot be null.");
        }

        public static void isNotNullOrEmpty(String argument, String argumentName) {
            if (argument == null || argument.length() == 0)
                throw new IllegalArgumentException(argumentName + " cannot be null or empty.");
        }

        public static void matches(String argument, String argumentName, String regEx) {
            isNotNullOrEmpty(argument, argumentName);
            isNotNullOrEmpty(regEx, "regEx");

            Pattern pattern = Pattern.compile(regEx);
            if (!pattern.matcher(argument).matches())
                throw new IllegalArgumentException(argumentName + " is invalid.");
        }

        public static void isAlphanumericAndNotEmpty(String argument, String argumentName) {
            isNotNullOrEmpty(argument, argumentName);
            if (!StringUtils.isAlphanumeric(argument))
                throw new IllegalArgumentException(argumentName + " must be a non empty alphanumeric string.");
        }

        public static void isNumericAndNotEmpty(String argument, String argumentName) {
            isNotNullOrEmpty(argument, argumentName);
            if (!StringUtils.isNumeric(argument))
                throw new IllegalArgumentException(argumentName + " must be a non empty alphanumeric string.");
        }

        public static void isPositive(int argument, String argumentName) {
            isGreaterThan(0, argument, argumentName);
        }

        public static void isPositiveOrZero(int argument, String argumentName) {
            isGreaterThanOrEqualTo(0, argument, argumentName);
        }

        public static void isGreaterThan(int value, int argument, String argumentName) {
            if (argument <= value)
                throw new IllegalArgumentException(argumentName + " must be greater than " + value + ".");
        }

        public static void isGreaterThanOrEqualTo(int value, int argument, String argumentName) {
            if (argument < value)
                throw new IllegalArgumentException(argumentName + " must be greater than or equal to " + value + ".");
        }

        public static void isLessThanOrEqualTo(int value, int argument, String argumentName) {
            if (argument > value)
                throw new IllegalArgumentException(argumentName + " must be less than or equal to " + value + ".");
        }

    }
}
