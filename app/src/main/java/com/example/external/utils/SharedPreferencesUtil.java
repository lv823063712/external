package com.example.external.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {
    private static final String FILE_NAME_APPCONFIG = "shared_appconfig";

    private static SharedPreferencesUtil mSharedPreferencesUtil;
    private static SharedPreferencesUtil mAppSharedPreferencesUtil;

    private SharedPreferences mSharedPreferences;

    private SharedPreferencesUtil(Context context, String fileName) {
        mSharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesUtil getInstance(Context context) {
        if (mSharedPreferencesUtil == null) {
            mSharedPreferencesUtil = new SharedPreferencesUtil(context, context.getPackageName());
        }
        return mSharedPreferencesUtil;
    }

    public static synchronized SharedPreferencesUtil getAppInstance(Context context) {
        if (mAppSharedPreferencesUtil == null) {
            mAppSharedPreferencesUtil = new SharedPreferencesUtil(context, FILE_NAME_APPCONFIG);
        }
        return mAppSharedPreferencesUtil;
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        Editor edit = mSharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public int getInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public void putInt(String key, int value) {
        Editor edit = mSharedPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public long getLong(String key) {
        return mSharedPreferences.getLong(key, 0);
    }

    public void putLong(String key, long value) {
        Editor edit = mSharedPreferences.edit();
        edit.putLong(key, value);
        edit.commit();
    }

    public Boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public void putBoolean(String key, boolean value) {
        Editor edit = mSharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    /**
     * 通过key进行删除
     *
     * @param key
     */
    public void remove(String key) {
        Editor edit = mSharedPreferences.edit();
        edit.remove(key);
        edit.commit();
    }

    /**
     * 清楚所有数据
     */
    public void clearSp() {
        mSharedPreferences.edit().clear().commit();
    }
}
