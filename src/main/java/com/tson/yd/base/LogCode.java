package com.tson.yd.base;

public enum LogCode {
    RC_HEAD_PARAMETER_ERROR(1003, "header parameter error, Please check header!"),
    RC_PARAMETER_ERROR(1002, "Parameter error,Please check parameters！"),
    RC_RESULT_EMPTY(1001, "result data is empty"),
    RC_SUCCESS(200, "success");

    private int code;
    private String message;

    LogCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
