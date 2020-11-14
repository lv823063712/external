package com.example.external.mvp.presenter;

import com.example.external.mvp.moudle.StartMoudle;
import com.example.external.mvp.myinterface.StartInterface;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @ClassName: StartPresenter
 * @Description:
 * @CreateDate: 2020/11/14 17:11
 * @Creator: lf
 */
public class StartPresenter implements StartInterface.StartPresenter {
    private StartInterface.StrartView myView;
    private StartMoudle startMoudle;

    public StartPresenter(StartInterface.StrartView myView) {
        this.myView = myView;
        startMoudle = new StartMoudle();
    }

    @Override
    public void get(String url, Map<String, Object> head, Map<String, Object> map, Class kind) {
        startMoudle.get(url, head, map, kind, new StartInterface.StartCallBack() {
            @Override
            public void startSuccess(Object data) {
                if (myView != null) {
                    myView.success(data);
                }
            }

            @Override
            public void startError(Object error) {
                if (myView != null && error != null) {
                    myView.error(error);
                }
            }
        });
    }

    @Override
    public void postQueryBody(String url, Map<String, Object> head, Map<String, Object> map, RequestBody body, Class kind) {
        startMoudle.postQueryBody(url, head, map, body, kind, new StartInterface.StartCallBack() {
            @Override
            public void startSuccess(Object data) {
                if (myView != null) {
                    myView.success(data);
                }
            }

            @Override
            public void startError(Object error) {
                if (myView != null && error != null) {
                    myView.error(error);
                }
            }
        });
    }


    public void onDatacth() {
        if (startMoudle != null) {
            startMoudle = null;
        }
        if (myView != null) {
            myView = null;
        }
    }
}
