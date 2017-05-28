package com.msisuzney.minisoccer.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.App;
import com.msisuzney.minisoccer.DQDApi.APIService;
import com.msisuzney.minisoccer.DQDApi.model.news.DaoSession;
import com.msisuzney.minisoccer.DQDApi.model.news.NextUrl;
import com.msisuzney.minisoccer.DQDApi.model.news.NextUrlDao;
import com.msisuzney.minisoccer.DQDApi.model.twins.Album;
import com.msisuzney.minisoccer.DQDApi.model.twins.Feedlist;
import com.msisuzney.minisoccer.DQDApi.model.twins.FeedlistDao;
import com.msisuzney.minisoccer.DQDApi.model.twins.Twins;
import com.msisuzney.minisoccer.utils.MyRetrofit;
import com.msisuzney.minisoccer.view.TwinsView;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TwinsPresenter extends MvpBasePresenter<TwinsView> {

    public static final int LOAD_MORE = 0x01;
    public static final int LOAD_FROM_DB = 0x02;
    public static final int LOAD_REFRESH = 0x03;
    public static final String TWINS_KIND = "1";
    public static final String KIND = "kind";
    public static final String GIRL_KIND = "2";
    private static final long TWINS_NEXT_URL_ID = 0x1234543;
    private static final long GIRL_NEXT_URL_ID = 0x1234542;
    private String nextPageUrl;


    public void loadData(int mode, String kind) {
        if (mode == LOAD_REFRESH) {
            if (isViewAttached())
                getView().showLoading(true);
            loadDataFromNet(true, kind);
        } else if (mode == LOAD_FROM_DB) {
            if (isViewAttached())
                getView().showLoading(false);
            loadDataFromDB(kind);
        } else if (mode == LOAD_MORE) {
            loadMoreDataFromNet(getNextUrlFromDB(kind), kind);
        }
    }


    private void loadDataFromNet(final boolean pullToRefresh, final String kind) {
        App.getApp().getMyRetrofit().getApiService().getTwins(kind).enqueue(new Callback<Twins>() {
            @Override
            public void onResponse(Call<Twins> call, Response<Twins> response) {
                if (isViewAttached()) {
                    try {
                        Twins twins = response.body();
                        deleteAllDataInDB(kind);//在每次需要重新加载的时候都删除所有以前的数据
                        List<Feedlist> list = twins.getFeedlist();
                        Album album;
                        for (int i = 0; i < list.size(); i++) {
                            if ((album = list.get(i).getAlbum()) != null && album.getPics() != null && album.getPics
                                    ().size() > 0) {
                                list.get(i).setPic_url(album.getPics().get(0).getUrl());
                                list.get(i).setPic_width(album.getPics().get(0).getWidth());
                                list.get(i).setPic_height(album.getPics().get(0).getHeight());
                                list.get(i).setKind(kind);
                            }
                        }
                        addDataToDB(list);//添加数据
                        nextPageUrl = twins.getNext();
                        updateNextUrlInDB(nextPageUrl, kind);//更新下一页的url
                        getView().setData(list);
                        getView().showContent();

                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), pullToRefresh);

                    }
                }
            }

            @Override
            public void onFailure(Call<Twins> call, Throwable t) {
                if (isViewAttached()) {
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);

                }
            }
        });
    }

    private void loadMoreDataFromNet(String nextPageUrl, final String kind) {
        App.getApp().getMyRetrofit().getApiService().getMoreTwins(nextPageUrl).enqueue(new Callback<Twins>() {
            @Override
            public void onResponse(Call<Twins> call, Response<Twins> response) {
                if (isViewAttached()) {
                    try {
                        Twins twins = response.body();
                        List<Feedlist> list = twins.getFeedlist();
                        updateNextUrlInDB(twins.getNext(), kind);//更新下一页的url
                        Album album;
                        for (int i = 0; i < list.size(); i++) {
                            if ((album = list.get(i).getAlbum()) != null && album.getPics() != null && album.getPics
                                    ().size() > 0) {
                                list.get(i).setPic_url(album.getPics().get(0).getUrl());
                                list.get(i).setPic_width(album.getPics().get(0).getWidth());
                                list.get(i).setPic_height(album.getPics().get(0).getHeight());
                                list.get(i).setKind(kind);
                            }
                        }
                        addDataToDB(list);//添加数据
                        getView().addData(list);
                        getView().haveLoadMore(true);
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), true);
                        getView().haveLoadMore(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<Twins> call, Throwable t) {
                if (isViewAttached()) {
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), true);
                    getView().haveLoadMore(false);
                }
            }
        });
    }


    private void loadDataFromDB(String kind) {
        List<Feedlist> list = queryDataFromDB(kind);
        if (list.size() <= 0) {
            //第一次加载
            loadDataFromNet(false,kind);
        } else {
            if (isViewAttached()) {
                getView().setData(list);
                getView().showContent();
            }
        }
    }

    private void addDataToDB(List<Feedlist> feedlists) {
        DaoSession daoSession = App.getApp().getDaoSession();
        FeedlistDao dao = daoSession.getFeedlistDao();
        for (Feedlist f : feedlists) {
            dao.insert(f);
        }
    }

    private void updateNextUrlInDB(String nextPageUrl, String kind) {
        long NEXT_URL_ID;
        if (kind.equals(TWINS_KIND)) {
            NEXT_URL_ID = TWINS_NEXT_URL_ID;
        } else {
            NEXT_URL_ID = GIRL_NEXT_URL_ID;
        }
        DaoSession daoSession = App.getApp().getDaoSession();
        NextUrlDao dao = daoSession.getNextUrlDao();
        List<NextUrl> nextUrls = dao.queryBuilder().where(NextUrlDao.Properties.NewsId.eq(NEXT_URL_ID)).list();
        if (nextUrls.size() == 0) {
            NextUrl url = new NextUrl(NEXT_URL_ID, nextPageUrl);
            dao.insert(url);
        } else {
            NextUrl url = nextUrls.get(0);
            url.setNextUrl(nextPageUrl);
            dao.update(url);
        }

    }

    private String getNextUrlFromDB(String kind) {
        long NEXT_URL_ID;
        if (kind.equals(TWINS_KIND)) {
            NEXT_URL_ID = TWINS_NEXT_URL_ID;
        } else {
            NEXT_URL_ID = GIRL_NEXT_URL_ID;
        }
        DaoSession daoSession = App.getApp().getDaoSession();
        NextUrlDao dao = daoSession.getNextUrlDao();
        List<NextUrl> nextUrls = dao.queryBuilder().where(NextUrlDao.Properties.NewsId.eq(NEXT_URL_ID)).list();
        if (nextUrls.size() == 0) {
            if (isViewAttached()) {
                getView().showError(new Exception("数据从数据库加载错误"), true);
            }
        }
        return nextUrls.get(0).getNextUrl();
    }

    private List<Feedlist> queryDataFromDB(String kind) {
        DaoSession daoSession = App.getApp().getDaoSession();
        FeedlistDao dao = daoSession.getFeedlistDao();
        Query<Feedlist> query = dao.queryBuilder().where(FeedlistDao.Properties.Kind.eq(kind)).orderAsc(FeedlistDao
                .Properties._id).build();
        return query.list();
    }

    private void deleteAllDataInDB(String kind) {
        DaoSession daoSession = App.getApp().getDaoSession();
        FeedlistDao dao = daoSession.getFeedlistDao();
        // DeleteQuery<Article> query = dao.queryBuilder().where(ArticleDao.Properties.NewsId.eq(newsId)).buildDelete();
        //        query.executeDeleteWithoutDetachingEntities();
        DeleteQuery<Feedlist> query = dao.queryBuilder().where(FeedlistDao.Properties.Kind.eq(kind)).buildDelete();
        query.executeDeleteWithoutDetachingEntities();
    }


}
