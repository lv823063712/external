package com.example.external.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;

import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.common.RequestCommon;
import com.example.external.common.SystemCommon;
import com.example.external.mvp.bean.SuccessCommon;
import com.example.external.mvp.bean.UserInfoBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.mvp.requestbean.BankInfoRequestBean;
import com.example.external.mvp.requestbean.BaseInfoRequestBean;
import com.example.external.mvp.requestbean.SalaryRequestBean;
import com.example.external.ui.view.BillTimerPop;
import com.example.external.ui.view.EducationPop;
import com.example.external.ui.view.EmploymentTypePop;
import com.example.external.ui.view.MaritalPop;
import com.example.external.ui.view.MonthlyFamilyIncomePop;
import com.example.external.ui.view.SexPicker;
import com.example.external.ui.view.YourMonthlySalaryPop;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.StatusBarUtil;
import com.example.external.utils.UserUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class IdentificationActivity extends BaseActivity implements View.OnClickListener, StartInterface.StrartView {
    private ImageView iden_back;
    private NestedScrollView first_step;
    private View one_line, two_line;
    private TextView one_button, two_button, three_button, step_tv, birthday_et, gender_et, marital_et,
            education_et, employment_type_et, your_monthly_salary_et, monthly_family_income_et;
    private EditText full_name_et, email_et, second_step,
            ifsc_et, bank_name_et, bank_account;
    private LinearLayout second_steps, third_step;
    private int next_step = 0;
    private BillTimerPop billTimerPop;
    private DialogUtils utils;
    private StartPresenter startPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_identification;
    }

    @Override
    protected void initData() {
        startPresenter = new StartPresenter(this);
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        StatusBarUtil.setTextColor(this);
        initView();
        Intent intent = getIntent();
        int action = intent.getIntExtra("next_step", 0);
        next_step = action;
        if (next_step == 1) {
            first_step.setVisibility(View.VISIBLE);
            second_steps.setVisibility(View.GONE);
            third_step.setVisibility(View.GONE);
        } else if (next_step == 2) {
            first_step.setVisibility(View.GONE);
            second_steps.setVisibility(View.VISIBLE);
            third_step.setVisibility(View.GONE);
            one_button.setText(null);
            one_line.setBackground(getResources().getDrawable(R.color.white));
            two_button.setText("2");
            two_button.setBackground(getResources().getDrawable(R.drawable.shape_10_white));
        } else if (next_step == 3) {
            first_step.setVisibility(View.GONE);
            second_steps.setVisibility(View.GONE);
            third_step.setVisibility(View.VISIBLE);
            two_button.setText(null);
            two_line.setBackground(getResources().getDrawable(R.color.white));
            three_button.setText("3");
            three_button.setBackground(getResources().getDrawable(R.drawable.shape_10_white));
        }

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
        birthday_et.setOnClickListener(this);
        gender_et.setOnClickListener(this);
        marital_et.setOnClickListener(this);
        education_et.setOnClickListener(this);
        employment_type_et.setOnClickListener(this);
        your_monthly_salary_et.setOnClickListener(this);
        monthly_family_income_et.setOnClickListener(this);
    }

    @Override
    protected void preLogic() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.birthday_et:
                SystemCommon.getInstance().keyBoard(mActivity);
                billTimerPop = new BillTimerPop(this);
                billTimerPop.showPopupWindow();
                billTimerPop.darkenBackground(0.5f);
                billTimerPop.setSelectTimeListener(start -> {
                    birthday_et.setText(start);
                });
                break;
            case R.id.gender_et:
                SystemCommon.getInstance().keyBoard(mActivity);
                SexPicker meCutPicker = new SexPicker(this);
                meCutPicker.showPopupWindow();
                meCutPicker.mySelectAll(nameAb -> gender_et.setText(nameAb));
                break;
            case R.id.marital_et:
                SystemCommon.getInstance().keyBoard(mActivity);
                MaritalPop maritalPop = new MaritalPop(this);
                maritalPop.showPopupWindow();
                maritalPop.mySelectAll(nameAb -> marital_et.setText(nameAb));
                break;
            case R.id.education_et:
                SystemCommon.getInstance().keyBoard(mActivity);
                EducationPop educationPop = new EducationPop(this);
                educationPop.showPopupWindow();
                educationPop.mySelectAll(nameAb -> education_et.setText(nameAb));
                break;
            case R.id.employment_type_et:
                EmploymentTypePop employmentTypePop = new EmploymentTypePop(this);
                employmentTypePop.showPopupWindow();
                employmentTypePop.mySelectAll(nameAb -> employment_type_et.setText(nameAb));
                break;
            case R.id.your_monthly_salary_et:
                YourMonthlySalaryPop salaryPop = new YourMonthlySalaryPop(this);
                salaryPop.showPopupWindow();
                salaryPop.mySelectAll(nameAb -> your_monthly_salary_et.setText(nameAb));
                break;
            case R.id.monthly_family_income_et:
                MonthlyFamilyIncomePop incomePop = new MonthlyFamilyIncomePop(this);
                incomePop.showPopupWindow();
                incomePop.mySelectAll(nameAb -> monthly_family_income_et.setText(nameAb));
                break;
            case R.id.iden_back:
                backActivity();
                break;
            case R.id.step_tv:
                if (next_step == 1) {
                    BaseInfoRequestBean bean = new BaseInfoRequestBean();
                    if (!"".equals(full_name_et.getText().toString()) && full_name_et.getText().toString() != null) {
                        bean.setName(full_name_et.getText().toString());
                    } else {
                        Toast.makeText(mActivity, "Please enter Full Name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!"".equals(birthday_et.getText().toString()) && birthday_et.getText().toString() != null) {
                        bean.setBirthday(birthday_et.getText().toString());
                    } else {
                        Toast.makeText(mActivity, "Please choose your birthday", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!"".equals(gender_et.getText().toString()) && gender_et.getText().toString() != null) {
                        if (gender_et.getText().toString().contains("MALE")) {
                            bean.setGender(1);
                        } else {
                            bean.setGender(2);
                        }
                    } else {
                        Toast.makeText(mActivity, "Please choose gender", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!"".equals(marital_et.getText().toString()) && marital_et.getText().toString() != null) {
                        if (marital_et.getText().toString().contains("Married")) {
                            bean.setMarital(1);
                        } else if (marital_et.getText().toString().contains("Unmarried")) {
                            bean.setMarital(2);
                        } else if (marital_et.getText().toString().contains("Divorced")) {
                            bean.setMarital(3);
                        }
                    } else {
                        Toast.makeText(mActivity, "Please choose marital status", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!"".equals(education_et.getText().toString()) && education_et.getText().toString() != null) {
                        if (education_et.getText().toString().contains("Other")) {
                            bean.setEducation(1);
                        } else if (education_et.getText().toString().contains("High School Diploma")) {
                            bean.setEducation(2);
                        } else if (education_et.getText().toString().contains("Bachelor degree")) {
                            bean.setEducation(3);
                        } else if (education_et.getText().toString().contains("Master Diploma")) {
                            bean.setEducation(4);
                        } else if (education_et.getText().toString().contains("Doctoral Diploma")) {
                            bean.setEducation(5);
                        }
                    } else {
                        Toast.makeText(mActivity, "Please select education background", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!"".equals(email_et.getText().toString()) && email_et.getText().toString() != null) {
                        bean.setEmail(email_et.getText().toString());
                    } else {
                        Toast.makeText(mActivity, "Please enter email address", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Gson gson = new Gson();
                    String s = gson.toJson(bean);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), s);
                    Map<String, Object> headers = RequestCommon.getInstance().headers(mActivity);
                    Map<String, Object> bodys = new HashMap<>();
                    utils.show();
                    startPresenter.postQueryBody(Constant.UPBASEINFO_URL, headers, bodys, requestBody, SuccessCommon.class);
                } else if (next_step == 2) {
                    SalaryRequestBean requestBean = new SalaryRequestBean();
                    if (employment_type_et.getText() != null && !"".equals(employment_type_et.getText().toString())) {
                        if (employment_type_et.getText().toString().contains("Full-time")) {
                            requestBean.setEmployment_type(1);
                        } else if (employment_type_et.getText().toString().contains("Part-time job")) {
                            requestBean.setEmployment_type(2);
                        }
                    } else {
                        Toast.makeText(mActivity, "Please select the type of occupation", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (your_monthly_salary_et.getText() != null && !"".equals(your_monthly_salary_et.getText().toString())) {
                        if (your_monthly_salary_et.getText().toString().contains("0～8000")) {
                            requestBean.setMonthly_salary(1);
                        } else if (your_monthly_salary_et.getText().toString().contains("8000～20000")) {
                            requestBean.setMonthly_salary(2);
                        } else if (your_monthly_salary_et.getText().toString().contains("20000～30000")) {
                            requestBean.setMonthly_salary(3);
                        } else if (your_monthly_salary_et.getText().toString().contains("30000～50000")) {
                            requestBean.setMonthly_salary(4);
                        } else if (your_monthly_salary_et.getText().toString().contains(">50000")) {
                            requestBean.setMonthly_salary(5);
                        }
                    } else {
                        Toast.makeText(mActivity, "Please fill in your income", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (monthly_family_income_et.getText() != null && !"".equals(monthly_family_income_et.getText().toString())) {
                        if (monthly_family_income_et.getText().toString().contains("<20000")) {
                            requestBean.setMonthly_family_salary(1);
                        } else if (monthly_family_income_et.getText().toString().contains("20000～30000")) {
                            requestBean.setMonthly_family_salary(2);
                        } else if (monthly_family_income_et.getText().toString().contains("30000～50000")) {
                            requestBean.setMonthly_family_salary(3);
                        } else if (monthly_family_income_et.getText().toString().contains("50000～80000")) {
                            requestBean.setMonthly_family_salary(4);
                        } else if (monthly_family_income_et.getText().toString().contains(">80000")) {
                            requestBean.setMonthly_family_salary(5);
                        }
                    } else {
                        Toast.makeText(mActivity, "Please fill in your household income", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Gson gson = new Gson();
                    String s = gson.toJson(requestBean);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), s);
                    Map<String, Object> headers = RequestCommon.getInstance().headers(mActivity);
                    Map<String, Object> bodys = new HashMap<>();
                    utils.show();
                    startPresenter.postQueryBody(Constant.UPWORKINFO_URL, headers, bodys, requestBody, SuccessCommon.class);
                    first_step.setVisibility(View.GONE);
                    second_steps.setVisibility(View.VISIBLE);
                    third_step.setVisibility(View.GONE);
                    two_button.setText(null);
                    two_line.setBackground(getResources().getDrawable(R.color.white));
                    three_button.setText("3");
                    three_button.setBackground(getResources().getDrawable(R.drawable.shape_10_white));
                } else if (next_step == 3) {
                    BankInfoRequestBean bankBean = new BankInfoRequestBean();
                    if (ifsc_et.getText() != null && !"".equals(ifsc_et.getText().toString())) {
                        bankBean.setIfsc_code(ifsc_et.getText().toString());
                    } else {
                        Toast.makeText(mActivity, "Please fill in THE IFSC code", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (bank_name_et.getText() != null && !"".equals(bank_name_et.getText().toString())) {
                        bankBean.setBank_name(bank_name_et.getText().toString());
                    } else {
                        Toast.makeText(mActivity, "Please fill in the bank name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (bank_account.getText() != null && !"".equals(bank_account.getText().toString())) {
                        bankBean.setBank_account_no(bank_account.getText().toString());
                    } else {
                        Toast.makeText(mActivity, "Please fill in your bank card number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Gson gson = new Gson();
                    String s = gson.toJson(bankBean);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), s);
                    Map<String, Object> headers = RequestCommon.getInstance().headers(mActivity);
                    Map<String, Object> bodys = new HashMap<>();
                    utils.show();
                    startPresenter.postQueryBody(Constant.UPBANKINFO_URL, headers, bodys, requestBody, SuccessCommon.class);
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

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof SuccessCommon) {
            SuccessCommon common = (SuccessCommon) data;
            if (next_step == 1) {
                next_step = 2;
                first_step.setVisibility(View.GONE);
                second_steps.setVisibility(View.VISIBLE);
                third_step.setVisibility(View.GONE);
                one_button.setText(null);
                one_line.setBackground(getResources().getDrawable(R.color.white));
                two_button.setText("2");
                two_button.setBackground(getResources().getDrawable(R.drawable.shape_10_white));
            } else if (next_step == 2) {
                first_step.setVisibility(View.GONE);
                second_steps.setVisibility(View.GONE);
                third_step.setVisibility(View.VISIBLE);
                two_button.setText(null);
                two_line.setBackground(getResources().getDrawable(R.color.white));
                three_button.setText("3");
                three_button.setBackground(getResources().getDrawable(R.drawable.shape_10_white));
                next_step = 3;
            } else if (next_step == 3) {
                Intent intent = new Intent(this, GetMoneyActivity.class);
                startActivity(intent);
            }
            Toast.makeText(mActivity, common.getMessage(), Toast.LENGTH_SHORT).show();
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