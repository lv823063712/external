package com.example.external.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.external.R;
import com.example.external.base.BaseFragment;
import com.example.external.ui.activity.GetMoneyActivity;


public class HomePageFragment extends BaseFragment {

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView() {
        TextView Borrow = mActivity.findViewById(R.id.Borrow);
        Borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, GetMoneyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {

    }
}