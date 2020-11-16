package com.example.external.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.common.RequestCommon;
import com.example.external.mvp.bean.LoginBean;
import com.example.external.mvp.bean.SendCodeBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.mvp.requestbean.LoginRequestBean;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.UserUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginActivity extends BaseActivity implements View.OnClickListener, StartInterface.StrartView {
    private EditText user_input_phone, user_input_code;
    private TextView get_send_code, login_register;
    private DialogUtils utils;
    private StartPresenter startPresenter;
    private View top_check;
    private TextView top_text;
    private int topView = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        initView();
    }

    private void initView() {
        user_input_phone = findViewById(R.id.user_input_phone);
        user_input_code = findViewById(R.id.send_code);
        get_send_code = findViewById(R.id.get_send_code);
        login_register = findViewById(R.id.login_register);
        top_check = findViewById(R.id.top_check);
        top_text = findViewById(R.id.top_text);
    }

    @Override
    protected void setClick() {
        get_send_code.setOnClickListener(this);
        login_register.setOnClickListener(this);
        top_check.setOnClickListener(this);
    }

    @Override
    protected void preLogic() {
        startPresenter = new StartPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_check:
                if (topView == 0) {
                    top_check.setBackground(getResources().getDrawable(R.drawable.shape_login_bottom_circle_bg));
                    top_text.setTextColor(getResources().getColor(R.color.blue_041D98));
                    topView = 1;
                } else {
                    top_check.setBackground(getResources().getDrawable(R.drawable.shape_hollow_circle));
                    top_text.setTextColor(getResources().getColor(R.color.gray_929292));
                    topView = 0;
                }
                break;

            case R.id.login_register:
                Map<String, Object> headers = RequestCommon.getInstance().headers(mActivity);
                Map<String, Object> bodys = new HashMap<>();
                LoginRequestBean bean = new LoginRequestBean();
                if (user_input_phone.getText() != null && !"".equals(user_input_phone.getText().toString())) {
                    bean.setMobile("+91" + user_input_phone.getText().toString());
                } else {
                    Toast.makeText(mActivity, "Please enter your mobile phone number", Toast.LENGTH_SHORT).show();
                }
                if (user_input_code.getText() != null && !"".equals(user_input_code.getText().toString())) {
                    bean.setCode(user_input_code.getText().toString());
                } else {
                    Toast.makeText(mActivity, "Please enter the SMS verification code", Toast.LENGTH_SHORT).show();
                }
                if (topView == 0) {
                    Toast.makeText(mActivity, "Please checked Service & Privacy policy.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson gson = new Gson();
                String s = gson.toJson(bean);
                RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), s);
                utils.show();
                startPresenter.postQueryBody(Constant.LOGIN, headers, bodys, requestBody, LoginBean.class);
                break;
            case R.id.get_send_code:
                Map<String, Object> header = new HashMap<>();
                Map<String, Object> body = new HashMap<>();
                if (user_input_phone.getText() != null && !"".equals(user_input_phone.getText().toString())) {
                    body.put("mobile", user_input_phone.getText().toString());
                } else {
                    Toast.makeText(mActivity, "Please enter your mobile phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                utils.show();
                startPresenter.get(Constant.SEND_CODE, header, body, SendCodeBean.class);

                break;
            default:
                break;
        }
    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof SendCodeBean) {
            SendCodeBean bean = (SendCodeBean) data;
            Toast.makeText(mActivity, bean.getMessage(), Toast.LENGTH_SHORT).show();
            new CountDownTimer(60 * 1000, 1000) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {
                    get_send_code.setBackground(getResources().getDrawable(R.mipmap.icon_recangle_blue));
                    get_send_code.setTextColor(getResources().getColor(R.color.green_06524D));
                    String countSecond = String.valueOf(millisUntilFinished / 1000);
                    get_send_code.setText("(" + countSecond + ")");
                    get_send_code.setEnabled(false);
                }

                @SuppressLint("WrongConstant")
                @Override
                public void onFinish() {
                    get_send_code.setBackground(getResources().getDrawable(R.mipmap.icon_recangle));
                    get_send_code.setTextColor(getResources().getColor(R.color.blue_041D98));
                    get_send_code.setText("resend");
                    get_send_code.setEnabled(true);
                }
            }.start();

        } else if (data instanceof LoginBean) {
            LoginBean loginBean = (LoginBean) data;
//            UserUtils.getInstance()
//            Toast.makeText(mActivity, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            UserUtils.getInstance().saveUserId(mActivity, loginBean.getData().getId());
            UserUtils.getInstance().saveAction(mActivity, loginBean.getData().getAction());
            UserUtils.getInstance().saveMobile(mActivity, loginBean.getData().getMobile());
            UserUtils.getInstance().saveAuthorized(mActivity, loginBean.getData().getAuthorized() + "");
            UserUtils.getInstance().saveToken(mActivity, loginBean.getData().getToken());
            UserUtils.getInstance().saveName(mActivity, loginBean.getData().getName());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void error(Object error) {
        utils.dismissDialog(utils);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        utils.dismissDialog(utils);
        startPresenter.onDatacth();
    }
}