package com.example.external.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.external.ui.adapter.HomeListAdapter;
import com.example.external.utils.DataUtils;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.LuckyNoticeView;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomePageFragment extends BaseFragment implements StartInterface.StrartView, View.OnClickListener {

    private DialogUtils utils;
    private TextView reduce_money;
    private TextView increase_money;
    private TextView home_borrow_money;
    private TextView borrow;
    private LuckyNoticeView testVf;
    private final List<MarqueeBean.DataBean> dataBeans = new ArrayList<>();
    private final ArrayList<ProductBean> beans = new ArrayList<>();
    private int money_show = 1;
    private boolean MoneyChangeFlag = false;
    private int status;
    private int phase;
    private SmartRefreshLayout home_page_refresh, home_page_refreshs;
    private LinearLayout my_infor, my_get_money;
    private RelativeLayout bototm_button;
    private StartPresenter startPresenter;
    private RecyclerView myHome_rv;
    private final ArrayList<ProductBean.DataBean.ViplistBean> list = new ArrayList<>();
    private HomeListAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        startPresenter = new StartPresenter(this);
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        borrow = mActivity.findViewById(R.id.Borrow);
        View title_view = mActivity.findViewById(R.id.title_view);
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)   //透明状态栏，不写默认透明色
                .keyboardEnable(true)
                .statusBarView(title_view)
                .autoStatusBarDarkModeEnable(true, 0.2f)
                .init();
        reduce_money = mActivity.findViewById(R.id.reduce_money);
        bototm_button = mActivity.findViewById(R.id.bototm_button);
        increase_money = mActivity.findViewById(R.id.increase_money);
        home_borrow_money = mActivity.findViewById(R.id.home_borrow_money);
        testVf = mActivity.findViewById(R.id.testVf);
        home_page_refresh = mActivity.findViewById(R.id.home_page_refresh);
        home_page_refreshs = mActivity.findViewById(R.id.home_page_refreshs);
        myHome_rv = mActivity.findViewById(R.id.myHome_rv);
        my_infor = mActivity.findViewById(R.id.my_infor);
        my_get_money = mActivity.findViewById(R.id.my_get_money);
        initClick();
    }

    private void initClick() {
        reduce_money.setOnClickListener(this);
        increase_money.setOnClickListener(this);
        my_infor.setOnClickListener(this);
        my_get_money.setOnClickListener(this);
        borrow.setOnClickListener(this);
        home_page_refresh.setEnableLoadMore(false);
        home_page_refresh.setOnRefreshListener(refreshLayout -> {
            netWork();
            home_page_refresh.finishRefresh();
        });
        home_page_refreshs.setOnRefreshListener(refreshLayout -> {
            netWork();
            home_page_refreshs.finishRefresh();
        });
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        myHome_rv.setLayoutManager(manager);
        adapter = new HomeListAdapter(mActivity, list);
        myHome_rv.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String isHidden) {
        if (isHidden.equals("yes")) {
            netWork();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_get_money:
                if (phase == 0) {
                    Intent intent = new Intent(mActivity, GetMoneyActivity.class);
                    intent.putParcelableArrayListExtra("ints", beans);
                    intent.putExtra("money", home_borrow_money.getText().toString());
                    startActivity(intent);
                } else if (phase == 1) {
                    Intent intent = new Intent(mActivity, ReviewingActivity.class);
                    intent.putExtra("attestation", phase);
                    intent.putExtra("money", home_borrow_money.getText().toString());
                    startActivity(intent);
                } else if (phase == 2) {
                    Intent intent = new Intent(mActivity, GetMoneyActivity.class);
                    intent.putParcelableArrayListExtra("ints", beans);
                    intent.putExtra("money", home_borrow_money.getText().toString());
                    startActivity(intent);
                } else if (phase == 3) {
                    Intent intent = new Intent(mActivity, GetMoneyActivity.class);
                    intent.putParcelableArrayListExtra("ints", beans);
                    startActivity(intent);
                }
                break;
            case R.id.my_infor:
            case R.id.Borrow:
                if (status == 0) {
                    if (phase == 0) {
                        Intent intent = new Intent(mActivity, GetMoneyActivity.class);
                        intent.putParcelableArrayListExtra("ints", beans);
                        intent.putExtra("money", home_borrow_money.getText().toString());
                        startActivity(intent);
                    } else if (phase == 1) {
                        Intent intent = new Intent(mActivity, ReviewingActivity.class);
                        intent.putExtra("attestation", phase);
                        startActivity(intent);
                    } else if (phase == 2) {
                        Intent intent = new Intent(mActivity, GetMoneyActivity.class);
                        intent.putExtra("money", home_borrow_money.getText().toString());
                        intent.putParcelableArrayListExtra("ints", beans);
                        startActivity(intent);
                    } else if (phase == 3) {
                        Intent intent = new Intent(mActivity, GetMoneyActivity.class);
                        intent.putExtra("money", home_borrow_money.getText().toString());
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
                MoneyChangeFlag = true;
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
                MoneyChangeFlag = true;
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
            default:
                break;
        }
    }

    @Override
    protected void initData() {
        netWork();
    }

    private void netWork() {
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        utils.show();
        startPresenter.get(Constant.HOMEPAGE, header, body, ProductBean.class);
        startPresenter.get(Constant.MARQUEE_URL, header, body, MarqueeBean.class);
    }

    @Override
    protected void loadData() {
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof ProductBean) {
            ProductBean productBean = (ProductBean) data;
            beans.add(productBean);
            if (productBean.getData().getViplist().size() < 1) {
                home_page_refresh.setVisibility(View.VISIBLE);
                home_page_refreshs.setVisibility(View.GONE);
                if (!MoneyChangeFlag) {
                    for (int i = 0; i < productBean.getData().getLimits().size(); i++) {
                        if (productBean.getData().getLimits().get(i).getIs_default() == 1) {
                            home_borrow_money.setText
                                    ("₹ " + DataUtils.addComma(productBean.getData().getLimits().get(i).getAmount() + ""));
                            if (productBean.getData().getLimits().get(i).getAmount()==80000){
                                money_show=2;
                            }else if (productBean.getData().getLimits().get(i).getAmount()==50000){
                                money_show=1;
                            }else if (productBean.getData().getLimits().get(i).getAmount()==30000){
                            }
                        }
                    }
                }
                status = productBean.getData().getCertification();
                phase = productBean.getData().getCertification();
                bototm_button.setVisibility(View.VISIBLE);

            } else {
                bototm_button.setVisibility(View.GONE);
                home_page_refresh.setVisibility(View.GONE);
                home_page_refreshs.setVisibility(View.VISIBLE);
                list.addAll(productBean.getData().getViplist());
                adapter.setData(list);
            }
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
        if (error.toString().trim().contains("401")) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        utils.dismissDialog(utils);
        if (startPresenter != null) {
            startPresenter.onDatacth();
        }
    }
}