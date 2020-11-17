package com.example.external.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.external.R;
import com.example.external.base.BaseFragment;
import com.example.external.common.RequestCommon;
import com.example.external.mvp.bean.UserInfoBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.ui.activity.FeedbackActivity;
import com.example.external.ui.activity.LoginActivity;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.UserUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

public class MeFragment extends BaseFragment implements View.OnClickListener, StartInterface.StrartView {

    private DialogUtils utils;
    private TextView log_out, my_name;
    private StartPresenter startPresenter;
    private SmartRefreshLayout me_refresh;

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        LinearLayout ll_ll_feedback = getActivity().findViewById(R.id.ll_feedback);
        log_out = getActivity().findViewById(R.id.log_out);
        my_name = getActivity().findViewById(R.id.my_name);
        me_refresh = getActivity().findViewById(R.id.me_refresh);
        ll_ll_feedback.setOnClickListener(v -> startActivity(new Intent(getActivity(), FeedbackActivity.class)));
        netWork();
        me_refresh.setEnableLoadMore(false);
        me_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                netWork();
                me_refresh.finishRefresh();
            }
        });


    }

    private void netWork() {
        startPresenter = new StartPresenter(this);
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        utils.show();
        startPresenter.get(Constant.PROFILE_URL, header, body, UserInfoBean.class);
    }

    @Override
    protected void initData() {
        log_out.setOnClickListener(this);

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

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof UserInfoBean) {
            UserInfoBean userInfoBean = (UserInfoBean) data;
//            Toast.makeText(mContext, userInfoBean.getMessage(), Toast.LENGTH_SHORT).show();
            my_name.setText(userInfoBean.getData().getName());
        }
    }

    @Override
    public void error(Object error) {
        utils.dismissDialog(utils);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startPresenter.onDatacth();
    }
}