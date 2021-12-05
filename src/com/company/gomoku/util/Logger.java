package com.company.gomoku.util;

public class Logger {

    private static final String INFO_PRE = "Gomoku_Info: ";

    private static final String ERROR_PRE = "Gomoku_error: ";

    public static void info(String txt) {
        System.out.println(INFO_PRE + txt);
    }

    public static void info(String txt, Object ... bean) {
        System.out.printf(INFO_PRE + txt, bean);
    }

    public static void error(String txt) {
        System.err.println(ERROR_PRE + txt);
    }
}
