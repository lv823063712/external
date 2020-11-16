package com.example.external.mvp.bean;

/**
 * @ClassName: LoginBean
 * @Description:
 * @CreateDate: 2020/11/14 20:21
 * @Creator: lf
 */
public class LoginBean {
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
        /**
         * id : 22eeeef5c7bc8578620ab5f6f6aab68b
         * action : login
         * mobile : 8881234567
         * authorized : 0
         * token : ef5b70db692f9dfa66b0ef33a8da056d
         * name : Lance
         */

        private String id;
        private String action;
        private String mobile;
        private int authorized;
        private String token;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getAuthorized() {
            return authorized;
        }

        public void setAuthorized(int authorized) {
            this.authorized = authorized;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
