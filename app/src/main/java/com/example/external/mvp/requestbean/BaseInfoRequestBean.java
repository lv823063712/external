package com.example.external.mvp.requestbean;

/**
 * @ClassName: BaseInfoRequestBean
 * @Description:
 * @CreateDate: 2020/11/16 23:07
 * @Creator: lf
 */

public class BaseInfoRequestBean {

    /**
     * gender :
     * marital :
     * education :
     * email :
     * name :
     * birthday :
     */

    private int gender;
    private int marital;
    private int education;
    private String email;
    private String name;
    private String birthday;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getMarital() {
        return marital;
    }

    public void setMarital(int marital) {
        this.marital = marital;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
