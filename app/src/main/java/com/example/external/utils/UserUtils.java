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
    private String USER_ID = "user_id";//用户ID
    private String ACTION = "action";//区分注册还是登陆
    private String MOBILE = "mobile";//手机号码
    private String AUTHORIZED = "authorized";//是否认证
    private String TOKEN = "token";//Token
    private String NAME = "name";//用户名

    public static UserUtils getInstance() {
        return ViewHolder.USER_UTILS;
    }

    private static class ViewHolder {
        private static final UserUtils USER_UTILS = new UserUtils();
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

    //用户id
    public void saveUserId(Context context, String userId) {
        SharedPreferencesUtil.getInstance(context).putString(USER_ID, userId);
    }

    public String getUserId(Context context) {
        return SharedPreferencesUtil.getInstance(context).getString(USER_ID);
    }

    public void removeUserId(Context context) {
        SharedPreferencesUtil.getInstance(context).remove(USER_ID);
    }

    //注册 or 登陆
    public void saveAction(Context context, String action) {
        SharedPreferencesUtil.getInstance(context).putString(ACTION, action);
    }

    public String getAction(Context context) {
        return SharedPreferencesUtil.getInstance(context).getString(ACTION);
    }

    public void removeAction(Context context) {
        SharedPreferencesUtil.getInstance(context).remove(ACTION);
    }

    //手机号码
    public void saveMobile(Context context, String mobile) {
        SharedPreferencesUtil.getInstance(context).putString(MOBILE, mobile);
    }

    public String getMobile(Context context) {
        return SharedPreferencesUtil.getInstance(context).getString(MOBILE);
    }

    public void removeMobile(Context context) {
        SharedPreferencesUtil.getInstance(context).remove(MOBILE);
    }

    //是否认证
    public void saveAuthorized(Context context, String authorized) {
        SharedPreferencesUtil.getInstance(context).putString(AUTHORIZED, authorized);
    }

    public String getAuthorized(Context context) {
        return SharedPreferencesUtil.getInstance(context).getString(AUTHORIZED);
    }

    public void removeAuthorized(Context context) {
        SharedPreferencesUtil.getInstance(context).remove(AUTHORIZED);
    }

    //TOKEN
    public void saveToken(Context context, String token) {
        SharedPreferencesUtil.getInstance(context).putString(TOKEN, token);
    }

    public String getToken(Context context) {
        return SharedPreferencesUtil.getInstance(context).getString(TOKEN);
    }

    public void removeToken(Context context) {
        SharedPreferencesUtil.getInstance(context).remove(TOKEN);
    }

    //NAME
    public void saveName(Context context, String name) {
        SharedPreferencesUtil.getInstance(context).putString(NAME, name);
    }

    public String getName(Context context) {
        return SharedPreferencesUtil.getInstance(context).getString(NAME);
    }

    public void removeName(Context context) {
        SharedPreferencesUtil.getInstance(context).remove(NAME);
    }

    //清除所有本地保存数据
    public void clearAllSp(Context context) {
        SharedPreferencesUtil.getInstance(context).clearSp();
    }


}
