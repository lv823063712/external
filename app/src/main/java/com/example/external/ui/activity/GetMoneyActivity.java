package com.example.external.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.common.RequestCommon;
import com.example.external.mvp.bean.GetMoneyBean;
import com.example.external.mvp.bean.ProductBean;
import com.example.external.mvp.bean.SuccessCommon;
import com.example.external.mvp.bean.UserInfoBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.utils.AppUtils;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.StatusBarUtil;
import com.example.external.utils.UserUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetMoneyActivity extends BaseActivity implements View.OnClickListener, StartInterface.StrartView {

    private TextView show_money;
    private TextView get_loan, month_show, loan_term, loan_interest, monthly_payment, monthly_principal, monthly_inerest,
            bank_card_num, original_money, reality_money, hint_textview;
    private LinearLayout details_show, list_content, my_background;
    private View money_one, money_two, money_three, money_four, months_one, months_two, months_three, months_four;
    private SeekBar money_bar, plan_months;
    private int isShow = 0;
    private ImageView details_img;
    private ArrayList<ProductBean> ints;
    private DialogUtils utils;

    private String idNet;
    private StartPresenter startPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_get_money;
    }

    @Override
    protected void initData() {
        startPresenter = new StartPresenter(this);
        StatusBarUtil.setTextColor(this);
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        startPresenter = new StartPresenter(this);
        initView();
        hint_textview.setText(UserUtils.getInstance().gettips_pay(mActivity));
        Intent intent = getIntent();
        ints = intent.getParcelableArrayListExtra("ints");
        String money = intent.getStringExtra("money");
        if (money.contains("30,000")) {
            show_money.setText(money);
            money_bar.setProgress(15);
            money_one.setBackground(getResources().getDrawable(R.color.red_6D83F2));
            money_two.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_three.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_four.setBackground(getResources().getDrawable(R.color.green_6D83F2));
        } else if (money.contains("50,000") &&!money.contains("150,000")) {
            show_money.setText(money);
            money_bar.setProgress(35);
            money_one.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_two.setBackground(getResources().getDrawable(R.color.red_6D83F2));
            money_three.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_four.setBackground(getResources().getDrawable(R.color.green_6D83F2));
        } else if (money.contains("80,000")) {
            show_money.setText(money);
            money_bar.setProgress(65);
            money_one.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_two.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_three.setBackground(getResources().getDrawable(R.color.red_6D83F2));
            money_four.setBackground(getResources().getDrawable(R.color.green_6D83F2));
        } else if (money.contains("150,000")) {
            show_money.setText(money);
            money_bar.setProgress(90);
            money_one.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_two.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_three.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_four.setBackground(getResources().getDrawable(R.color.red_6D83F2));
        }
//        if (ints.get(0) == null) {
            netWork();
//        } else {
//            setData(ints.get(0));
//        }

    }

    private void initView() {
        show_money = findViewById(R.id.show_money);
        money_one = findViewById(R.id.money_one);
        money_two = findViewById(R.id.money_two);
        money_three = findViewById(R.id.money_three);
        money_four = findViewById(R.id.money_four);
        money_bar = findViewById(R.id.money_bar);
        month_show = findViewById(R.id.month_show);
        months_one = findViewById(R.id.months_one);
        months_two = findViewById(R.id.months_two);
        months_three = findViewById(R.id.months_three);
        months_four = findViewById(R.id.months_four);
        plan_months = findViewById(R.id.plan_months);
        my_background = findViewById(R.id.my_background);
        details_show = findViewById(R.id.details_show);
        list_content = findViewById(R.id.list_content);
        loan_term = findViewById(R.id.loan_term);
        loan_interest = findViewById(R.id.loan_interest);
        monthly_payment = findViewById(R.id.monthly_payment);
        monthly_principal = findViewById(R.id.monthly_principal);
        monthly_inerest = findViewById(R.id.monthly_inerest);
        bank_card_num = findViewById(R.id.bank_card_num);
        original_money = findViewById(R.id.original_money);
        reality_money = findViewById(R.id.reality_money);
        hint_textview = findViewById(R.id.hint_textview);
        get_loan = findViewById(R.id.get_loan);
        details_img = findViewById(R.id.details_img);
    }

    @Override
    protected void setClick() {
        original_money.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        get_loan.setOnClickListener(this);
        details_show.setOnClickListener(this);
        money_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setMBackGround(progress, show_money);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setData(ints.get(0));
            }
        });
        plan_months.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setMothBackGround(progress, month_show);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setData(ints.get(0));
            }
        });
    }

    @Override
    protected void preLogic() {
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        utils.show();
        startPresenter.get(Constant.PROFILE_URL, header, body, UserInfoBean.class);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.details_show:
                if (isShow == 0) {
                    setData(ints.get(0));
                    list_content.setVisibility(View.VISIBLE);
                    details_img.setImageDrawable(getResources().getDrawable(R.mipmap.icon_xsj));
                    my_background.setBackground(getResources().getDrawable(R.drawable.icon_back_bg));
                    ViewGroup.LayoutParams layoutParams = my_background.getLayoutParams();
                    int i = AppUtils.dip2px(mActivity, 258);
                    layoutParams.height = i;
                    my_background.setLayoutParams(layoutParams);
                    isShow = 1;
                } else {
                    list_content.setVisibility(View.GONE);
                    details_img.setImageDrawable(getResources().getDrawable(R.mipmap.icon_hxsj));
                    my_background.setBackground(getResources().getDrawable(R.drawable.shape_bg_home));
                    ViewGroup.LayoutParams layoutParams = my_background.getLayoutParams();
                    int i = AppUtils.dip2px(mActivity, 48);
                    layoutParams.height = i;
                    my_background.setLayoutParams(layoutParams);
                    isShow = 0;
                }
                break;
            case R.id.get_loan:
                razNetWork();
                break;
            default:break;
        }
    }

    private void razNetWork() {
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        body.put("id", idNet);
        utils.show();
        if ("razorpay".equals(UserUtils.getInstance().getPayChannel(mActivity))) {
            startPresenter.get(Constant.CREATERAZORPAY_URL, header, body, GetMoneyBean.class);
        } else if ("cashfree".equals(UserUtils.getInstance().getPayChannel(mActivity))) {
            startPresenter.get(Constant.CREATECASHFREEPAY_URL, header, body, GetMoneyBean.class);
        }

    }

    private void netWork() {
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        utils.show();
        startPresenter.get(Constant.HOMEPAGE, header, body, ProductBean.class);
    }

    @SuppressLint("SetTextI18n")
    private void setData(ProductBean data) {
        for (int i = 0; i < data.getData().getLimits().size(); i++) {
            if (data.getData().getLimits().get(i).getAmount() == Integer.parseInt(show_money.getText().toString().split("₹")[1].replace(",", "").trim())) {
                for (int j = 0; j < data.getData().getLimits().get(i).getDurations().size(); j++) {
                    if (data.getData().getLimits().get(i).getDurations().get(j).getDuration().contains(month_show.getText().toString().replace("Months", "month"))) {
                        idNet = data.getData().getLimits().get(i).getDurations().get(j).getId();
                        loan_term.setText(data.getData().getLimits().get(i).getDurations().get(j).getDuration());
                        loan_interest.setText(data.getData().getLimits().get(i).getDurations().get(j).getInterest() + "");
                        monthly_payment.setText(data.getData().getLimits().get(i).getDurations().get(j).getMonthly_payment() + "");
                        monthly_principal.setText(data.getData().getLimits().get(i).getDurations().get(j).getMonthly_principal() + "");
                        monthly_inerest.setText(data.getData().getLimits().get(i).getDurations().get(j).getMonthly_inerest() + "");
                        original_money.setText("₹" + data.getData().getLimits().get(i).getDurations().get(j).getMember_ori_fee());
                        reality_money.setText("₹" + data.getData().getLimits().get(i).getDurations().get(j).getMember_fee());
                    }
                }
            }
        }

    }

    private void setMBackGround(int plan, TextView textView) {
        if (plan <= 25) {
            money_one.setBackground(getResources().getDrawable(R.color.red_6D83F2));
            money_two.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_three.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_four.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            textView.setText("₹30,000");
        } else if (plan > 25 && plan <= 50) {
            money_one.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_two.setBackground(getResources().getDrawable(R.color.red_6D83F2));
            money_three.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_four.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            textView.setText("₹50,000");
        } else if (plan > 50 && plan <= 75) {
            money_one.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_two.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_three.setBackground(getResources().getDrawable(R.color.red_6D83F2));
            money_four.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            textView.setText("₹80,000");
        } else if (plan > 75 && plan <= 100) {
            money_one.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_two.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_three.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            money_four.setBackground(getResources().getDrawable(R.color.red_6D83F2));
            textView.setText("₹150,000");
        }
    }

    private void setMothBackGround(int plan, TextView textView) {
        if (plan <= 25) {
            months_one.setBackground(getResources().getDrawable(R.color.red_6D83F2));
            months_two.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            months_three.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            months_four.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            textView.setText("1 Months");
        } else if (plan > 25 && plan <= 50) {
            months_one.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            months_two.setBackground(getResources().getDrawable(R.color.red_6D83F2));
            months_three.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            months_four.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            textView.setText("3 Months");
        } else if (plan > 50 && plan <= 75) {
            months_one.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            months_two.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            months_three.setBackground(getResources().getDrawable(R.color.red_6D83F2));
            months_four.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            textView.setText("6 Months");
        } else if (plan > 75 && plan <= 100) {
            months_one.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            months_two.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            months_three.setBackground(getResources().getDrawable(R.color.green_6D83F2));
            months_four.setBackground(getResources().getDrawable(R.color.red_6D83F2));
            textView.setText("12 Months");
        }
    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof UserInfoBean) {
            UserInfoBean bean = (UserInfoBean) data;
            if (bean.getData() != null) {
                bank_card_num.setText(bean.getData().getBank_account_no());
            }
        } else if (data instanceof ProductBean) {
            ProductBean bean = (ProductBean) data;
            ints.add(bean);
        } else if (data instanceof GetMoneyBean) {
            GetMoneyBean moneyBean = (GetMoneyBean) data;
            if (moneyBean.getStatus() == 1) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                backActivity();
            }
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
        if (startPresenter != null) {
            startPresenter.onDatacth();
        }
    }
}