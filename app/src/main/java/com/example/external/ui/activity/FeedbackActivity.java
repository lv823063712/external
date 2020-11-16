package com.example.external.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.example.external.R;
import com.example.external.base.BaseActivity;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mFeedBack;

    @Override
    protected int getLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initData() {
        initView();
    }

    private void initView() {
        mFeedBack = mActivity.findViewById(R.id.feed_back);
        mFeedBack.setOnClickListener(this);
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
            case R.id.feed_back:
                finish();
                break;
        }
    }
}