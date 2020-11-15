package com.example.external.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.utils.AppUtils;
import com.example.external.utils.StatusBarUtil;

public class GetMoneyActivity extends BaseActivity implements View.OnClickListener {

    private TextView show_money;
    private TextView get_loan, month_show, loan_term, loan_interest, monthly_payment, monthly_principal, monthly_inerest,
            bank_card_num, original_money, reality_money, hint_textview;
    private LinearLayout details_show, list_content, my_background;
    private View money_one, money_two, money_three, money_four, months_one, months_two, months_three, months_four;
    private SeekBar money_bar, plan_months;
    private int isShow = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_get_money;
    }

    @Override
    protected void initData() {
        StatusBarUtil.setTextColor(this);
        initView();
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
    }

    @Override
    protected void setClick() {
        original_money.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        original_money.setText("₹666");
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

            }
        });
    }

    @Override
    protected void preLogic() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.details_show:
                if (isShow == 0) {
                    list_content.setVisibility(View.VISIBLE);
                    my_background.setBackground(getResources().getDrawable(R.drawable.icon_back_bg));
                    ViewGroup.LayoutParams layoutParams = my_background.getLayoutParams();
                    int i = AppUtils.dip2px(mActivity, 258);
                    layoutParams.height = i;
                    my_background.setLayoutParams(layoutParams);
                    isShow = 1;
                } else {
                    list_content.setVisibility(View.GONE);
                    my_background.setBackground(getResources().getDrawable(R.drawable.shape_bg_home));
                    ViewGroup.LayoutParams layoutParams = my_background.getLayoutParams();
                    int i = AppUtils.dip2px(mActivity, 48);
                    layoutParams.height = i;
                    my_background.setLayoutParams(layoutParams);
                    isShow = 0;
                }
                break;
            case R.id.get_loan:
                break;
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
}