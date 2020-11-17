package com.example.external.ui.activity;

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
import com.example.external.mvp.utils.GsonUtil;
import com.example.external.mvp.utils.LogUtil;
import com.example.external.utils.DialogUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener, StartInterface.StrartView {

    @BindView(R.id.feed_back)
    ImageView feedBack;
    @BindView(R.id.feed_relative)
    RelativeLayout feedRelative;
    @BindView(R.id.feed_text)
    TextView feedText;
    @BindView(R.id.add_content)
    EditText addContent;
    @BindView(R.id.feed_edit)
    RelativeLayout feedEdit;
    private ImageView mFeedBack;
    private DialogUtils utils;
    private StartPresenter startPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initData() {
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        initView();
    }

    private void initView() {
        mFeedBack = mActivity.findViewById(R.id.feed_back);
        mFeedBack.setOnClickListener(this);
    }

    @Override
    protected void setClick() {

    }

    @Override
    protected void preLogic() {
        startPresenter = new StartPresenter(this);
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        if (!TextUtils.isEmpty(addContent.getText())) {
            body.put("mobile", addContent.getText().toString());
        } else {
            Toast.makeText(mActivity, "Please fill in the feedback", Toast.LENGTH_SHORT).show();
            return;
        }
        startPresenter.get(Constant.HOMEPAGE, header, body, ConfigBean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feed_back:
                finish();
                break;
        }
    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof FeedBackBean) {
            FeedBackBean bean = (FeedBackBean) data;
            Toast.makeText(mActivity, bean.getMessage(), Toast.LENGTH_SHORT).show();
            LogUtil.d("FeedBack+-+", GsonUtil.beanToJson(bean));
            finish();
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