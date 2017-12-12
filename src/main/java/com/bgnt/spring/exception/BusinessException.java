package com.bgnt.spring.exception;

/**
 * User: GaoYuan
 * Date: 17/11/20
 * Time: 18:41
 */
public class BusinessException extends RuntimeException {

    private String resultCode;
    private String resultMessage;
    private String detail;

    public BusinessException(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
