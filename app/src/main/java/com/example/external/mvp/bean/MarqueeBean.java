package com.example.external.mvp.bean;

import java.util.List;

/**
 * @ClassName: MarqueeBean
 * @Description:
 * @CreateDate: 2020/11/16 19:37
 * @Creator: lf
 */

public class MarqueeBean {

    /**
     * status : 1
     * code : 0001
     * message : success
     * data : [{"mobile":"995****268","text":"become a member and just borrowed \u20b970000"},{"mobile":"901****532","text":"become a member and just borrowed \u20b990000"},{"mobile":"755****916","text":"become a member and just borrowed \u20b9130000"},{"mobile":"942****460","text":"become a member and just borrowed \u20b9100000"},{"mobile":"942****692","text":"become a member and just borrowed \u20b9150000"},{"mobile":"987****183","text":"become a member and just borrowed \u20b990000"},{"mobile":"901****585","text":"become a member and just borrowed \u20b9140000"},{"mobile":"987****350","text":"become a member and just borrowed \u20b980000"},{"mobile":"987****280","text":"become a member and just borrowed \u20b9100000"},{"mobile":"872****245","text":"become a member and just borrowed \u20b9120000"}]
     */

    private int status;
    private String code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mobile : 995****268
         * text : become a member and just borrowed ₹70000
         */

        private String mobile;
        private String text;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
