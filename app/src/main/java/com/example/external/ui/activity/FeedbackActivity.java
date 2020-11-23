package com.example.external.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.common.RequestCommon;
import com.example.external.mvp.bean.ConfigBean;
import com.example.external.mvp.bean.FeedBackBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.mvp.requestbean.FeedbackRequestBean;
import com.example.external.mvp.utils.GsonUtil;
import com.example.external.mvp.utils.LogUtil;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.StatusBarUtil;
import com.example.external.utils.UserUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener, StartInterface.StrartView {


    private ImageView feed_msg_back, feed_images;
    private EditText add_content;
    private TextView feed_save;
    private TextView tv_feedtime;
    private DialogUtils utils;
    private StartPresenter startPresenter;
    private TextView feed_text;

    @Override
    protected int getLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initData() {
        startPresenter = new StartPresenter(this);
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        StatusBarUtil.setTextColor(this);
        initView();
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        feed_msg_back = mActivity.findViewById(R.id.feed_msg_back);
        feed_images = mActivity.findViewById(R.id.feed_images);
        feed_text = mActivity.findViewById(R.id.feed_text);
        add_content = mActivity.findViewById(R.id.add_content);
        feed_save = mActivity.findViewById(R.id.feed_save);
        tv_feedtime = mActivity.findViewById(R.id.tv_feedtime);
//        tv_feedtime.setText( UserUtils.getInstance().getServiceTime(this));
        feed_text.setText(UserUtils.getInstance().getServiceTime(this)+ "\n\n" +
                "E-mail: " + UserUtils.getInstance().getsys_service_email(mActivity));
    }

    @Override
    protected void setClick() {
        feed_msg_back.setOnClickListener(this);
        feed_save.setOnClickListener(this);
    }

    @Override
    protected void preLogic() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feed_save:
                Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
                Map<String, Object> body = new HashMap<>();
                FeedbackRequestBean bean = new FeedbackRequestBean();
                if (add_content.getText() != null && !add_content.getText().toString().equals("")) {
                    bean.setContent(add_content.getText().toString());
                } else {
                    Toast.makeText(mActivity, "Please fill in the feedback", Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson gson = new Gson();
                String s = gson.toJson(bean);
                RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), s);
                utils.show();
                startPresenter.postQueryBody(Constant.FEEDBACK_URL, header, body, requestBody, FeedBackBean.class);
                break;
            case R.id.feed_msg_back:
                backActivity();
                break;
            default:break;
        }
    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof FeedBackBean) {
            FeedBackBean bean = (FeedBackBean) data;
            Toast.makeText(mActivity, bean.getMessage(), Toast.LENGTH_SHORT).show();
            backActivity();
        }
    }

    @Override
    public void error(Object error) {
        utils.dismissDialog(utils);
        if (error.toString().trim().contains("401")) {
            Intent intent = new Intent(mActivity, LoginActivity.class);
            startActivity(intent);
            backActivity();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (utils!=null) {
            utils.dismissDialog(utils);
        }
        if (startPresenter!=null) {
            startPresenter.onDatacth();
        }
    }


}