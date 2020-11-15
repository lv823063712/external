package com.example.external.mvp.requestbean;

/**
 * @ClassName: LoginRequestBean
 * @Description:
 * @CreateDate: 2020/11/14 20:24
 * @Creator: lf
 */
public  class BaseBean {
    private String status;
    private Object dataobj;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getDataobj() {
        return dataobj;
    }

    public void setDataobj(Object dataobj) {
        this.dataobj = dataobj;
    }
}
