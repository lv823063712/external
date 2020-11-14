package com.example.external.utils;


import com.example.external.config.AppStatusConstant;

public class AppStatusManager {

    public int appStatus = AppStatusConstant.STATUS_FORCE_KILLED;

    public static AppStatusManager appStatusManager;

    private AppStatusManager() {

    }


    public static AppStatusManager getInstance() {
        if (appStatusManager == null) {
            appStatusManager = new AppStatusManager();
        }
        return appStatusManager;
    }

    public int getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(int appStatus) {
        this.appStatus = appStatus;
    }
}
