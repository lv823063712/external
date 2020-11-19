package com.example.external.ui.activity;

import android.widget.FrameLayout;

import com.example.external.R;
import com.example.external.base.BaseActivity;
import com.example.external.ui.fragment.HomePageFragment;
import com.example.external.ui.fragment.MeFragment;
import com.example.external.utils.StatusBarUtil;
import com.yinglan.alphatabs.AlphaTabsIndicator;
import com.yinglan.alphatabs.OnTabChangedListner;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity {

    private FrameLayout home_yidian_fl_container;
    private AlphaTabsIndicator bottom_navigation;
    private ArrayList<SupportFragment> mFragments = new ArrayList<>();
    private int mTabPosition = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        StatusBarUtil.setTextColor(this);
        initView();
        initFragment();
        initBottomNavigationView();
    }


    private void initView() {
        home_yidian_fl_container = findViewById(R.id.home_yidian_fl_container);
        bottom_navigation = findViewById(R.id.bottom_navigation);
    }

    private void initFragment() {
        mFragments.add(new HomePageFragment());
        mFragments.add(new MeFragment());
        loadMultipleRootFragment(R.id.home_yidian_fl_container, 0, mFragments.get(0), mFragments.get(1));
    }

    private void initBottomNavigationView() {
        bottom_navigation.setOnTabChangedListner(tabNum -> {
            showHideFragment(mFragments.get(tabNum), mFragments.get(mTabPosition));
            mTabPosition = tabNum;
        });
    }

    @Override
    protected void setClick() {

    }

    @Override
    protected void preLogic() {

    }

}