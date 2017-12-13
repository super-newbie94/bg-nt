package com.spring.response;

import com.bgnt.em.BaseResultCode;

import java.io.Serializable;

/**
 * User: GaoYuan
 * Date: 17/11/21
 * Time: 10:41
 */
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -333540377328038498L;

    private String resultCode;
    private String resultMessage;

    public BaseResponse() {
        this.resultCode = BaseResultCode.OK.getValue();
        this.resultMessage = BaseResultCode.OK.getDetail();
    }

    public BaseResponse(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
