package com.example.external.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
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
import com.example.external.utils.DataUtils;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.LuckyNoticeView;
import com.example.external.utils.StatusBarUtil;
import com.example.external.utils.UserUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewingActivity extends BaseActivity implements StartInterface.StrartView {
    private TextView first_reviewing, step_tv, vip_hint, under_review_hint, borrow_money_limit;
    private RelativeLayout first_show, second_reviewing;
    private int next_int = 0;
    private DialogUtils utils;
    private StartPresenter startPresenter;
    private LuckyNoticeView testVfs;
    private List<MarqueeBean.DataBean> dataBeans = new ArrayList<>();
    private ArrayList<ProductBean> beans = new ArrayList<>();
    private String moneys;

    @Override
    protected int getLayout() {
        return R.layout.activity_reviewing;
    }

    @SuppressLint("SetTextI18n")
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
        borrow_money_limit = findViewById(R.id.borrow_money_limit);
        vip_hint = findViewById(R.id.vip_hint);
        under_review_hint = findViewById(R.id.under_review_hint);
        testVfs = findViewById(R.id.testVfs);
        under_review_hint.setText(UserUtils.getInstance().gettips_processing(mActivity));
        Log.e("Show up in the audit", UserUtils.getInstance().gettips_processing(mActivity));
        step_tv = findViewById(R.id.step_tvs);
        step_tv.setEnabled(false);
        netWork();
    }

    @Override
    protected void setClick() {
        step_tv.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, GetMoneyActivity.class);
            intent.putParcelableArrayListExtra("ints", beans);
            intent.putExtra("money", moneys);
            startActivity(intent);
            backActivity();
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

    @SuppressLint("SetTextI18n")
    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof ProductBean) {
            ProductBean bean = (ProductBean) data;
            beans.add(bean);
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
                    netWorks();
                    for (int i = 0; i < bean.getData().getLimits().size(); i++) {
                        if (bean.getData().getLimits().get(i).getIs_default() == 1) {
                            moneys = "₹" + DataUtils.addComma(bean.getData().getLimits().get(i).getAmount() + "");
                            borrow_money_limit.setText("₹" + DataUtils.addComma(bean.getData().getLimits().get(i).getAmount() + ""));
                            break;
                        }
                    }
                    first_show.setVisibility(View.GONE);
                    second_reviewing.setVisibility(View.VISIBLE);
                    first_reviewing.setText("2. Approved");
                    step_tv.setEnabled(true);
                    vip_hint.setText(UserUtils.getInstance().gettips_congratulations(mActivity));
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
        if (error.toString().trim().equals("HTTP 401")) {
            Intent intent = new Intent(mActivity, LoginActivity.class);
            startActivity(intent);
        }
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