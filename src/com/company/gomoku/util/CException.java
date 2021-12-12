package com.company.gomoku.util;

public class CException extends Exception {

    public static final int OUT_OF_BOUND = 101;

    public static final int FORBIDDEN_MOVE = 102;

    public static final int REPETITION_MOVE = 103;

    private int errCode;

    public CException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
