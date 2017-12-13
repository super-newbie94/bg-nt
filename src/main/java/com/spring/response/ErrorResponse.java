package com.spring.response;


/**
 * User: GaoYuan
 * Date: 17/11/20
 * Time: 18:50
 */
public class ErrorResponse {
    private String resultCode;
    private String resultMessage;

    public ErrorResponse() {
    }

    public ErrorResponse(String errorCode, String resultMessage) {
        this.resultCode = errorCode;
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String errorCode) {
        this.resultCode = errorCode;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
