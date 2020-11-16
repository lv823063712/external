package com.example.external.mvp.network;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @ClassName: Api
 * @Description:API interface
 * @CreateDate: 2020/11/14 16:34
 * @Creator: lf
 */
public interface ApiService {
    @GET
    Observable<ResponseBody> get(@Url String url, @HeaderMap Map<String, Object> headMap, @QueryMap Map<String, Object> map);

    @POST
    Observable<ResponseBody> postQueryBody(@Url String url, @HeaderMap Map<String, Object> headMap, @QueryMap Map<String, Object> map, @Body RequestBody body);
}
