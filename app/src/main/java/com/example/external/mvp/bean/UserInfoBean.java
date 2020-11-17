package com.example.external.mvp.bean;

/**
 * @ClassName: UserInfoBean
 * @Description:
 * @CreateDate: 2020/11/17 13:37
 * @Creator: lf
 */

public class UserInfoBean {

    /**
     * status : 1
     * code : 0001
     * message : success
     * data : {"gender":"Male","marital":"Married","education":"Other","employment_type":"Full-time","monthly_salary":"8000～20000","monthly_family_salary":"20000～30000","id":"66c9ad0f9805dc1c25d79b74dcecc76b","mobile":"888000111","email":"00000@qq.com","ifsc_code":"545455","bank_name":"5741287","bank_account_no":"45241524324","name":"jim","birthday":"2000-09-09"}
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
         * gender : Male
         * marital : Married
         * education : Other
         * employment_type : Full-time
         * monthly_salary : 8000～20000
         * monthly_family_salary : 20000～30000
         * id : 66c9ad0f9805dc1c25d79b74dcecc76b
         * mobile : 888000111
         * email : 00000@qq.com
         * ifsc_code : 545455
         * bank_name : 5741287
         * bank_account_no : 45241524324
         * name : jim
         * birthday : 2000-09-09
         */

        private String gender;
        private String marital;
        private String education;
        private String employment_type;
        private String monthly_salary;
        private String monthly_family_salary;
        private String id;
        private String mobile;
        private String email;
        private String ifsc_code;
        private String bank_name;
        private String bank_account_no;
        private String name;
        private String birthday;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMarital() {
            return marital;
        }

        public void setMarital(String marital) {
            this.marital = marital;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getEmployment_type() {
            return employment_type;
        }

        public void setEmployment_type(String employment_type) {
            this.employment_type = employment_type;
        }

        public String getMonthly_salary() {
            return monthly_salary;
        }

        public void setMonthly_salary(String monthly_salary) {
            this.monthly_salary = monthly_salary;
        }

        public String getMonthly_family_salary() {
            return monthly_family_salary;
        }

        public void setMonthly_family_salary(String monthly_family_salary) {
            this.monthly_family_salary = monthly_family_salary;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIfsc_code() {
            return ifsc_code;
        }

        public void setIfsc_code(String ifsc_code) {
            this.ifsc_code = ifsc_code;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_account_no() {
            return bank_account_no;
        }

        public void setBank_account_no(String bank_account_no) {
            this.bank_account_no = bank_account_no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}
