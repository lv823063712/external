package com.example.external.mvp.moudle;

import com.example.external.mvp.myinterface.StartInterface;
import com.example.external.mvp.network.RetrofitUtils;
import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @ClassName: StartMoudle
 * @CreateDate: 2020/11/14 17:07
 * @Creator: lf
 */
public class StartMoudle implements StartInterface.StartMoudle {
    @Override
    public void get(String url, Map<String, Object> head, Map<String, Object> map, Class kind, StartInterface.StartCallBack startCallBack) {
        RetrofitUtils.getInstance().get(url, head, map, new RetrofitUtils.setHttpListener() {
            @Override
            public void success(Object data) {
                Gson gson = new Gson();
                Object o = gson.fromJson((String) data, kind);
                startCallBack.startSuccess(o);
            }

            @Override
            public void error(Object error) {
                startCallBack.startError(error);

            }
        });
    }

    @Override
    public void postQueryBody(String url, Map<String, Object> head, Map<String, Object> map, RequestBody body, Class kind, StartInterface.StartCallBack startCallBack) {
        RetrofitUtils.getInstance().postString(url, head, map, body, new RetrofitUtils.setHttpListener() {
            @Override
            public void success(Object data) {
                Gson gson = new Gson();
                Object o = gson.fromJson((String) data, kind);
                startCallBack.startSuccess(o);
            }

            @Override
            public void error(Object error) {
                startCallBack.startError(error);
            }
        });
    }
}
