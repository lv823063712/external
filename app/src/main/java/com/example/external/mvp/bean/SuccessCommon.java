package com.example.external.mvp.bean;

/**
 * @ClassName: SuccessCommon
 * @Description:
 * @CreateDate: 2020/11/16 23:20
 * @Creator: lf
 */
public class SuccessCommon {

    /**
     * status : 1
     * code : 0001
     * message : success
     * data : null
     */

    private int status;
    private String code;
    private String message;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
