package com.spring.em;

/**
 * User: GaoYuan
 * Date: 17/12/20
 * Time: 16:56
 */
public enum SysResultCode {
    LOGIN_ERROR("500001", "Username or password is incorrect");

    private String value;
    private String detail;

    SysResultCode(String value, String detail) {
        this.value = value;
        this.detail = detail;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
