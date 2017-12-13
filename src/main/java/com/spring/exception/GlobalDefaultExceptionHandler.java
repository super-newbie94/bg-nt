package com.spring.exception;

import com.spring.response.ErrorResponse;
import org.apache.log4j.Logger;
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
    private static Logger LOGGER = Logger.getLogger(GlobalDefaultExceptionHandler.class);
    @ExceptionHandler({ BusinessException.class })
    public ResponseEntity<ErrorResponse> exception(BusinessException e) {
        ErrorResponse resp = new ErrorResponse(e.getResultCode(),
                e.getResultMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorResponse> exception(Exception e) {
        LOGGER.error("<========《系统异常》请联系管理员处理此问题========>", e);
        ErrorResponse resp = new ErrorResponse("500",
               "《系统异常》请联系管理员处理此问题");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
