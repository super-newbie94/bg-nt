package com.bgnt.em;

/**
 * User: GaoYuan
 * Date: 17/11/21
 * Time: 10:55
 */
public enum BaseResultCode {

    OK("000000","成功"),
    PARAMETER_ERROR("100001", "参数传递异常")




    ;

    private String value;
    private String detail;


    BaseResultCode(String value, String detail) {
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
