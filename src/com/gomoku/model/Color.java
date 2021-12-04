package com.gomoku.model;

public enum Color {
    BLACK(1),
    WHITE(2);

    private int value;

    Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
