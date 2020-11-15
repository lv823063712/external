package com.example.external.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.PermissionUtils;
import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.config.AppStatusConstant;
import com.example.external.mvp.bean.ConfigBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.utils.AppStatusManager;
import com.yanzhenjie.permission.AndPermission;

import java.util.HashMap;
import java.util.Map;

public class StartActivity extends BaseActivity implements StartInterface.StrartView {
    private String[] strings = new String[]{
            Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppStatusManager.getInstance().setAppStatus(AppStatusConstant.STATUS_NORMAL);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setClick() {

    }

    @Override
    protected void preLogic() {
        StartPresenter startPresenter = new StartPresenter(this);
        Map<String, Object> header = new HashMap<>();
        Map<String, Object> body = new HashMap<>();
        startPresenter.get(Constant.CONFIG, header, body, ConfigBean.class);

    }

    @Override
    public void success(Object data) {
        if (data instanceof ConfigBean) {
            ConfigBean configBean = (ConfigBean) data;
            Toast.makeText(mActivity, configBean.getMessage(), Toast.LENGTH_SHORT).show();
            AndPermission.with(mActivity)
                    .runtime()
                    .permission(strings)
                    .onGranted(datas -> {
                        Intent intent = new Intent(mActivity, LoginActivity.class);
                        startActivity(intent);
                    }).onDenied(datas -> PermissionUtils.launchAppDetailsSettings()).start();
        }
    }

    @Override
    public void error(Object error) {

    }
}