package com.example.external.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.external.R;
import com.example.external.base.BaseFragment;
import com.example.external.common.RequestCommon;
import com.example.external.mvp.bean.ConfigBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.ui.activity.GetMoneyActivity;
import com.example.external.ui.activity.IdentificationActivity;
import com.example.external.utils.DialogUtils;

import java.util.HashMap;
import java.util.Map;


public class HomePageFragment extends BaseFragment implements StartInterface.StrartView {

    private DialogUtils utils;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView() {
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        TextView Borrow = mActivity.findViewById(R.id.Borrow);
        AppCompatImageView test = mActivity.findViewById(R.id.test);
        Borrow.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, GetMoneyActivity.class);
            startActivity(intent);
        });
        test.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, IdentificationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {
        StartPresenter startPresenter = new StartPresenter(this);
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        utils.show();
        startPresenter.get(Constant.HOMEPAGE, header, body, ConfigBean.class);

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof ConfigBean){
            ConfigBean configBean = new ConfigBean();
            Toast.makeText(mContext, configBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void error(Object error) {
        utils.dismissDialog(utils);
    }
}