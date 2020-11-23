package com.example.external.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.common.RequestCommon;
import com.example.external.mvp.bean.SuccessCommon;
import com.example.external.mvp.bean.UserInfoBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.mvp.requestbean.SalaryRequestBean;
import com.example.external.ui.view.EmploymentTypePop;
import com.example.external.ui.view.MonthlyFamilyIncomePop;
import com.example.external.ui.view.YourMonthlySalaryPop;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.UserUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WorkInformationActivity extends BaseActivity implements View.OnClickListener, StartInterface.StrartView {

    private ImageView work_msg_back;
    private RelativeLayout employment_relative, relative_monthly, relative_family;
    private TextView employment_text, monthly_text, family_text, tv_save_work;
    private DialogUtils utils;
    private StartPresenter startPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_work_information;
    }

    @Override
    protected void initData() {
        startPresenter = new StartPresenter(this);
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        work_msg_back = findViewById(R.id.work_msg_back);
        employment_relative = findViewById(R.id.employment_relative);
        relative_monthly = findViewById(R.id.relative_monthly);
        relative_family = findViewById(R.id.relative_family);
        employment_text = findViewById(R.id.employment_text);
        monthly_text = findViewById(R.id.monthly_text);
        family_text = findViewById(R.id.family_text);
        tv_save_work = findViewById(R.id.tv_save_work);
    }

    @Override
    protected void setClick() {
        work_msg_back.setOnClickListener(this);
        employment_relative.setOnClickListener(this);
        relative_monthly.setOnClickListener(this);
        relative_family.setOnClickListener(this);
        tv_save_work.setOnClickListener(this);
    }

    @Override
    protected void preLogic() {
        Map<String, Object> header = RequestCommon.getInstance().headers(mActivity);
        Map<String, Object> body = new HashMap<>();
        utils.show();
        startPresenter.get(Constant.PROFILE_URL, header, body, UserInfoBean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.work_msg_back:
                backActivity();
                break;
            case R.id.employment_relative:
                EmploymentTypePop employmentTypePop = new EmploymentTypePop(this);
                employmentTypePop.showPopupWindow();
                employmentTypePop.mySelectAll(nameAb -> employment_text.setText(nameAb));
                break;
            case R.id.relative_monthly:
                YourMonthlySalaryPop salaryPop = new YourMonthlySalaryPop(this);
                salaryPop.showPopupWindow();
                salaryPop.mySelectAll(nameAb -> monthly_text.setText(nameAb));
                break;
            case R.id.relative_family:
                MonthlyFamilyIncomePop incomePop = new MonthlyFamilyIncomePop(this);
                incomePop.showPopupWindow();
                incomePop.mySelectAll(nameAb -> family_text.setText(nameAb));
                break;
            case R.id.tv_save_work:
                SalaryRequestBean requestBean = new SalaryRequestBean();
                if (employment_text.getText() != null && !employment_text.getText().toString().equals("")) {
                    if (employment_text.getText().toString().contains("Full-time")) {
                        requestBean.setEmployment_type(1);
                    } else if (employment_text.getText().toString().contains("Part-time job")) {
                        requestBean.setEmployment_type(2);
                    }
                } else {
                    Toast.makeText(mActivity, "Please select the type of occupation", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (monthly_text.getText() != null && !monthly_text.getText().toString().equals("")) {
                    if (monthly_text.getText().toString().contains("0～8000")) {
                        requestBean.setMonthly_salary(1);
                    } else if (monthly_text.getText().toString().contains("8000～20000")) {
                        requestBean.setMonthly_salary(2);
                    } else if (monthly_text.getText().toString().contains("20000～30000")) {
                        requestBean.setMonthly_salary(3);
                    } else if (monthly_text.getText().toString().contains("30000～50000")) {
                        requestBean.setMonthly_salary(4);
                    } else if (monthly_text.getText().toString().contains(">50000")) {
                        requestBean.setMonthly_salary(5);
                    }
                } else {
                    Toast.makeText(mActivity, "Please fill in your income", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (family_text.getText() != null && !family_text.getText().toString().equals("")) {
                    if (family_text.getText().toString().contains("<20000")) {
                        requestBean.setMonthly_family_salary(1);
                    } else if (family_text.getText().toString().contains("20000～30000")) {
                        requestBean.setMonthly_family_salary(2);
                    } else if (family_text.getText().toString().contains("30000～50000")) {
                        requestBean.setMonthly_family_salary(3);
                    } else if (family_text.getText().toString().contains("50000～80000")) {
                        requestBean.setMonthly_family_salary(4);
                    } else if (family_text.getText().toString().contains(">80000")) {
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
                break;
        }
    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof SuccessCommon) {
            SuccessCommon common = (SuccessCommon) data;
            if (common.getStatus() == 1) {
                Toast.makeText(mActivity, "Work information saved successfully", Toast.LENGTH_SHORT).show();
                backActivity();
            } else {
                Toast.makeText(mActivity, "Failed to save work information", Toast.LENGTH_SHORT).show();
            }
        } else if (data instanceof UserInfoBean) {
            UserInfoBean bean = (UserInfoBean) data;
            if (bean.getStatus() == 1) {
                employment_text.setText(bean.getData().getEmployment_type());
                monthly_text.setText(bean.getData().getMonthly_salary());
                family_text.setText(bean.getData().getMonthly_family_salary());
            }
        }
    }

    @Override
    public void error(Object error) {
        utils.dismissDialog(utils);
        if (error.toString().trim().contains("401")) {
            Intent intent = new Intent(mActivity, LoginActivity.class);
            startActivity(intent);
            backActivity();
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