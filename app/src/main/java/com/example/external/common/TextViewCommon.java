package com.example.external.common;

import android.content.Context;
import android.widget.Toast;

/**
 * @ClassName: TextViewCommon
 * @Description:
 * @CreateDate: 2020/11/16 23:10
 * @Creator: lf
 */
public class TextViewCommon {
    private String et;

    public static TextViewCommon getInstance() {
        return ViewHolder.REQUEST_COMMON;
    }

    private static class ViewHolder {
        private static final TextViewCommon REQUEST_COMMON = new TextViewCommon();
    }

    public String setTextContent1(Context context, String content) {
        if (!content.equals("") && content != null) {
            et = content;
        } else {
            Toast.makeText(context, "Please choose your birthday", Toast.LENGTH_SHORT).show();
        }
        return et;
    }

    public String setTextContent2(Context context, String content) {
        if (!content.equals("") && content != null) {
            et = content;
        } else {
            Toast.makeText(context, "Please choose gender", Toast.LENGTH_SHORT).show();
        }
        return et;
    }

    public String setTextContent3(Context context, String content) {
        if (!content.equals("") && content != null) {
            et = content;
        } else {
            Toast.makeText(context, "Please choose marital status", Toast.LENGTH_SHORT).show();
        }
        return et;
    }

    public String setTextContent4(Context context, String content) {
        if (!content.equals("") && content != null) {
            et = content;
        } else {
            Toast.makeText(context, "Please select education background", Toast.LENGTH_SHORT).show();
        }
        return et;
    }

    public String setTextContent5(Context context, String content) {
        if (!content.equals("") && content != null) {
            et = content;
        } else {
            Toast.makeText(context, "Please enter email address", Toast.LENGTH_SHORT).show();
        }
        return et;
    }
}
