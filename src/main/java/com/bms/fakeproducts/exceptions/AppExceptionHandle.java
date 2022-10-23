package com.bms.fakeproducts.exceptions;

import com.bms.fakeproducts.core.utilities.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ErrorResult> handleNotFoundException(NotFoundException notfoundException) {
        ErrorResult exceptionResponse = new ErrorResult(notfoundException.getMessage(), LocalDateTime.now(), "404");
        return new ResponseEntity<ErrorResult>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AllReadyException.class})
    public ResponseEntity<ErrorResult> handleAllReadyException(AllReadyException allReadyException) {
        ErrorResult exceptionResponse = new ErrorResult(allReadyException.getMessage(),
                LocalDateTime.now(), "400");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResult> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        ErrorResult exceptionResponse = new ErrorResult(illegalArgumentException.getMessage(),
                LocalDateTime.now(), "400");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {FieldNotEmptyException.class})
    public ResponseEntity<ErrorResult> handleFieldNotEmptyException(FieldNotEmptyException fieldNotEmptyException) {
        ErrorResult exceptionResponse = new ErrorResult(fieldNotEmptyException.getMessage(),
                LocalDateTime.now(), "400");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
