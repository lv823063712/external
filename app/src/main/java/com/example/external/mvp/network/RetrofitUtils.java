package com.example.external.mvp.network;

import android.util.Log;

import com.example.external.mvp.utils.MLogInterceptor;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName: RetrofitUtils
 * @CreateDate: 2020/11/14 16:45
 * @Creator: lf
 */
public class RetrofitUtils {

    private ApiService apiService;

    private RetrofitUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new MLogInterceptor())
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.getApiUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitUtils getInstance() {
        return ViewHolder.RETROFIT_UTILS;
    }

    private static class ViewHolder {
        private static final RetrofitUtils RETROFIT_UTILS = new RetrofitUtils();
    }

    public void get(String url, Map<String, Object> headerMap, Map<String, Object> map, final setHttpListener httpListener){
        apiService.get(url, headerMap, map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        if (httpListener != null) {
                            try {
                                httpListener.success(responseBody.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("onError", "get_onError" + e.getMessage());
                        if (httpListener != null) {
                            httpListener.error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postString(String url, Map<String, Object> headerMap, Map<String, Object> map, RequestBody body, final setHttpListener httpListener){
        apiService.postQueryBody(url, headerMap, map, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        if (httpListener != null) {
                            try {
                                httpListener.success(responseBody.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("onError", "get_onError" + e.getMessage());
                        if (httpListener != null) {
                            httpListener.error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface setHttpListener<T> {
        void success(T data);

        void error(T error);
    }

}
