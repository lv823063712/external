package com.example.external.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.common.RequestCommon;
import com.example.external.mvp.bean.MarqueeBean;
import com.example.external.mvp.bean.ProductBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.LuckyNoticeView;
import com.example.external.utils.StatusBarUtil;
import com.example.external.utils.UserUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewingActivity extends BaseActivity implements StartInterface.StrartView {
    private TextView first_reviewing, step_tv, some_user, some_user_dollor, vip_hint, under_review_hint;
    private RelativeLayout first_show, second_reviewing;
    private int next_int = 0;
    private DialogUtils utils;
    private StartPresenter startPresenter;
    private LuckyNoticeView testVfs;
    private List<MarqueeBean.DataBean> dataBeans = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_reviewing;
    }

    @Override
    protected void initData() {
        startPresenter = new StartPresenter(this);
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        StatusBarUtil.setTextColor(this);
        initView();
        Intent intent = getIntent();
        int attestation = intent.getIntExtra("attestation", 0);
        if (attestation == 1) {
            first_reviewing.setText("1. Reviewing");
            first_show.setVisibility(View.VISIBLE);
            second_reviewing.setVisibility(View.GONE);
        }
    }

    private void initView() {
        first_reviewing = findViewById(R.id.first_reviewing);
        first_show = findViewById(R.id.first_show);
        second_reviewing = findViewById(R.id.second_reviewing);
        vip_hint = findViewById(R.id.vip_hint);
        under_review_hint = findViewById(R.id.under_review_hint);
        testVfs = findViewById(R.id.testVfs);
        under_review_hint.setText(UserUtils.getInstance().gettips_processing(mActivity));
        vip_hint.setText(UserUtils.getInstance().gettips_congratulations(mActivity));
        step_tv = findViewById(R.id.step_tvs);
        step_tv.setEnabled(false);
        netWork();
        netWorks();
    }

    @Override
    protected void setClick() {
        step_tv.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, GetMoneyActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void preLogic() {
        netWork();
    }

    private void netWork() {
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        utils.show();
        startPresenter.get(Constant.HOMEPAGE, header, body, ProductBean.class);
    }

    private void netWorks() {
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        utils.show();
        startPresenter.get(Constant.MARQUEE_URL, header, body, MarqueeBean.class);
    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof ProductBean) {
            ProductBean bean = (ProductBean) data;
            if (bean.getData() != null) {
                if (bean.getData().getPhase() == 1) {
                    new CountDownTimer(3 * 1000, 1000) {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }

                        @SuppressLint("WrongConstant")
                        @Override
                        public void onFinish() {
                            netWork();
                        }
                    }.start();
                } else if (bean.getData().getPhase() == 2) {
                    first_show.setVisibility(View.GONE);
                    second_reviewing.setVisibility(View.VISIBLE);
                    first_reviewing.setText("2. Approved");
                    step_tv.setEnabled(true);
                }
            } else {
                new CountDownTimer(3 * 1000, 1000) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onFinish() {
                        netWork();
                    }
                }.start();
            }
        } else if (data instanceof MarqueeBean) {
            MarqueeBean bean = (MarqueeBean) data;
            dataBeans.addAll(bean.getData());
            testVfs.addNotice(dataBeans);
            testVfs.startFlipping();
        }
    }

    @Override
    public void error(Object error) {
        utils.dismissDialog(utils);
        if ("HTTP 401".equals(error.toString().trim())) {
            Intent intent = new Intent(mActivity, LoginActivity.class);
            startActivity(intent);
            UserUtils.getInstance().clearAllSp(mActivity);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (utils!=null) {
            utils.dismissDialog(utils);
        }
        if (startPresenter!=null){
            startPresenter.onDatacth();
        }
    }
}