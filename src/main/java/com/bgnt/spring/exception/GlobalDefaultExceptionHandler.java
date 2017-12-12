package com.bgnt.spring.exception;

import com.bgnt.spring.response.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * User: GaoYuan
 * Date: 17/11/20
 * Time: 18:38
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler extends Exception {

    @ExceptionHandler({ BusinessException.class })
    public ResponseEntity<ErrorResponse> exception(BusinessException e) {
        ErrorResponse resp = new ErrorResponse(e.getResultCode(),
                e.getResultMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorResponse> exception(Exception e) {
        System.out.println("《系统异常》请联系管理员处理此问题");
        ErrorResponse resp = new ErrorResponse("500",
                e.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
