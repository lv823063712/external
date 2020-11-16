package com.example.external.mvp.myinterface;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @ClassName: StartInterface
 * @CreateDate: 2020/11/14 17:03
 * @Creator: lf
 */
public interface StartInterface {
    interface StartCallBack<T> {
        void startSuccess(T data);

        void startError(T error);
    }

    interface StartMoudle {
        void get(String url, Map<String, Object> head, Map<String, Object> map, Class kind, StartCallBack startCallBack);

        void postQueryBody(String url, Map<String, Object> head, Map<String, Object> map, RequestBody body, Class kind, StartCallBack startCallBack);
    }

    interface StartPresenter {
        void get(String url, Map<String, Object> head, Map<String, Object> map, Class kind);

        void postQueryBody(String url, Map<String, Object> head, Map<String, Object> map, RequestBody body, Class kind);
    }

    interface StrartView<T> {
        void success(T data);

        void error(T error);
    }
}
