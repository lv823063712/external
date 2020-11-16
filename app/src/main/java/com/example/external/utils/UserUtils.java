package com.example.external.utils;

import android.content.Context;

/**
 * @ClassName: UserUtils
 * @Description:
 * @CreateDate: 2020/11/15 11:49
 * @Creator: lf
 */
public class UserUtils {
    private String ISEMAIL = "isEmail";//客服邮箱
    private String ISEMAILS = "isEmails";//客服备用邮箱
    private String SERVICETIME = "serviceTime";//客服时间
    private String CONGRATULATIONS = "congratulations";//审核成功
    private String PAY_CHANNEL = "pay_channel";//支付渠道

    public static UserUtils getInstance() {
        return ViewHolder.USER_UTILS;
    }

    //客服邮箱
    public void saveEmail(Context context, String email) {
        SharedPreferencesUtil.getInstance(context).putString(ISEMAIL, email);
    }

    public String getEmail(Context context) {
        return SharedPreferencesUtil.getInstance(context).getString(ISEMAIL);
    }

    public void removeEmail(Context context) {
        SharedPreferencesUtil.getInstance(context).remove(ISEMAIL);
    }

    //客服备用邮箱
    public void saveEmails(Context context, String emails) {
        SharedPreferencesUtil.getInstance(context).putString(ISEMAILS, emails);
    }

    public String getEmails(Context context) {
        return SharedPreferencesUtil.getInstance(context).getString(ISEMAILS);
    }

    public void removeEmails(Context context) {
        SharedPreferencesUtil.getInstance(context).remove(ISEMAILS);
    }

    //客服时间
    public void saveServiceTime(Context context, String serviceTime) {
        SharedPreferencesUtil.getInstance(context).putString(SERVICETIME, serviceTime);
    }

    public String getServiceTime(Context context) {
        return SharedPreferencesUtil.getInstance(context).getString(SERVICETIME);
    }

    public void removeServiceTime(Context context) {
        SharedPreferencesUtil.getInstance(context).remove(SERVICETIME);
    }

    //审核成功
    public void saveCongratulations(Context context, String Congratulations) {
        SharedPreferencesUtil.getInstance(context).putString(CONGRATULATIONS, Congratulations);
    }

    public String getCongratulations(Context context) {
        return SharedPreferencesUtil.getInstance(context).getString(CONGRATULATIONS);
    }

    public void removeCongratulations(Context context) {
        SharedPreferencesUtil.getInstance(context).remove(CONGRATULATIONS);
    }

    //支付渠道
    public void savePayChannel(Context context, String PayChannel) {
        SharedPreferencesUtil.getInstance(context).putString(PAY_CHANNEL, PayChannel);
    }

    public String getPayChannel(Context context) {
        return SharedPreferencesUtil.getInstance(context).getString(PAY_CHANNEL);
    }

    public void removePayChannel(Context context) {
        SharedPreferencesUtil.getInstance(context).remove(PAY_CHANNEL);
    }

    //清除所有本地保存数据
    public void clearAllSp(Context context) {
        SharedPreferencesUtil.getInstance(context).clearSp();
    }

    private static class ViewHolder {
        private static final UserUtils USER_UTILS = new UserUtils();
    }
}
