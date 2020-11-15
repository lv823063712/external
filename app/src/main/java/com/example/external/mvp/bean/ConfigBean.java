package com.example.external.mvp.bean;

import java.util.List;

/**
 * @ClassName: ConfigBean
 * @Description:
 * @CreateDate: 2020/11/15 11:23
 * @Creator: lf
 */
public class ConfigBean {


    /**
     * status : 1
     * code : 0001
     * message : success
     * data : {"dicts":[{"id":"d060be46b9e011eab7fd061e6e4502a8","name":"Other","value":1,"type":"Education"},{"id":"d07ac432b9e011eab7fd061e6e4502a8","name":"High School Diploma","value":2,"type":"Education"},{"id":"d094c749b9e011eab7fd061e6e4502a8","name":"Bachelor degree","value":3,"type":"Education"},{"id":"d0aeca27b9e011eab7fd061e6e4502a8","name":"Master Diploma","value":4,"type":"Education"},{"id":"d0c8a87db9e011eab7fd061e6e4502a8","name":"Doctoral Diploma","value":5,"type":"Education"},{"id":"d1d66854b9e011eab7fd061e6e4502a8","name":"Full-time","value":1,"type":"Employment Type"},{"id":"d2342930b9e011eab7fd061e6e4502a8","name":"Part-time job","value":2,"type":"Employment Type"},{"id":"8d039405b9df11eab7fd061e6e4502a8","name":"Male","value":1,"type":"Gender"},{"id":"8d15e267b9df11eab7fd061e6e4502a8","name":"Female","value":2,"type":"Gender"},{"id":"d0121a3db9e011eab7fd061e6e4502a8","name":"Married","value":1,"type":"Marital"},{"id":"d02c1da7b9e011eab7fd061e6e4502a8","name":"Unmarried","value":2,"type":"Marital"},{"id":"d0465ccdb9e011eab7fd061e6e4502a8","name":"Divorced","value":3,"type":"Marital"},{"id":"d2d01649b9e011eab7fd061e6e4502a8","name":"＜20000","value":1,"type":"Monthly Family Income"},{"id":"d323a20cb9e011eab7fd061e6e4502a8","name":"20000～30000","value":2,"type":"Monthly Family Income"},{"id":"d33da7dcb9e011eab7fd061e6e4502a8","name":"30000～50000","value":3,"type":"Monthly Family Income"},{"id":"d35791dcb9e011eab7fd061e6e4502a8","name":"50000～800000","value":4,"type":"Monthly Family Income"},{"id":"d37196a6b9e011eab7fd061e6e4502a8","name":"＞800000","value":5,"type":"Monthly Family Income"},{"id":"d24e1879b9e011eab7fd061e6e4502a8","name":"0～8000","value":1,"type":"Your Monthly Salary"},{"id":"d2680b02b9e011eab7fd061e6e4502a8","name":"8000～20000","value":2,"type":"Your Monthly Salary"},{"id":"d2820f70b9e011eab7fd061e6e4502a8","name":"20000～30000","value":3,"type":"Your Monthly Salary"},{"id":"d29c11f1b9e011eab7fd061e6e4502a8","name":"30000～50000","value":4,"type":"Your Monthly Salary"},{"id":"d2b5ff86b9e011eab7fd061e6e4502a8","name":"＞50000","value":5,"type":"Your Monthly Salary"}],"pay_channel":"razorpay","tips_processing":"显示到审核中,Application information has been submitted, we will review the information to complete within three minutes, please check it.","tips_congratulations":"显示到审核成功，You need to become a member to get an installment loan. After becoming a member, you will also get very low loan interest","tips_pay":"显示到支付底部，If you fail,it be refunded to you within 5 days","sys_service_time":"10: 00 am. -18: 00 pm.","sys_service_email":"Feedback邮箱，service@hqcashmart.com","sys_service_email_bak":"显示到个人中心底部，service@hqcashmart.com","adjust_code":"uj02p5i5cmww","adjust_register":"ju7rgv","adjust_login":"yhtuku","adjust_add_basicinfo":"jkq58k","adjust_add_idinfo":"t8yo8l","adjust_add_workinfo":"njjl2z","adjust_add_bankinfo":"yuqrc8","adjust_click_pay":"f2v52p","adjust_pay_success":"vwg1ed","adjust_switch":"1","branch_switch":"1","credit_amount":150000}
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
         * dicts : [{"id":"d060be46b9e011eab7fd061e6e4502a8","name":"Other","value":1,"type":"Education"},{"id":"d07ac432b9e011eab7fd061e6e4502a8","name":"High School Diploma","value":2,"type":"Education"},{"id":"d094c749b9e011eab7fd061e6e4502a8","name":"Bachelor degree","value":3,"type":"Education"},{"id":"d0aeca27b9e011eab7fd061e6e4502a8","name":"Master Diploma","value":4,"type":"Education"},{"id":"d0c8a87db9e011eab7fd061e6e4502a8","name":"Doctoral Diploma","value":5,"type":"Education"},{"id":"d1d66854b9e011eab7fd061e6e4502a8","name":"Full-time","value":1,"type":"Employment Type"},{"id":"d2342930b9e011eab7fd061e6e4502a8","name":"Part-time job","value":2,"type":"Employment Type"},{"id":"8d039405b9df11eab7fd061e6e4502a8","name":"Male","value":1,"type":"Gender"},{"id":"8d15e267b9df11eab7fd061e6e4502a8","name":"Female","value":2,"type":"Gender"},{"id":"d0121a3db9e011eab7fd061e6e4502a8","name":"Married","value":1,"type":"Marital"},{"id":"d02c1da7b9e011eab7fd061e6e4502a8","name":"Unmarried","value":2,"type":"Marital"},{"id":"d0465ccdb9e011eab7fd061e6e4502a8","name":"Divorced","value":3,"type":"Marital"},{"id":"d2d01649b9e011eab7fd061e6e4502a8","name":"＜20000","value":1,"type":"Monthly Family Income"},{"id":"d323a20cb9e011eab7fd061e6e4502a8","name":"20000～30000","value":2,"type":"Monthly Family Income"},{"id":"d33da7dcb9e011eab7fd061e6e4502a8","name":"30000～50000","value":3,"type":"Monthly Family Income"},{"id":"d35791dcb9e011eab7fd061e6e4502a8","name":"50000～800000","value":4,"type":"Monthly Family Income"},{"id":"d37196a6b9e011eab7fd061e6e4502a8","name":"＞800000","value":5,"type":"Monthly Family Income"},{"id":"d24e1879b9e011eab7fd061e6e4502a8","name":"0～8000","value":1,"type":"Your Monthly Salary"},{"id":"d2680b02b9e011eab7fd061e6e4502a8","name":"8000～20000","value":2,"type":"Your Monthly Salary"},{"id":"d2820f70b9e011eab7fd061e6e4502a8","name":"20000～30000","value":3,"type":"Your Monthly Salary"},{"id":"d29c11f1b9e011eab7fd061e6e4502a8","name":"30000～50000","value":4,"type":"Your Monthly Salary"},{"id":"d2b5ff86b9e011eab7fd061e6e4502a8","name":"＞50000","value":5,"type":"Your Monthly Salary"}]
         * pay_channel : razorpay
         * tips_processing : 显示到审核中,Application information has been submitted, we will review the information to complete within three minutes, please check it.
         * tips_congratulations : 显示到审核成功，You need to become a member to get an installment loan. After becoming a member, you will also get very low loan interest
         * tips_pay : 显示到支付底部，If you fail,it be refunded to you within 5 days
         * sys_service_time : 10: 00 am. -18: 00 pm.
         * sys_service_email : Feedback邮箱，service@hqcashmart.com
         * sys_service_email_bak : 显示到个人中心底部，service@hqcashmart.com
         * adjust_code : uj02p5i5cmww
         * adjust_register : ju7rgv
         * adjust_login : yhtuku
         * adjust_add_basicinfo : jkq58k
         * adjust_add_idinfo : t8yo8l
         * adjust_add_workinfo : njjl2z
         * adjust_add_bankinfo : yuqrc8
         * adjust_click_pay : f2v52p
         * adjust_pay_success : vwg1ed
         * adjust_switch : 1
         * branch_switch : 1
         * credit_amount : 150000
         */

