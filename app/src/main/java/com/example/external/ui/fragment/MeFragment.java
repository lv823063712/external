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
import com.example.external.ui.activity.MyProfileActivity;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.UserUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;
import java.util.Map;

public class MeFragment extends BaseFragment implements View.OnClickListener, StartInterface.StrartView {

    private DialogUtils utils;
    private TextView log_out, my_name, bottom_wenan;
    private StartPresenter startPresenter;
    private SmartRefreshLayout me_refresh;
    private LinearLayout my_profile, my_customer, my_aboutus, ll_feedback;
    private View title_view;

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        startPresenter = new StartPresenter(this);
        title_view = getActivity().findViewById(R.id.title_view);
        ImmersionBar.with(this)
                //解决软键盘与底部输入框冲突问题
                .keyboardEnable(true)
                .statusBarView(title_view)
                .statusBarDarkFont(true, 0.2f)
                .titleBarMarginTop(title_view)
                .init();
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        startPresenter = new StartPresenter(this);
        ll_feedback = getActivity().findViewById(R.id.ll_feedback);
        log_out = getActivity().findViewById(R.id.log_out);
        my_name = getActivity().findViewById(R.id.my_name);
        me_refresh = getActivity().findViewById(R.id.me_refresh);
        my_profile = getActivity().findViewById(R.id.my_profile);
        my_customer = getActivity().findViewById(R.id.my_customer);
        my_aboutus = getActivity().findViewById(R.id.my_aboutus);
        bottom_wenan = getActivity().findViewById(R.id.bottom_wenan);
        bottom_wenan.setText(UserUtils.getInstance().getsys_service_email_bak(mActivity));
        log_out.setOnClickListener(this);
        my_profile.setOnClickListener(this);
        ll_feedback.setOnClickListener(this);
//        my_customer.setOnClickListener(this);
//        my_aboutus.setOnClickListener(this);
        netWork();
        me_refresh.setEnableLoadMore(false);
        me_refresh.setOnRefreshListener(refreshLayout -> {
            netWork();
            me_refresh.finishRefresh();
        });

    }

    private void netWork() {
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        utils.show();
        startPresenter.get(Constant.PROFILE_URL, header, body, UserInfoBean.class);
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.log_out:
                UserUtils.getInstance().clearAllSp(mActivity);
                startActivity(new Intent(mActivity, LoginActivity.class));
                mActivity.finish();
                break;
            case R.id.my_profile:
                startActivity(new Intent(getActivity(), MyProfileActivity.class));
                break;
            case R.id.ll_feedback:
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            /*case R.id.my_customer:
                break;
            case R.id.my_aboutus:
                break;*/
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
        if ("HTTP 401".equals(error.toString().trim())) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            UserUtils.getInstance().clearAllSp(mActivity);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (utils != null) {
            utils.dismissDialog(utils);
        }
        if (startPresenter!=null) {
            startPresenter.onDatacth();
        }
    }
}