package com.example.external.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.InflateException;
import android.view.Window;

import androidx.annotation.Nullable;

import com.example.external.R;
import com.example.external.config.AppStatusConstant;
import com.example.external.ui.activity.StartActivity;
import com.example.external.utils.AppStatusManager;
import com.example.external.utils.StatusBarUtil;
import com.gyf.immersionbar.ImmersionBar;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @ClassName: BaseActivity
 * @CreateDate: 2020/11/13 17:43
 * @Creator: lf
 */
public abstract class BaseActivity extends SupportActivity {
    protected Activity mActivity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        switch (AppStatusManager.getInstance().getAppStatus()) {
            case AppStatusConstant.STATUS_FORCE_KILLED:
                Intent intent = new Intent(mActivity, StartActivity.class);
                startActivity(intent);
                break;
            case AppStatusConstant.STATUS_NORMAL:
                ImmersionBar.with(this)
                        //解决软键盘与底部输入框冲突问题
                        .keyboardEnable(true)
                        .statusBarDarkFont(true, 0.2f)
                        .init();
//                setStatusBar();
                init();
                break;
            default:break;
        }
    }

    protected abstract int getLayout();

    protected abstract void initData();

    protected abstract void setClick();

    protected abstract void preLogic();

    void init() {
        try {
            if (getLayout() != 0) {
                setContentView(getLayout());
                initData();
                setClick();
                preLogic();
            }
        } catch (Exception e) {
            if (e instanceof InflateException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    protected void setStatusBar() {
        ImmersionBar.with(this)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .keyboardEnable(true)
                .autoStatusBarDarkModeEnable(true,0.2f)
                .init();
//        StatusBarUtil.setStatusBar(mActivity);
    }

    protected void backActivity() {
        finish();
        overridePendingTransition(R.anim.not_exit_push_left_in, R.anim.push_right_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}