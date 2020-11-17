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

    private int employment_type;
    private int monthly_salary;
    private int monthly_family_salary;

    public int getEmployment_type() {
        return employment_type;
    }

    public void setEmployment_type(int employment_type) {
        this.employment_type = employment_type;
    }

    public int getMonthly_salary() {
        return monthly_salary;
    }

    public void setMonthly_salary(int monthly_salary) {
        this.monthly_salary = monthly_salary;
    }

    public int getMonthly_family_salary() {
        return monthly_family_salary;
    }

    public void setMonthly_family_salary(int monthly_family_salary) {
        this.monthly_family_salary = monthly_family_salary;
    }
}
