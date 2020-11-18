package com.example.external.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.utils.StatusBarUtil;

/**
 * 个人中心
 */
public class MyProfileActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private RelativeLayout mBasicRelative, mWorkRelative, mBankRelative;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_profile;
    }

    @Override
    protected void initData() {
        StatusBarUtil.setTextColor(this);
        initView();
        mBack.setOnClickListener(this);
        mBasicRelative.setOnClickListener(this);
        mWorkRelative.setOnClickListener(this);
        mBankRelative.setOnClickListener(this);
    }

    private void initView() {
        mBack = mActivity.findViewById(R.id.profile_back);
        mBasicRelative = mActivity.findViewById(R.id.basic_Relative);
        mWorkRelative = mActivity.findViewById(R.id.work_relative);
        mBankRelative = mActivity.findViewById(R.id.bank_relative);
    }

    @Override
    protected void setClick() {

    }

    @Override
    protected void preLogic() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_back:
                finish();
                break;
            case R.id.basic_Relative:
                Intent intent = new Intent(this, BasicInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.work_relative:
                Intent workIntent = new Intent(this, WorkInformationActivity.class);
                startActivity(workIntent);
                break;
            case R.id.bank_relative:
                Intent bankIntent = new Intent(this, BankInformationActivity.class);
                startActivity(bankIntent);
                break;
        }
    }
}