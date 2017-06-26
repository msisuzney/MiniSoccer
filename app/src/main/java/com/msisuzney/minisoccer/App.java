package com.msisuzney.minisoccer;

import android.app.Application;

import com.msisuzney.minisoccer.DQDApi.model.news.DaoMaster;
import com.msisuzney.minisoccer.DQDApi.model.news.DaoSession;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.greendao.database.Database;

/**
 * Created by chenxin.
 * Date: 2017/5/20.
 * Time: 19:37.
 */

public class App extends Application {
    private static App app;
    private DaoSession daoSession;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "msisuzney-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        app = this;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
