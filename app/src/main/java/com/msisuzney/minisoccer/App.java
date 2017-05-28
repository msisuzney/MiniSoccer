package com.msisuzney.minisoccer;

import android.app.Application;

import com.msisuzney.minisoccer.DQDApi.model.news.DaoMaster;
import com.msisuzney.minisoccer.DQDApi.model.news.DaoSession;
import com.msisuzney.minisoccer.utils.MyRetrofit;

import org.greenrobot.greendao.database.Database;

/**
 * Created by chenxin.
 * Date: 2017/5/20.
 * Time: 19:37.
 */

public class App extends Application{
    private DaoSession daoSession;
    private static App app;
    private MyRetrofit myRetrofit = MyRetrofit.getMyRetrofit();
    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"msisuzney-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        app = this;
    }
    public static App getApp(){
        return app;
    }
    public DaoSession getDaoSession(){
        return daoSession;
    }
    public MyRetrofit getMyRetrofit(){
        return myRetrofit;
    }
}
