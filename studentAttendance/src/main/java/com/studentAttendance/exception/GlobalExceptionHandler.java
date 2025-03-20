//package com.studentAttendance.exception;
//
//import com.studentAttendance.dto.response.ApiResponse;
//import com.studentAttendance.utils.AppCode;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice //This annotation tells Spring that this class will handle
//// exceptions globally across all controllers in the application.
//public class GlobalExceptionHandler extends Throwable {
//
//    @ExceptionHandler(AttendanceException.class)   //This annotation tells Spring to call this method
//    // whenever an AttendanceException is thrown anywhere in the application.
//    public ResponseEntity<ApiResponse<String>> handleAttendanceException(AttendanceException ex) {
//        AppCode code = ex.getCode(); //Retrieves the error code and message from the exception.
//        ApiResponse<String> response = new ApiResponse<>(
//
//                code.getCode(),
//                code.getMessage(),
//                null
//        );//Creates a standardized response object with the error code, message, and no data (null)
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);//Wraps the response in a ResponseEntity with an HTTP status of 400 Bad Request.
//    }
//
//    @ExceptionHandler(Exception.class)  //This annotation tells Spring to call this method whenever any unhandled exception (of type Exception or its subclasses) is thrown.
//    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
//        ApiResponse<String> response = new ApiResponse<>(
//
//                5000,
//                "An unexpected error occurred: " + ex.getMessage(),
//                null
//
//        );
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//
//}


package com.studentAttendance.exception;

import com.studentAttendance.dto.response.ApiResponse;
import com.studentAttendance.utils.AppCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  // Marks this class as a global exception handler,
// meaning it will handle exceptions across all controllers.
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AttendanceException.class)
    public ResponseEntity<ApiResponse<String>> handleAttendanceException(AttendanceException ex) {
        AppCode code = ex.getCode();

        logger.error("AttendanceException occurred: {}", ex.getMessage());

        ApiResponse<String> response = new ApiResponse<>(
                code.getCode(),
                code.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.warn("IllegalArgumentException: {}", ex.getMessage());

        ApiResponse<String> response = new ApiResponse<>(
                4001,
                "Invalid request: " + ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handleRuntimeException(RuntimeException ex) {
        logger.error("RuntimeException occurred: {}", ex.getMessage(), ex);

        ApiResponse<String> response = new ApiResponse<>(
                5001,
                "A runtime error occurred: " + ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
        logger.error("Unhandled exception: {}", ex.getMessage(), ex);

        ApiResponse<String> response = new ApiResponse<>(
                5000,
                "An unexpected error occurred: " + ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.warn("ResourceNotFoundException: {}", ex.getMessage());

        ApiResponse<String> response = new ApiResponse<>(
                4041, // Custom error code for resource not found
                "Resource not found: " + ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
