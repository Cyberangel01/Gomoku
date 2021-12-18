package model;

import com.sustc.stdlib.StdDraw;

import java.awt.*;

public enum ChessColor {
    BLACK(1),
    WHITE(2);

    private int value;

    ChessColor(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        if (value == 1) {
            return StdDraw.BLACK;
        }
        return StdDraw.WHITE;
    }

    @Override
    public String toString() {
        if (value == 1) {
            return "BLACK";
        }
        return "WHITE";
    }
}
