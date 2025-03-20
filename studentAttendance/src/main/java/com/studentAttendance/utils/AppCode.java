package com.studentAttendance.utils;

public enum AppCode {
    ACCOUNT_NOT_FOUND(4001, "Account with specified credentials not found"),
    STUDENT_NOT_FOUND(4002, "Student not found"),
    ROLE_NOT_AVAILABLE(4003, "Role not available"),
    NOT_FOUND(4004,"Not Found"),
    OKAY(2002, "Successful");


    private final int code;
    private final String message;


    private AppCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    // Getter for the message
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "AppCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
