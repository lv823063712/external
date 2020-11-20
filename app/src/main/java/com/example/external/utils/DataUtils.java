package com.example.external.utils;

import java.text.DecimalFormat;

/**
 * 页面: 数据处理
 * lvfei
 */
public class DataUtils {
    /**
     * 将每三个数字加上逗号处理,最后保留两位小数（通常使用金额方面的编辑）示例：9，702.44
     *
     * @param str
     * @return
     */
    public static String addCommaDots(String str) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern(",##0.00");
        return myformat.format(Double.parseDouble(str));

    }

    /**
     * 给数字每三位加一个逗号的处理
     *
     * @param str
     * @return
     */
    public static String addComma(String str) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("#,###");
        return myformat.format(Double.parseDouble(str));

    }


    /**
     * 将每三个数字加上逗号处理,最多保留两位小数（通常使用金额方面的编辑）示例：9，702.44
     *
     * @param text
     * @return
     */
    public static String dataFormat(String text) {
        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {//含有小数
            if (text.length() - text.indexOf(".") - 1 == 0) {//含有一位小数
                df = new DecimalFormat("###,##0.");
            } else if (text.length() - text.indexOf(".") - 1 == 1) {//含有两位小数
                df = new DecimalFormat("###,##0.0");
            } else {//含有两位以上的小数
                df = new DecimalFormat("###,##0.00");
            }
        } else {//只有整数部分
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);

    }

}
