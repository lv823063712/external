package com.example.external.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.PermissionUtils;
import com.example.external.R;
import com.example.external.base.BaseActivitys;
import com.example.external.config.AppStatusConstant;
import com.example.external.mvp.bean.ConfigBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.ui.view.CustomDialog;
import com.example.external.utils.AppStatusManager;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.PhoneInfoUtil;
import com.example.external.utils.UserUtils;
import com.yanzhenjie.permission.AndPermission;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StartActivity extends BaseActivitys implements StartInterface.StrartView {
    private StartPresenter startPresenter;
    private CustomDialog dialog;
    private DialogUtils utils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppStatusManager.getInstance().setAppStatus(AppStatusConstant.STATUS_NORMAL);

        PhoneInfoUtil.readKey(mActivity);


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initData() {
        startPresenter = new StartPresenter(this);
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
    }

    @Override
    protected void setClick() {

    }

    @Override
    protected void preLogic() {
        network();

    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof ConfigBean) {
            ConfigBean configBean = (ConfigBean) data;
            if (configBean.getStatus() == 1) {
                UserUtils.getInstance().saveEmail(mActivity, configBean.getData().getSys_service_email());
                UserUtils.getInstance().saveEmails(mActivity, configBean.getData().getSys_service_email_bak());
                UserUtils.getInstance().saveServiceTime(mActivity, configBean.getData().getSys_service_time());
                UserUtils.getInstance().savePayChannel(mActivity, configBean.getData().getPay_channel());
                UserUtils.getInstance().savetips_processing(mActivity, configBean.getData().getTips_processing());
                UserUtils.getInstance().savetips_congratulations(mActivity, configBean.getData().getTips_congratulations());
                UserUtils.getInstance().savetips_pay(mActivity, configBean.getData().getTips_pay());
                UserUtils.getInstance().savesys_service_email_bak(mActivity, configBean.getData().getSys_service_email_bak());
                UserUtils.getInstance().savesys_service_email(mActivity, configBean.getData().getSys_service_email());
                UserUtils.getInstance().savesys_service_time(mActivity, configBean.getData().getSys_service_time());

                if (UserUtils.getInstance().getToken(mActivity).length() == 0) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                } else {
                    startActivity(new Intent(mActivity, MainActivity.class));
                }
                backActivity();
            } else if (configBean.getStatus() == 0) {
                Toast.makeText(mActivity, configBean.getMessage(), Toast.LENGTH_SHORT).show();
                showErrorDialog();
            }
        }
    }

    @Override
    public void error(Object error) {
        utils.dismissDialog(utils);
        showErrorDialog();
    }

    //登陆失败弹窗
    @SuppressLint("SetTextI18n")
    private void showErrorDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
        CustomDialog.Builder mBuilder = new CustomDialog.Builder(mActivity);
        CustomDialog.Builder view = mBuilder.view(R.layout.dialog_start_error_hint);
        dialog = mBuilder
                .heightDimenRes(ViewGroup.LayoutParams.WRAP_CONTENT)
                .widthDimenRes(ViewGroup.LayoutParams.WRAP_CONTENT)
                .style(R.style.Dialog)
                .setGravity(Gravity.CENTER)
                .cancelTouchout(true)
                .build();
        TextView tuxing = view.getView().findViewById(R.id.tuxing);
        tuxing.setOnClickListener(v -> network());
        dialog.show();
    }

    private void network() {
        Map<String, Object> header = new HashMap<>();
        Map<String, Object> body = new HashMap<>();
        utils.show();
        startPresenter.get(Constant.CONFIG_URL, header, body, ConfigBean.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (utils != null) {
            utils.dismissDialog(utils);
        }
        if (startPresenter != null) {
            startPresenter.onDatacth();
        }
    }
}