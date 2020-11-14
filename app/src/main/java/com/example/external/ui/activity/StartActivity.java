package com.example.external.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.PermissionUtils;
import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.config.AppStatusConstant;
import com.example.external.utils.AppStatusManager;
import com.yanzhenjie.permission.AndPermission;

public class StartActivity extends BaseActivity {
    private TextView login_start;
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
        login_start = findViewById(R.id.login_start);
        AndPermission.with(mActivity)
                .runtime()
                .permission(strings)
                .onGranted(data -> {
                }).onDenied(data -> PermissionUtils.launchAppDetailsSettings()).start();
    }

    @Override
    protected void setClick() {
        login_start.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, LoginActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void preLogic() {


    }
}