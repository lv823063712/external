package com.example.external.common;

import android.content.Context;

import com.example.external.utils.PhoneInfoUtil;

import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName: RequestCommon
 * @Description:
 * @CreateDate: 2020/11/14 18:44
 * @Creator: lf
 */
public class RequestCommon {
    public static RequestCommon getInstance() {
        return ViewHolder.REQUEST_COMMON;
    }

    private static class ViewHolder {
        private static final RequestCommon REQUEST_COMMON = new RequestCommon();
    }

    public HashMap<String, Object> headers(Context context) {

        String deviceId = null;
        try {
            deviceId = PhoneInfoUtil.readKey();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("os-type", "android");
        map.put("os-version", PhoneInfoUtil.getOsInfo());
        map.put("brand", PhoneInfoUtil.getDeviceBrand());
        map.put("model", PhoneInfoUtil.getSystemModel());
        map.put("app-version", PhoneInfoUtil.getVersionName(context));
        map.put("device-id", deviceId);
        /*map.put("adid","android");
        map.put("gps-adid","android");
        map.put("token","android");*/
        return map;
    }
}
