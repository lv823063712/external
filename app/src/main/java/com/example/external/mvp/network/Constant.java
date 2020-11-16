package com.example.external.mvp.network;

/**
 * @ClassName: Constant
 * @Description: Interface to deposit class
 * @CreateDate: 2020/11/14 16:34
 * @Creator: lf
 */
public class Constant {

    //配置信息
    public static final String CONFIG_URL = "config";
    //首页
    public static final String HOMEPAGE = "product";
    //验证码
    public static final String SEND_CODE = "user/sms";
    //登录
    public static final String LOGIN = "user/login";
    //查询订单
    public static final String PAYLIST_URL = "cashfree/query";
    //反馈
    public static final String FEEDBACK_URL = "feedback";
    //跑马灯
    public static final String MARQUEE_URL = "marquee";
    //创建razorpay订单
    public static final String CREATERAZORPAY_URL = "razorpay/create";
    ///创建cashfree订单
    public static final String CREATECASHFREEPAY_URL = "cashfree/create";
    //埋点
    public static final String TREVENT_URL = "track/event";
    //获取用户信息
    public static final String PROFILE_URL = "profile";
    //个人资料提交-BANKINFO
    public static final String UPBANKINFO_URL = "profile/update_bankinfo";
    //个人资料提交-WORKINFO
    public static final String UPWORKINFO_URL = "profile/update_workinfo";
    //个人资料提交-BASEINFO
    public static final String UPBASEINFO_URL = "profile/update_baseinfo";
    //个人资料提交-IDINFO
    public static final String UPIDINFO_URL = "profile/update_idinfo";

    private static final String TEST_BASE_URL = "http://test-api.hqcashmart.com/v3/public/index.php/";

    public static String getApiUrl() {
        return TEST_BASE_URL;
    }
}
