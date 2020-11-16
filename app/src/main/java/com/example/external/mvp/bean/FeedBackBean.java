package com.example.external.mvp.bean;

/**
 * @ClassName: LoginBean
 * @Description:
 * @CreateDate: 2020/11/14 20:21
 * @Creator: lf
 */
public class FeedBackBean {
    /**
     * status : 1
     * code : 0001
     * message : success
     * data : {"id":"22eeeef5c7bc8578620ab5f6f6aab68b","action":"login","mobile":"8881234567","authorized":0,"token":"ef5b70db692f9dfa66b0ef33a8da056d","name":"Lance"}
     */

    private int status;
    private String code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
