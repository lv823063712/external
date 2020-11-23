package com.example.external.mvp.requestbean;

/**
 * @ClassName: EventRequestBean
 * @Description:
 * @CreateDate: 2020/11/23 18:47
 * @Creator: lf
 */
public class EventRequestBean {
    private String type;
    private String loan_id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(String loan_id) {
        this.loan_id = loan_id;
    }
}
