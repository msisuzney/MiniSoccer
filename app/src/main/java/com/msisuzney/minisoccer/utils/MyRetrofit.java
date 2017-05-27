package com.msisuzney.minisoccer.utils;

import com.msisuzney.minisoccer.DQDApi.APIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenxin.
 * Date: 2017/5/8.
 * Time: 13:53.
 */

public class MyRetrofit {

    private Retrofit retrofit;
    private APIService apiService;
    private static MyRetrofit myRetrofit;
    private final String base_url = "https://api.dongqiudi.com";
    public static MyRetrofit getMyRetrofit(){
        if(myRetrofit == null){
            synchronized (MyRetrofit.class){
                myRetrofit = new MyRetrofit();
            }
        }
        return myRetrofit;
    }
    public MyRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofit.create(APIService.class);
    }
    public APIService getApiService(){
        return apiService;
    }
}