        private String pay_channel;
        private String tips_processing;
        private String tips_congratulations;
        private String tips_pay;
        private String sys_service_time;
        private String sys_service_email;
        private String sys_service_email_bak;
        private String adjust_code;
        private String adjust_register;
        private String adjust_login;
        private String adjust_add_basicinfo;
        private String adjust_add_idinfo;
        private String adjust_add_workinfo;
        private String adjust_add_bankinfo;
        private String adjust_click_pay;
        private String adjust_pay_success;
        private String adjust_switch;
        private String branch_switch;
        private int credit_amount;
        private List<DictsBean> dicts;

        public String getPay_channel() {
            return pay_channel;
        }

        public void setPay_channel(String pay_channel) {
            this.pay_channel = pay_channel;
        }

        public String getTips_processing() {
            return tips_processing;
        }

        public void setTips_processing(String tips_processing) {
            this.tips_processing = tips_processing;
        }

        public String getTips_congratulations() {
            return tips_congratulations;
        }

        public void setTips_congratulations(String tips_congratulations) {
            this.tips_congratulations = tips_congratulations;
        }

        public String getTips_pay() {
            return tips_pay;
        }

        public void setTips_pay(String tips_pay) {
            this.tips_pay = tips_pay;
        }

        public String getSys_service_time() {
            return sys_service_time;
        }

