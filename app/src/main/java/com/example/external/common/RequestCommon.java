package com.example.external.common;

import android.content.Context;

import com.example.external.utils.PhoneInfoUtil;
import com.example.external.utils.UserUtils;

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

    public HashMap<String, Object> headers(Context context) {

        String deviceId = null;
        deviceId = PhoneInfoUtil.readKey(context);
        HashMap<String, Object> map = new HashMap<>();
        map.put("os-type", "android");
        map.put("os-version", PhoneInfoUtil.getOsInfo());
        map.put("brand", PhoneInfoUtil.getDeviceBrand());
        map.put("model", PhoneInfoUtil.getSystemModel());
        map.put("app-version", PhoneInfoUtil.getVersionName(context));
        map.put("device-id", deviceId);
        map.put("token", UserUtils.getInstance().getToken(context));
        /*map.put("adid","android");
        map.put("gps-adid","android");
        */
        return map;
    }

    private static class ViewHolder {
        private static final RequestCommon REQUEST_COMMON = new RequestCommon();
    }


}
