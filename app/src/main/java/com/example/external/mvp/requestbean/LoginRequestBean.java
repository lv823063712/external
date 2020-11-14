package com.example.external.mvp.requestbean;

/**
 * @ClassName: LoginRequestBean
 * @Description:
 * @CreateDate: 2020/11/14 20:24
 * @Creator: lf
 */
public class LoginRequestBean {

    /**
     * mobile : 8881234567
     * code : 8888
     */

    private String mobile;
    private String code;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
