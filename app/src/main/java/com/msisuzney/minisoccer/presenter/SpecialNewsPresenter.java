package com.msisuzney.minisoccer.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.App;
import com.msisuzney.minisoccer.DQDApi.model.news.DaoSession;
import com.msisuzney.minisoccer.DQDApi.model.specialNews.ArticleSpecial;
import com.msisuzney.minisoccer.DQDApi.model.specialNews.ArticleSpecialDao;
import com.msisuzney.minisoccer.DQDApi.model.specialNews.SpecialNews;
import com.msisuzney.minisoccer.DQDApi.MyRetrofit;
import com.msisuzney.minisoccer.view.SpecialNewsView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/24.
 * Time: 13:33.
 */

public class SpecialNewsPresenter extends MvpBasePresenter<SpecialNewsView> {

    public static final int LOAD_FROM_DB = 0x02;
    public static final int LOAD_REFRESH = 0x03;



    public void loadData(int mode) {
        if (mode == LOAD_FROM_DB) {
            loadDataFromDB();
        } else if (mode == LOAD_REFRESH) {
            loadDataFromNet(true);
        }
    }

    private void loadDataFromNet(final boolean pullToRefresh) {
        MyRetrofit.getMyRetrofit().getApiService().getSpecial().enqueue(new Callback<SpecialNews>() {
            @Override
            public void onResponse(Call<SpecialNews> call, Response<SpecialNews> response) {
                if (isViewAttached()) {
                    try {
                        deleteAllDataInDB();
                        List<ArticleSpecial> articles = response.body().getArticles();
                        addDataToDB(articles);
                        getView().setData(articles);
                        getView().showContent();
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析加载错误"), pullToRefresh);
                    }
                }
            }

            @Override
            public void onFailure(Call<SpecialNews> call, Throwable t) {
                if (isViewAttached()) {
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);
                }
            }
        });
    }

    private void addDataToDB(List<ArticleSpecial> articles) {
        DaoSession daoSession = App.getApp().getDaoSession();
        ArticleSpecialDao dao = daoSession.getArticleSpecialDao();
        for (ArticleSpecial article : articles) {
            dao.insert(article);
        }
    }

    private void deleteAllDataInDB() {
        DaoSession daoSession = App.getApp().getDaoSession();
        ArticleSpecialDao dao = daoSession.getArticleSpecialDao();
        dao.deleteAll();
    }

    private void loadDataFromDB() {
        DaoSession daoSession = App.getApp().getDaoSession();
        ArticleSpecialDao dao = daoSession.getArticleSpecialDao();
        List<ArticleSpecial> articles = dao.loadAll();
        if (articles.size() <= 0) {
            loadDataFromNet(false);
        } else {
            if (isViewAttached()) {
                getView().setData(articles);
                getView().showContent();
            }
        }
    }

}
