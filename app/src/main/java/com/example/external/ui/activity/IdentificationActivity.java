package com.example.external.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;

import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.utils.StatusBarUtil;

public class IdentificationActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iden_back;
    private NestedScrollView first_step;
    private View one_line, two_line;
    private TextView one_button, two_button, three_button, step_tv;
    private EditText full_name_et, birthday_et, gender_et, marital_et, education_et, email_et, second_step,
            employment_type_et, your_monthly_salary_et, monthly_family_income_et,
            ifsc_et, bank_name_et, bank_account;
    private LinearLayout second_steps, third_step;
    private int next_step = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_identification;
    }

    @Override
    protected void initData() {
        StatusBarUtil.setTextColor(this);
        initView();
    }

    private void initView() {
        iden_back = findViewById(R.id.iden_back);
        one_button = findViewById(R.id.one_button);
        one_line = findViewById(R.id.one_line);
        two_button = findViewById(R.id.two_button);
        two_line = findViewById(R.id.two_line);
        three_button = findViewById(R.id.three_button);
        first_step = findViewById(R.id.first_step);
        full_name_et = findViewById(R.id.full_name_et);
        birthday_et = findViewById(R.id.birthday_et);
        gender_et = findViewById(R.id.gender_et);
        marital_et = findViewById(R.id.marital_et);
        education_et = findViewById(R.id.education_et);
        email_et = findViewById(R.id.email_et);
        second_steps = findViewById(R.id.second_steps);
        employment_type_et = findViewById(R.id.employment_type_et);
        your_monthly_salary_et = findViewById(R.id.your_monthly_salary_et);
        monthly_family_income_et = findViewById(R.id.monthly_family_income_et);
        third_step = findViewById(R.id.third_step);
        ifsc_et = findViewById(R.id.ifsc_et);
        bank_name_et = findViewById(R.id.bank_name_et);
        bank_account = findViewById(R.id.bank_account);
        step_tv = findViewById(R.id.step_tv);

    }

    @Override
    protected void setClick() {
        iden_back.setOnClickListener(this);
        step_tv.setOnClickListener(this);
    }

    @Override
    protected void preLogic() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iden_back:
                backActivity();
                break;
            case R.id.step_tv:
                if (next_step == 0) {
                    first_step.setVisibility(View.GONE);
                    second_steps.setVisibility(View.VISIBLE);
                    third_step.setVisibility(View.GONE);
                    one_button.setText(null);
                    one_line.setBackground(getResources().getDrawable(R.color.white));
                    two_button.setText("2");
                    two_button.setBackground(getResources().getDrawable(R.drawable.shape_10_white));
                    next_step = 1;
                } else if (next_step == 1) {
                    first_step.setVisibility(View.GONE);
                    second_steps.setVisibility(View.GONE);
                    third_step.setVisibility(View.VISIBLE);
                    two_button.setText(null);
                    two_line.setBackground(getResources().getDrawable(R.color.white));
                    three_button.setText("3");
                    three_button.setBackground(getResources().getDrawable(R.drawable.shape_10_white));
                }
                break;
            default:break;
        }
    }
}