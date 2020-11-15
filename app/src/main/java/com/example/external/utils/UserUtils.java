package com.example.external.utils;

import android.content.Context;

/**
 * @ClassName: UserUtils
 * @Description:
 * @CreateDate: 2020/11/15 11:49
 * @Creator: lf
 */
public class UserUtils {
    private String ISEMAIL = "isEmail";
    public static UserUtils getInstance(){
        return ViewHolder.USER_UTILS;
    }

    private static class ViewHolder{
        private static final UserUtils USER_UTILS = new UserUtils();
    }

    public void saveEmail(Context context,String email){
        SharedPreferencesUtil.getInstance(context).putString(ISEMAIL,email);
    }

    public String getEmail(Context context){
        return SharedPreferencesUtil.getInstance(context).getString(ISEMAIL);
    }

    public void removeEmail(Context context){
        SharedPreferencesUtil.getInstance(context).remove(ISEMAIL);
    }
}
