package com.example.external.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.common.RequestCommon;
import com.example.external.common.SystemCommon;
import com.example.external.mvp.bean.SuccessCommon;
import com.example.external.mvp.bean.UserInfoBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.mvp.requestbean.BaseInfoRequestBean;
import com.example.external.ui.view.BillTimerPop;
import com.example.external.ui.view.EducationPop;
import com.example.external.ui.view.MaritalPop;
import com.example.external.ui.view.SexPicker;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.UserUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 基础信息
 */
public class BasicInformationActivity extends BaseActivity implements View.OnClickListener, StartInterface.StrartView {

    private TextView tv_save, education_text, marital_text, gender_text, birthday_text;
    private ImageView msg_back;
    private EditText full_name_edit, email_edit;
    private BillTimerPop billTimerPop;
    private DialogUtils utils;
    private StartPresenter startPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_basic_information;
    }

    @Override
    protected void initData() {
        startPresenter = new StartPresenter(this);
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        initView();
    }

    private void initView() {
        tv_save = findViewById(R.id.tv_save);
        msg_back = findViewById(R.id.msg_back);
        full_name_edit = findViewById(R.id.full_name_edit);
        birthday_text = findViewById(R.id.birthday_text);
        gender_text = findViewById(R.id.gender_text);
        marital_text = findViewById(R.id.marital_text);
        education_text = findViewById(R.id.education_text);
        email_edit = findViewById(R.id.email_edit);
    }

    @Override
    protected void setClick() {
        tv_save.setOnClickListener(this);
        msg_back.setOnClickListener(this);
        birthday_text.setOnClickListener(this);
        gender_text.setOnClickListener(this);
        marital_text.setOnClickListener(this);
        education_text.setOnClickListener(this);
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
            case R.id.msg_back:
                backActivity();
                break;
            case R.id.tv_save:
                BaseInfoRequestBean bean = new BaseInfoRequestBean();
                if (!full_name_edit.getText().toString().equals("") && full_name_edit.getText().toString() != null) {
                    bean.setName(full_name_edit.getText().toString());
                } else {
                    Toast.makeText(mActivity, "Please enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!birthday_text.getText().toString().equals("") && birthday_text.getText().toString() != null) {
                    bean.setBirthday(birthday_text.getText().toString());
                } else {
                    Toast.makeText(mActivity, "Please choose your birthday", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!gender_text.getText().toString().equals("") && gender_text.getText().toString() != null) {
                    if (gender_text.getText().toString().contains("MALE")) {
                        bean.setGender(1);
                    } else {
                        bean.setGender(2);
                    }
                } else {
                    Toast.makeText(mActivity, "Please choose gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!marital_text.getText().toString().equals("") && marital_text.getText().toString() != null) {
                    if (marital_text.getText().toString().contains("Married")) {
                        bean.setMarital(1);
                    } else if (marital_text.getText().toString().contains("Unmarried")) {
                        bean.setMarital(2);
                    } else if (marital_text.getText().toString().contains("Divorced")) {
                        bean.setMarital(3);
                    }
                } else {
                    Toast.makeText(mActivity, "Please choose marital status", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!education_text.getText().toString().equals("") && education_text.getText().toString() != null) {
                    if (education_text.getText().toString().contains("Other")) {
                        bean.setEducation(1);
                    } else if (education_text.getText().toString().contains("High School Diploma")) {
                        bean.setEducation(2);
                    } else if (education_text.getText().toString().contains("Bachelor degree")) {
                        bean.setEducation(3);
                    } else if (education_text.getText().toString().contains("Master Diploma")) {
                        bean.setEducation(4);
                    } else if (education_text.getText().toString().contains("Doctoral Diploma")) {
                        bean.setEducation(5);
                    }
                } else {
                    Toast.makeText(mActivity, "Please select education background", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!email_edit.getText().toString().equals("") && email_edit.getText().toString() != null) {
                    bean.setEmail(email_edit.getText().toString());
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
                break;
            case R.id.birthday_text:
                SystemCommon.getInstance().keyBoard(mActivity);
                billTimerPop = new BillTimerPop(this);
                billTimerPop.showPopupWindow();
                billTimerPop.darkenBackground(0.5f);
                billTimerPop.setSelectTimeListener(start -> {
                    birthday_text.setText(start);
                });
                break;
            case R.id.gender_text:
                SystemCommon.getInstance().keyBoard(mActivity);
                SexPicker meCutPicker = new SexPicker(this);
                meCutPicker.showPopupWindow();
                meCutPicker.mySelectAll(nameAb -> gender_text.setText(nameAb));
                break;
            case R.id.marital_text:
                SystemCommon.getInstance().keyBoard(mActivity);
                MaritalPop maritalPop = new MaritalPop(this);
                maritalPop.showPopupWindow();
                maritalPop.mySelectAll(nameAb -> marital_text.setText(nameAb));
                break;
            case R.id.education_text:
                SystemCommon.getInstance().keyBoard(mActivity);
                EducationPop educationPop = new EducationPop(this);
                educationPop.showPopupWindow();
                educationPop.mySelectAll(nameAb -> education_text.setText(nameAb));
                break;
        }
    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof SuccessCommon) {
            SuccessCommon common = (SuccessCommon) data;
            if (common.getStatus() == 1) {
                Toast.makeText(mActivity, "Personal information saved successfully", Toast.LENGTH_SHORT).show();
                backActivity();
            } else {
                Toast.makeText(mActivity, "Personal information saving failed", Toast.LENGTH_SHORT).show();
            }
        } else if (data instanceof UserInfoBean) {
            UserInfoBean bean = (UserInfoBean) data;
            if (bean.getStatus() == 1) {
                full_name_edit.setText(bean.getData().getName());
                birthday_text.setText(bean.getData().getBirthday());
                gender_text.setText(bean.getData().getGender());
                marital_text.setText(bean.getData().getMarital());
                education_text.setText(bean.getData().getEducation());
                email_edit.setText(bean.getData().getEmail());
            }

        }
    }

    @Override
    public void error(Object error) {
        utils.dismissDialog(utils);
        if (error.toString().trim().equals("401")) {
            Intent intent = new Intent(mActivity, LoginActivity.class);
            startActivity(intent);
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