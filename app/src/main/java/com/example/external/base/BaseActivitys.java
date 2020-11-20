package com.example.external.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;

import com.example.external.R;
import com.example.external.utils.StatusBarUtil;
import com.gyf.immersionbar.ImmersionBar;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivitys extends SupportActivity {
    protected Activity mActivity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setStatusBar();
        init();
    }

    protected abstract int getLayout();

    protected abstract void initData();

    protected abstract void setClick();

    protected abstract void preLogic();

    void init() {
        if (getLayout() != 0) {
            setContentView(getLayout());
            initData();
            setClick();
            preLogic();
        }
    }

    protected void setStatusBar() {
        ImmersionBar.with(this)
                //解决软键盘与底部输入框冲突问题
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .init();
//        StatusBarUtil.setStatusBar(mActivity);
    }

    protected void backActivity() {
        finish();
        overridePendingTransition(R.anim.not_exit_push_left_in, R.anim.push_right_out);
    }
}
