package com.msisuzney.minisoccer.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.App;
import com.msisuzney.minisoccer.DQDApi.model.news.DaoSession;
import com.msisuzney.minisoccer.DQDApi.model.specialNewsColumn.SpecialNewsColumn;
import com.msisuzney.minisoccer.DQDApi.model.specialNewsColumn.SpecialNewsColumnArticle;
import com.msisuzney.minisoccer.DQDApi.model.specialNewsColumn.SpecialNewsColumnArticleDao;
import com.msisuzney.minisoccer.utils.MyRetrofit;
import com.msisuzney.minisoccer.view.SpecialNewsColumnView;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/24.
 * Time: 15:45.
 */

public class SpecialNewsColumnPresenter extends MvpBasePresenter<SpecialNewsColumnView> {

    public static final int LOAD_FROM_DB = 0;
    public static final int LOAD_REFRESH = 1;
    public static final int LOAD_MORE = 2;
    int currentPage = 1;
    int lastPage;
    private MyRetrofit myRetrofit = MyRetrofit.getMyRetrofit();

    public void loadData(int mode, String id) {
        if (mode == LOAD_REFRESH) {
            if (isViewAttached())
                getView().showLoading(true);
            loadDataFromNet(id, true);
        } else if (mode == LOAD_FROM_DB) {
            if (isViewAttached())
                getView().showLoading(true);
            loadDataFromDB(id);
        } else if (mode == LOAD_MORE) {
            loadMoreData(id);
        }
    }

    private void loadMoreData(final String id) {
        Log.d("SpecialNews", "loadMoreData " + id + " currentPage " + currentPage + " lastPage " + lastPage);
        if (++currentPage > lastPage) {
            if (isViewAttached())
                getView().showError(new Exception("已是最后一页"), true);
            return;
        }
        myRetrofit.getApiService().getSpecialColumns(id, String.valueOf(currentPage)).enqueue(new Callback<SpecialNewsColumn>() {
            @Override
            public void onResponse(Call<SpecialNewsColumn> call, Response<SpecialNewsColumn> response) {
                if (isViewAttached()) {
                    try {
                        List<SpecialNewsColumnArticle> articles = response.body().getData();
                        currentPage = response.body().getCurrent_page();
                        lastPage = response.body().getLast_page();
                        String main_title = response.body().getTitle();
                        String main_description = response.body().getDescription();
                        String main_logo_url = response.body().getAvatar();
                        for (int i = 0; i < articles.size(); i++) {
                            articles.get(i).setMain_title(main_title);
                            articles.get(i).setMain_description(main_description);
                            articles.get(i).setCurrrentPage(currentPage);
                            articles.get(i).setLastPage(lastPage);
                            articles.get(i).setId(id);
                            articles.get(i).setIsViewed(false);
                            articles.get(i).setMain_logo(main_logo_url);
                        }
                        insertDataToDB(articles);
                        getView().addData(articles);
                        getView().showContent();
                        getView().haveLoadMore(true);
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), true);
                    }
                }
            }

            @Override
            public void onFailure(Call<SpecialNewsColumn> call, Throwable t) {
                currentPage--;
                if (isViewAttached()) {
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), true);
                    getView().haveLoadMore(false);
                }
            }
        });
    }

    private void loadDataFromDB(String id) {
        List<SpecialNewsColumnArticle> articles = queryDataFromDB(id);
        if (articles.size() <= 0) {
            loadData(SpecialNewsColumnPresenter.LOAD_REFRESH, id);
        } else {
            lastPage = articles.get(articles.size() - 1).getLastPage();
            currentPage = articles.get(articles.size() - 1).getCurrrentPage();
            getView().setData(articles);
            getView().showContent();
        }
    }


    private void loadDataFromNet(final String id, final boolean pullToRefresh) {
        currentPage = 1;
        myRetrofit.getApiService().getSpecialColumns(id, String.valueOf(currentPage)).enqueue(new Callback<SpecialNewsColumn>() {

            @Override
            public void onResponse(Call<SpecialNewsColumn> call, Response<SpecialNewsColumn> response) {
                if (isViewAttached()) {
                    try {
                        List<SpecialNewsColumnArticle> articles = response.body().getData();
                        currentPage = response.body().getCurrent_page();
                        lastPage = response.body().getLast_page();
                        String main_title = response.body().getTitle();
                        String main_description = response.body().getDescription();
                        String main_logo_url = response.body().getAvatar();
                        for (int i = 0; i < articles.size(); i++) {
                            articles.get(i).setMain_title(main_title);
                            articles.get(i).setMain_description(main_description);
                            articles.get(i).setCurrrentPage(currentPage);
                            articles.get(i).setLastPage(lastPage);
                            articles.get(i).setId(id);
                            articles.get(i).setIsViewed(false);
                            articles.get(i).setMain_logo(main_logo_url);
                        }
                        deleteAllDataInDB(id);
                        insertDataToDB(articles);
                        getView().setData(articles);
                        getView().showContent();
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), pullToRefresh);
                    }
                }
            }

            @Override
            public void onFailure(Call<SpecialNewsColumn> call, Throwable t) {
                if (isViewAttached()) {
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);

                }
            }
        });
    }

    private void insertDataToDB(List<SpecialNewsColumnArticle> articles) {
        DaoSession daoSession = App.getApp().getDaoSession();
        SpecialNewsColumnArticleDao dao = daoSession.getSpecialNewsColumnArticleDao();
        for (int i = 0; i < articles.size(); i++) {
            dao.insert(articles.get(i));
        }
    }

    public void updateArticleIsViewed(SpecialNewsColumnArticle article) {
        DaoSession daoSession = App.getApp().getDaoSession();
        SpecialNewsColumnArticleDao dao = daoSession.getSpecialNewsColumnArticleDao();
        List<SpecialNewsColumnArticle> list = dao.queryBuilder().where(SpecialNewsColumnArticleDao.Properties.Aid.eq
                (article.getAid())).list();
        SpecialNewsColumnArticle mArticle = list.get(0);
        mArticle.setIsViewed(article.getIsViewed());
        dao.update(mArticle);
    }

    private void deleteAllDataInDB(String id) {
        DaoSession daoSession = App.getApp().getDaoSession();
        SpecialNewsColumnArticleDao dao = daoSession.getSpecialNewsColumnArticleDao();
        DeleteQuery<SpecialNewsColumnArticle> builder = dao.queryBuilder().where(SpecialNewsColumnArticleDao
                .Properties.Id
                .eq(id)).buildDelete();
        builder.executeDeleteWithoutDetachingEntities();
    }

    private List<SpecialNewsColumnArticle> queryDataFromDB(String id) {
        DaoSession daoSession = App.getApp().getDaoSession();
        SpecialNewsColumnArticleDao dao = daoSession.getSpecialNewsColumnArticleDao();
        Query<SpecialNewsColumnArticle> builder = dao.queryBuilder().where(SpecialNewsColumnArticleDao.Properties.Id
                .eq(id)).orderAsc(SpecialNewsColumnArticleDao.Properties._id).build();
        return builder.list();
    }
}
