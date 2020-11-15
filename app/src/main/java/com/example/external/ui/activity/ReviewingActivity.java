package com.example.external.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.external.R;
import com.example.external.base.BaseActivity;

public class ReviewingActivity extends BaseActivity {
    private TextView first_reviewing, step_tv, some_user, some_user_dollor;
    private RelativeLayout first_show, second_reviewing;
    private int next_int = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_reviewing;
    }

    @Override
    protected void initData() {
        initView();
    }

    private void initView() {
        first_reviewing = findViewById(R.id.first_reviewing);
        first_show = findViewById(R.id.first_show);
        second_reviewing = findViewById(R.id.second_reviewing);
        some_user = findViewById(R.id.some_user);
        some_user_dollor = findViewById(R.id.some_user_dollor);
        step_tv = findViewById(R.id.step_tv);

    }

    @Override
    protected void setClick() {
        step_tv.setOnClickListener(v -> {
            if (next_int == 0) {
                first_show.setVisibility(View.GONE);
                second_reviewing.setVisibility(View.VISIBLE);
                first_reviewing.setText("2. Approved");
                next_int = 1;
            } else {
                Intent intent = new Intent(mActivity, GetMoneyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void preLogic() {

    }
}