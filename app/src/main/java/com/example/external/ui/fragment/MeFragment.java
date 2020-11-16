package com.example.external.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.external.R;
import com.example.external.base.BaseFragment;
import com.example.external.common.RequestCommon;
import com.example.external.mvp.bean.ConfigBean;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.ui.activity.FeedbackActivity;
import com.example.external.ui.activity.LoginActivity;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.UserUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.headportrait)
    ImageView headportrait;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.my_profile)
    ImageView myProfile;
    @BindView(R.id.my_feedback)
    ImageView myFeedback;
    @BindView(R.id.ll_feedback)
    LinearLayout ll_feedback;
    @BindView(R.id.my_customer)
    ImageView myCustomer;
    @BindView(R.id.my_aboutus)
    ImageView myAboutus;
    private DialogUtils utils;
    private TextView log_out;

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        LinearLayout ll_ll_feedback = getActivity().findViewById(R.id.ll_feedback);
        log_out = getActivity().findViewById(R.id.log_out);
        ll_ll_feedback.setOnClickListener(v -> startActivity(new Intent(getActivity(), FeedbackActivity.class)));
    }

    @Override
    protected void initData() {
        log_out.setOnClickListener(this);
//        myProfile.setOnClickListener(this);
//        ll_feedback.setOnClickListener(this);
//        myCustomer.setOnClickListener(this);
//        myAboutus.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.my_profile, R.id.ll_feedback, R.id.my_customer, R.id.my_aboutus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.log_out:
                UserUtils.getInstance().clearAllSp(mActivity);
                startActivity(new Intent(mActivity, LoginActivity.class));
                mActivity.finish();
                break;
            case R.id.my_profile:
                break;
            case R.id.ll_feedback:
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.my_customer:
                break;
            case R.id.my_aboutus:
                break;
            default:
                break;
        }
    }
}