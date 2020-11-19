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
import com.example.external.mvp.bean.SuccessCommon;
import com.example.external.mvp.bean.UserInfoBean;
import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.Constant;
import com.example.external.mvp.presenter.StartPresenter;
import com.example.external.mvp.requestbean.BankInfoRequestBean;
import com.example.external.utils.DialogUtils;
import com.example.external.utils.UserUtils;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BankInformationActivity extends BaseActivity implements View.OnClickListener, StartInterface.StrartView {

    private ImageView bank_msg_back;
    private TextView tv_save;
    private EditText code_edit, bank_edit, account_edit;
    private DialogUtils utils;
    private StartPresenter startPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_bank_information;
    }

    @Override
    protected void initData() {
        startPresenter = new StartPresenter(this);
        utils = new DialogUtils(mActivity, R.style.CustomDialog);
        bank_msg_back = findViewById(R.id.bank_msg_back);
        code_edit = findViewById(R.id.code_edit);
        bank_edit = findViewById(R.id.bank_edit);
        account_edit = findViewById(R.id.account_edit);
        tv_save = findViewById(R.id.tv_save);
    }

    @Override
    protected void setClick() {
        bank_msg_back.setOnClickListener(this);
        tv_save.setOnClickListener(this);
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
            case R.id.bank_msg_back:
                backActivity();
                break;
            case R.id.tv_save:
                BankInfoRequestBean bankBean = new BankInfoRequestBean();
                if (code_edit.getText() != null && !code_edit.getText().toString().equals("")) {
                    bankBean.setIfsc_code(code_edit.getText().toString());
                } else {
                    Toast.makeText(mActivity, "Please fill in THE IFSC code", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (bank_edit.getText() != null && !bank_edit.getText().toString().equals("")) {
                    bankBean.setBank_name(bank_edit.getText().toString());
                } else {
                    Toast.makeText(mActivity, "Please fill in the bank name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (account_edit.getText() != null && !account_edit.getText().toString().equals("")) {
                    bankBean.setBank_account_no(account_edit.getText().toString());
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
                break;
        }
    }

    @Override
    public void success(Object data) {
        utils.dismissDialog(utils);
        if (data instanceof SuccessCommon) {
            SuccessCommon common = (SuccessCommon) data;
            if (common.getStatus() == 1) {
                Toast.makeText(mActivity, "Bank card information saved successfully", Toast.LENGTH_SHORT).show();
                backActivity();
            } else {
                Toast.makeText(mActivity, "Failed to save bank card information", Toast.LENGTH_SHORT).show();
            }
        } else if (data instanceof UserInfoBean) {
            UserInfoBean bean = (UserInfoBean) data;
            if (bean.getStatus() == 1) {
                code_edit.setText(bean.getData().getIfsc_code());
                bank_edit.setText(bean.getData().getBank_name());
                account_edit.setText(bean.getData().getBank_account_no());
            }
        }
    }

    @Override
    public void error(Object error) {
        utils.dismissDialog(utils);
        if (error.toString().trim().equals("HTTP 401")) {
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