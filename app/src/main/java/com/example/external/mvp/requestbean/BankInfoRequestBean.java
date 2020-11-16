package com.example.external.mvp.requestbean;

/**
 * @ClassName: BankInfoRequestBean
 * @Description:
 * @CreateDate: 2020/11/17 0:12
 * @Creator: lf
 */

public class BankInfoRequestBean {

    /**
     * ifsc_code :
     * bank_name :
     * bank_account_no :
     */

    private String ifsc_code;
    private String bank_name;
    private String bank_account_no;

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
}
