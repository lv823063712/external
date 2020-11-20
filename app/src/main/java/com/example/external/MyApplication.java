package com.example.external;

import android.app.Activity;
import android.app.Application;

import androidx.multidex.MultiDex;

import com.example.external.ui.activity.StartActivity;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.LinkedHashSet;

import cat.ereza.customactivityoncrash.config.CaocConfig;
import me.jessyan.autosize.AutoSize;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * @ClassName: MyApplication
 * @CreateDate: 2020/11/14 16:18
 * @Creator: lf
 */
public class MyApplication extends Application {
    private MyApplication mContext;
    private LinkedHashSet<Activity> mAllActivities = new LinkedHashSet();

    static {
        ClassicsHeader.REFRESH_HEADER_REFRESHING = "Is refreshing...";
        ClassicsHeader.REFRESH_HEADER_LOADING = "Being loaded...";
        ClassicsHeader.REFRESH_HEADER_RELEASE = "Release refresh now";
        ClassicsHeader.REFRESH_HEADER_FINISH = "Refresh to complete";
        ClassicsHeader.REFRESH_HEADER_FAILED = "Refresh the failure";
        ClassicsHeader.REFRESH_HEADER_SECONDARY = "Release into the second floor";
        ClassicsFooter.REFRESH_FOOTER_PULLING = "Pull up loads more";
        ClassicsFooter.REFRESH_FOOTER_RELEASE = "Release immediate load";
        ClassicsFooter.REFRESH_FOOTER_REFRESHING = "Is refreshing...";
        ClassicsFooter.REFRESH_FOOTER_LOADING = "Being loaded...";
        ClassicsFooter.REFRESH_FOOTER_FINISH = "loaded";
        ClassicsFooter.REFRESH_FOOTER_FAILED = "Load failed";
        ClassicsFooter.REFRESH_FOOTER_NOTHING = "There's no more data";

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/montserrat_bold.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        //dex 65535
        MultiDex.install(this);
        AutoSize.initCompatMultiProcess(this);
        initCrash();
        initSmart();
    }

    private void initSmart() {

    }

    private void initCrash() {
        CaocConfig
                .Builder.create()
                .enabled(true)
                .showErrorDetails(false)
                .showRestartButton(false)
                .trackActivities(false)
                .minTimeBetweenCrashesMs(2000)
                .errorActivity(StartActivity.class)
                .restartActivity(StartActivity.class)
                .apply();
    }
}
