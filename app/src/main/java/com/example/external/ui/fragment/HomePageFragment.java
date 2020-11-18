package com.example.external.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.external.R;
import com.example.external.base.BaseFragment;
import com.example.external.common.RequestCommon;
import com.example.external.mvp.bean.MarqueeBean;
import com.example.external.mvp.bean.ProductBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.ui.activity.GetMoneyActivity;
import com.example.external.ui.activity.IdentificationActivity;
import com.example.external.ui.activity.LoginActivity;
import com.example.external.ui.activity.ReviewingActivity;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.LuckyNoticeView;
import com.example.external.utils.UserUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomePageFragment extends BaseFragment implements StartInterface.StrartView, View.OnClickListener {

    private DialogUtils utils;
    private TextView home_some_user, reduce_money, increase_money, home_borrow_money, borrow, home_some_user_content;
    private LuckyNoticeView testVf;
    private List<MarqueeBean.DataBean> dataBeans = new ArrayList<>();
    private ArrayList<ProductBean> beans = new ArrayList<>();
    private int money_show = 1;
    private int status;
    private int phase;
    private SmartRefreshLayout home_page_refresh;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView() {
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        borrow = mActivity.findViewById(R.id.Borrow);
        home_some_user = mActivity.findViewById(R.id.home_some_user);
        reduce_money = mActivity.findViewById(R.id.reduce_money);
        increase_money = mActivity.findViewById(R.id.increase_money);
        home_borrow_money = mActivity.findViewById(R.id.home_borrow_money);
        testVf = mActivity.findViewById(R.id.testVf);
        home_page_refresh = mActivity.findViewById(R.id.home_page_refresh);
        home_some_user_content = mActivity.findViewById(R.id.home_some_user_content);
        AppCompatImageView test = mActivity.findViewById(R.id.test);
        initClick();
    }

    private void initClick() {
        reduce_money.setOnClickListener(this);
        increase_money.setOnClickListener(this);
        borrow.setOnClickListener(this);
        home_page_refresh.setEnableLoadMore(false);
        home_page_refresh.setOnRefreshListener(refreshLayout -> {
            netWork();
            home_page_refresh.finishRefresh();
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Borrow:
                if (status == 0) {
                    if (phase == 0) {
                        Intent intent = new Intent(mActivity, GetMoneyActivity.class);
                        intent.putParcelableArrayListExtra("ints", beans);
                        startActivity(intent);
                    } else if (phase == 1) {
                        Intent intent = new Intent(mActivity, ReviewingActivity.class);
                        intent.putExtra("attestation", phase);
                        startActivity(intent);
                    } else if (phase == 2) {
                        Intent intent = new Intent(mActivity, GetMoneyActivity.class);
                        intent.putParcelableArrayListExtra("ints", beans);
                        startActivity(intent);
                    } else if (phase == 3) {
                        Intent intent = new Intent(mActivity, GetMoneyActivity.class);
                        intent.putParcelableArrayListExtra("ints", beans);
                        startActivity(intent);
                    }
                } else if (status == 1) {
                    Intent intent = new Intent(mActivity, IdentificationActivity.class);
                    intent.putExtra("next_step", status);
                    startActivity(intent);
                } else if (status == 2) {
                    Intent intent = new Intent(mActivity, IdentificationActivity.class);
                    intent.putExtra("next_step", status);
                    startActivity(intent);
                } else if (status == 3) {
                    Intent intent = new Intent(mActivity, IdentificationActivity.class);
                    intent.putExtra("next_step", status);
                    startActivity(intent);
                }
                break;
            case R.id.reduce_money:
                if (money_show == 3) {
                    home_borrow_money.setText("₹ 80,000");
                    money_show = 2;
                } else if (money_show == 2) {
                    home_borrow_money.setText("₹ 50,000");
                    money_show = 1;
                } else if (money_show == 1) {
                    home_borrow_money.setText("₹ 30,000");
                }
                break;
            case R.id.increase_money:
                if (money_show == 1) {
                    home_borrow_money.setText("₹ 50,000");
                    money_show = 2;
                } else if (money_show == 2) {
                    home_borrow_money.setText("₹ 80,000");
                    money_show = 3;
                } else if (money_show == 3) {
                    home_borrow_money.setText("₹ 150,000");
                }
                break;
        }
    }

    @Override
    protected void initData() {
        netWork();
    }

    private void netWork() {
        StartPresenter startPresenter = new StartPresenter(this);
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        utils.show();
        startPresenter.get(Constant.HOMEPAGE, header, body, ProductBean.class);
        startPresenter.get(Constant.MARQUEE_URL, header, body, MarqueeBean.class);
    }

    @Override
    protected void loadData() {
    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof ProductBean) {
            ProductBean productBean = (ProductBean) data;
            beans.add(productBean);
            status = productBean.getData().getCertification();
            phase = productBean.getData().getCertification();
        } else if (data instanceof MarqueeBean) {
            MarqueeBean bean = (MarqueeBean) data;
            dataBeans.addAll(bean.getData());
            testVf.addNotice(dataBeans);
            testVf.startFlipping();
        }
    }

    @Override
    public void error(Object error) {
        utils.dismissDialog(utils);
        if (error.toString().trim().equals("HTTP 401")) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            UserUtils.getInstance().clearAllSp(mActivity);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        utils.dismissDialog(utils);
    }
}