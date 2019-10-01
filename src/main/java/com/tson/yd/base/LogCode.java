package com.tson.yd.base;

public enum LogCode {
    RC_HEAD_PARAMETER_ERROR(1003, "Header parameter error, Please check header!"),
    RC_PARAMETER_ERROR(1002, "Parameter error,Please check parameters！"),
    RC_RESULT_EMPTY(1001, "Result data is empty"),
    RC_USER_NONE(7777, "User does not exist!"),
    RC_USER_NOT_NONE(7778, "User already exists ~"),
    RC_USER_PASSWORD_ERROR(7779, "Password error ~"),
    RC_USER_PASSWORD_EQUALLY(7781, "Password error ~"),
    RC_USER_EMAIL_FORMAT_ERROR(7780, "The old and new passwords are the same ~"),
    RC_SEND_EMAIL_ERROR(3304, "send email error~"),
    RC_QUERY_EMAIL_ERROR(3305, "email code is empty"),
    RC_VERIFICATION_CODE_SEND_ERROR(3306, "VerificationCode send error"),
    RC_VERIFICATION_CODE_ERROR(3307, "VerificationCode error"),
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
