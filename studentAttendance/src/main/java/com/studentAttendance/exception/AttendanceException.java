package com.studentAttendance.exception;

import com.studentAttendance.utils.AppCode;

public class AttendanceException extends RuntimeException{
    private AppCode code;

    public AttendanceException(AppCode code){
        super(code.getMessage());
        this.code = code;
    }

    public AppCode getCode(){
        return code;
    }
}
