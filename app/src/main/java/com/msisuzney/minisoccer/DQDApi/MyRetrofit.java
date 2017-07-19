package com.msisuzney.minisoccer.DQDApi;

import com.msisuzney.minisoccer.App;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
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
    public static final String cache_file = "OKHTTP_CACHE";
    private static final int cache_size =  50*1024*1024;
    private MyRetrofit() {
        File file  = App.getApp().getExternalCacheDir();
        File cacheFile = new File(file,cache_file);
        if (!cacheFile.exists()){
            cacheFile.mkdir();
        }
        Cache cache = new Cache(cacheFile,cache_size);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                .callFactory(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
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
