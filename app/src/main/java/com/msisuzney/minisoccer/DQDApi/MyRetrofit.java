package com.msisuzney.minisoccer.DQDApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenxin.
 * Date: 2017/5/8.
 * Time: 13:53.
 */

public class MyRetrofit {

    private static final String base_url = "https://api.dongqiudi.com";
    private APIService apiService;

    public MyRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofit.create(APIService.class);
    }

    public static MyRetrofit getMyRetrofit() {
        return MyRetrofitHolder.myRetrofit;
    }

    public APIService getApiService() {
        return apiService;
    }

    private static class MyRetrofitHolder {
        static final MyRetrofit myRetrofit = new MyRetrofit();
    }
}
