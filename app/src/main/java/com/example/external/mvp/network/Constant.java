package com.example.external.mvp.network;

/**
 * @ClassName: Constant
 * @Description: Interface to deposit class
 * @CreateDate: 2020/11/14 16:34
 * @Creator: lf
 */
public class Constant {

    private static final String TEST_BASE_URL = "http://test-api.hqcashmart.com/v3/public/index.php/";


    public static String getApiUrl() {
        return TEST_BASE_URL;
    }

    public static final String HOMEPAGE = "product";
    public static final String SEND_CODE = "user/sms";
    public static final String LOGIN = "user/login";

}
