package com.example.external.mvp.bean;

/**
 * @ClassName: GetMoneyBean
 * @Description:
 * @CreateDate: 2020/11/18 14:27
 * @Creator: lf
 */

public class GetMoneyBean {

    /**
     * status : 1
     * code : 0001
     * message : success
     * data : {"app_key":"rzp_test_jkN78QfHZBE7Ei","order_id":"test_20201118114646738911","razorpay_order_id":"order_G2WrhMuuAU7J7X","amount":29700,"currency":"INR","contact":"+91123444","email":"111111"}
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
         * app_key : rzp_test_jkN78QfHZBE7Ei
         * order_id : test_20201118114646738911
         * razorpay_order_id : order_G2WrhMuuAU7J7X
         * amount : 29700
         * currency : INR
         * contact : +91123444
         * email : 111111
         */

        private String app_key;
        private String order_id;
        private String razorpay_order_id;
        private int amount;
        private String currency;
        private String contact;
        private String email;

        public String getApp_key() {
            return app_key;
        }

        public void setApp_key(String app_key) {
            this.app_key = app_key;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getRazorpay_order_id() {
            return razorpay_order_id;
        }

        public void setRazorpay_order_id(String razorpay_order_id) {
            this.razorpay_order_id = razorpay_order_id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
