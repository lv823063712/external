package com.example.external.common;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @ClassName: SystemCommon
 * @Description:
 * @CreateDate: 2020/11/16 10:04
 * @Creator: lf
 */
public class SystemCommon {
    public static SystemCommon getInstance() {
        return SystemCommon.ViewHolder.SYSTEM_COMMON;
    }

    private static class ViewHolder {
        private static final SystemCommon SYSTEM_COMMON = new SystemCommon();
    }

    public void keyBoard(Activity mActivity) {
        View view = mActivity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
