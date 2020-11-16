package com.example.external.mvp.requestbean;

/**
 * @ClassName: SalaryRequestBean
 * @Description:
 * @CreateDate: 2020/11/16 23:49
 * @Creator: lf
 */
public class SalaryRequestBean {

    /**
     * employment_type :
     * monthly_salary :
     * monthly_family_salary :
     */

    private String employment_type;
    private String monthly_salary;
    private String monthly_family_salary;

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
}
