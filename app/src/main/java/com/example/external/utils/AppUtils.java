package com.example.external.utils;

import android.content.Context;

/**
 * @ClassName: AppUtils
 * @Description:
 * @CreateDate: 2020/11/15 22:37
 * @Creator: lf
 */
public class AppUtils {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
