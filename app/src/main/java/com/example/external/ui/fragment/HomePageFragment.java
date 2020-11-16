package com.example.external.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.external.R;
import com.example.external.base.BaseFragment;
import com.example.external.ui.activity.GetMoneyActivity;
import com.example.external.ui.activity.IdentificationActivity;


public class HomePageFragment extends BaseFragment {

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView() {
        TextView Borrow = mActivity.findViewById(R.id.Borrow);
        AppCompatImageView test = mActivity.findViewById(R.id.test);
        Borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, GetMoneyActivity.class);
                startActivity(intent);
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, IdentificationActivity.class);
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