package com.sun.zq.error;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ErrorInfo {
    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 100;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;

    private String url;

    public ErrorInfo(Integer code, String message, String url) {
        this.code = code;
        this.message = message;
        this.url = url;
    }

    public static Integer getSUCCESS() {
        return SUCCESS;
    }

    public static Integer getERROR() {
        return ERROR;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