        public void setSys_service_time(String sys_service_time) {
            this.sys_service_time = sys_service_time;
        }

        public String getSys_service_email() {
            return sys_service_email;
        }

        public void setSys_service_email(String sys_service_email) {
            this.sys_service_email = sys_service_email;
        }

        public String getSys_service_email_bak() {
            return sys_service_email_bak;
        }

        public void setSys_service_email_bak(String sys_service_email_bak) {
            this.sys_service_email_bak = sys_service_email_bak;
        }

        public String getAdjust_code() {
            return adjust_code;
        }

        public void setAdjust_code(String adjust_code) {
            this.adjust_code = adjust_code;
        }

        public String getAdjust_register() {
            return adjust_register;
        }

        public void setAdjust_register(String adjust_register) {
            this.adjust_register = adjust_register;
        }

        public String getAdjust_login() {
            return adjust_login;
        }

        public void setAdjust_login(String adjust_login) {
            this.adjust_login = adjust_login;
        }

        public String getAdjust_add_basicinfo() {
            return adjust_add_basicinfo;
        }

        public void setAdjust_add_basicinfo(String adjust_add_basicinfo) {
            this.adjust_add_basicinfo = adjust_add_basicinfo;
        }

        public String getAdjust_add_idinfo() {
            return adjust_add_idinfo;
        }

        public void setAdjust_add_idinfo(String adjust_add_idinfo) {
            this.adjust_add_idinfo = adjust_add_idinfo;
        }

        public String getAdjust_add_workinfo() {
            return adjust_add_workinfo;
        }

        public void setAdjust_add_workinfo(String adjust_add_workinfo) {
            this.adjust_add_workinfo = adjust_add_workinfo;
        }

        public String getAdjust_add_bankinfo() {
            return adjust_add_bankinfo;
        }

        public void setAdjust_add_bankinfo(String adjust_add_bankinfo) {
            this.adjust_add_bankinfo = adjust_add_bankinfo;
        }

        public String getAdjust_click_pay() {
            return adjust_click_pay;
        }

        public void setAdjust_click_pay(String adjust_click_pay) {
            this.adjust_click_pay = adjust_click_pay;
        }

        public String getAdjust_pay_success() {
            return adjust_pay_success;
        }

        public void setAdjust_pay_success(String adjust_pay_success) {
            this.adjust_pay_success = adjust_pay_success;
        }

        public String getAdjust_switch() {
            return adjust_switch;
        }

        public void setAdjust_switch(String adjust_switch) {
            this.adjust_switch = adjust_switch;
        }

        public String getBranch_switch() {
            return branch_switch;
        }

        public void setBranch_switch(String branch_switch) {
            this.branch_switch = branch_switch;
        }

        public int getCredit_amount() {
            return credit_amount;
        }

        public void setCredit_amount(int credit_amount) {
            this.credit_amount = credit_amount;
        }

        public List<DictsBean> getDicts() {
            return dicts;
        }

        public void setDicts(List<DictsBean> dicts) {
            this.dicts = dicts;
        }

        public static class DictsBean {
            /**
             * id : d060be46b9e011eab7fd061e6e4502a8
             * name : Other
             * value : 1
             * type : Education
             */

            private String id;
            private String name;
            private int value;
            private String type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
