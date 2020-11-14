package com.example.external.ui.activity;

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
        user_input_code = findViewById(R.id.user_input_password);
        get_send_code = findViewById(R.id.get_send_code);
        login_register = findViewById(R.id.login_register);

    }

    @Override
    protected void setClick() {
        get_send_code.setOnClickListener(this);
        login_register.setOnClickListener(this);
    }

    @Override
    protected void preLogic() {
        startPresenter = new StartPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_register:
                Map<String, Object> headers = RequestCommon.getInstance().headers(mActivity);
                Map<String, Object> bodys = new HashMap<>();
                LoginRequestBean bean = new LoginRequestBean();
                if (user_input_phone.getText() != null && !user_input_phone.getText().toString().equals("")) {
                    bean.setMobile(user_input_phone.getText().toString());
                } else {
                    Toast.makeText(mActivity, "Please enter your mobile phone number", Toast.LENGTH_SHORT).show();
                }
                if (user_input_code.getText() != null && !user_input_code.getText().toString().equals("")) {
                    bean.setCode("8888");
                } else {
                    Toast.makeText(mActivity, "Please enter the SMS verification code", Toast.LENGTH_SHORT).show();
                }
                Gson gson = new Gson();
                String s = gson.toJson(bean);
                RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), s);
                startPresenter.postQueryBody(Constant.LOGIN, headers, bodys, requestBody, LoginBean.class);
                break;
            case R.id.get_send_code:
                Map<String, Object> header = new HashMap<>();
                Map<String, Object> body = new HashMap<>();
                if (user_input_phone.getText() != null && !user_input_phone.getText().toString().equals("")) {
                    body.put("mobile", user_input_phone.getText().toString());
                } else {
                    Toast.makeText(mActivity, "Please enter your mobile phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                utils.show();
                startPresenter.get(Constant.SEND_CODE, header, body, SendCodeBean.class);
                break;
        }
    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof SendCodeBean) {
            SendCodeBean bean = (SendCodeBean) data;
            Toast.makeText(mActivity, bean.getMessage(), Toast.LENGTH_SHORT).show();
            user_input_code.setText(bean.getCode());
        } else if (data instanceof LoginBean) {
            LoginBean loginBean = (LoginBean) data;
            Toast.makeText(mActivity, loginBean.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void error(Object error) {

    }
}