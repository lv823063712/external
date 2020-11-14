package com.example.external;

import android.app.Activity;
import android.app.Application;

import androidx.multidex.MultiDex;

import com.example.external.ui.activity.StartActivity;

import java.util.LinkedHashSet;

import cat.ereza.customactivityoncrash.config.CaocConfig;
import me.jessyan.autosize.AutoSize;

/**
 * @ClassName: MyApplication
 * @CreateDate: 2020/11/14 16:18
 * @Creator: lf
 */
public class MyApplication extends Application {
    private MyApplication mContext;
    private LinkedHashSet<Activity> mAllActivities = new LinkedHashSet();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //dex 65535
        MultiDex.install(this);
        AutoSize.initCompatMultiProcess(this);
        initCrash();
    }

    private void initCrash() {
        CaocConfig.Builder.create()
                .enabled(true)
                .showErrorDetails(false)
                .showRestartButton(true)
                .trackActivities(false)
                .minTimeBetweenCrashesMs(2000)
                .errorActivity(StartActivity.class)
            .restartActivity(StartActivity.class)
            .apply();
    }
}
