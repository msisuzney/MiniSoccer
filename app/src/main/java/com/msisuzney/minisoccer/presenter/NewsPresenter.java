package com.msisuzney.minisoccer.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.App;
import com.msisuzney.minisoccer.DQDApi.MyRetrofit;
import com.msisuzney.minisoccer.DQDApi.model.news.Article;
import com.msisuzney.minisoccer.DQDApi.model.news.ArticleDao;
import com.msisuzney.minisoccer.DQDApi.model.news.DaoSession;
import com.msisuzney.minisoccer.DQDApi.model.news.News;
import com.msisuzney.minisoccer.DQDApi.model.news.NextUrl;
import com.msisuzney.minisoccer.DQDApi.model.news.NextUrlDao;
import com.msisuzney.minisoccer.view.NewsView;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by chenxin.
 * Date: 2017/5/8.
 * Time: 12:56.
 * 本来想把recyclerView的adapter和各种listener写到presenter中，但adapter需要用context来加载itemView布局这就要
 * 传入getActivity(),对于Activity就是this，这样又向presenter传入了Activity的实际引用，mosby这个库不能
 * 通过getView()获取Context,并且如果写context的get方法的话，presenter就会有了view的强引用，容易内存泄漏
 * ,并且mvp的目的就是解耦啊？所以就把adapter写到了view中。而如果要写各种listener中就要在MvpView中定义各种view
 * 的get方法，在presenter中用getView().getXX获取，然后init()，问题是复写setListener的中的复写方法会涉及到
 * recyclerView的其他部分的引用，难道要给他们写各种get方法？不这样的话，直接给recyclerView写get方法，把所有关于
 * recyclerView的初始化写到presenter中？最后还是要给它设置adapter，adapter不能再NewsPresenter中初始化！
 * <p>
 * 所以recyclerView的adapter和各种listener写到view中，一些数据处理逻辑也只能在view中了
 */

public class NewsPresenter extends MvpBasePresenter<NewsView> {

    public static final int LOAD_MORE = 0x01;
    public static final int LOAD_FROM_DB = 0x02;
    public static final int LOAD_REFRESH = 0x03;

    private String nextPageUrl;
    private DaoSession daoSession = App.getApp().getDaoSession();
    private ArticleDao dao = daoSession.getArticleDao();

    public void loadData(int newsId, int mode) {
        if (mode == LOAD_REFRESH) {
            if (isViewAttached())
                getView().showLoading(true);
            loadDataFromNet(newsId, true);
        } else if (mode == LOAD_FROM_DB) {
            if (isViewAttached())
                getView().showLoading(false);
            loadDataFromDB(newsId);
        } else if (mode == LOAD_MORE) {
            loadMoreDataFromNet(getNextUrlFromDB(newsId), newsId);
        }
    }

    /**
     * 网络 请求数据
     *
     * @param newsId        联赛id
     * @param pullToRefresh pullToRefresh
     */
    private void loadDataFromNet(final int newsId, final boolean pullToRefresh) {
//        Log.d("loadDataFromNet", "loadDataFromNet newsId " + newsId);
        String id = String.valueOf(newsId);
        MyRetrofit.getMyRetrofit().getApiService().getNews(id)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<News>() {
                    @Override
                    public void call(News news) {
                        Log.d("rxjava", "doOnNext thread " + Thread.currentThread().getName());
                        deleteAllDataInDB(newsId);//在每次需要重新加载的时候都删除所有以前的数据
                        nextPageUrl = news.getNext();
                        updateNextUrlInDB(nextPageUrl, newsId);//更新下一页的url
                    }
                }).flatMap(new Func1<News, Observable<Article>>() {
            @Override
            public Observable<Article> call(News news) {
                return Observable.from(news.getArticles());
            }
        }).map(new Func1<Article, Article>() {
            @Override
            public Article call(Article article) {
                Log.d("rxjava", "call thread " + Thread.currentThread().getName());
                article.setNewsId(newsId);
                return article;
            }
        }).doOnNext(new Action1<Article>() {
            @Override
            public void call(Article article) {
                Log.d("rxjava", "doOnNext2 thread " + Thread.currentThread().getName());
                addArticleToDB(article);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Article>() {
            List<Article> articles = new ArrayList<Article>();

            @Override
            public void onCompleted() {
                if (isViewAttached()) {
                    getView().setData(articles);
                    getView().showContent();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);
                }
            }

            @Override
            public void onNext(Article article) {
                Log.d("rxjava", "onNext thread " + Thread.currentThread().getName());
                articles.add(article);
            }
        });

//                .enqueue(new Callback<News>() {
//            @Override
//            public void onResponse(Call<News> call, Response<News> response) {
//                if (isViewAttached()) {
//                    try {
//                        News news = response.body();
//                        deleteAllDataInDB(newsId);//在每次需要重新加载的时候都删除所有以前的数据
//                        List<Article> articles = news.getArticles();
//                        for (int i = 0; i < articles.size(); i++) {
//                            articles.get(i).setNewsId(newsId);
//                        }
//                        addDataToDB(articles);//添加数据
//                        nextPageUrl = news.getNext();
//                        updateNextUrlInDB(nextPageUrl, newsId);//更新下一页的url
//                        getView().setData(articles);
//                        getView().showContent();
//
//                    } catch (Exception e) {
//                        getView().showError(new Exception("数据解析错误"), pullToRefresh);
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<News> call, Throwable t) {
//                if (isViewAttached()) {
//                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);
//
//                }
//            }
//        });
    }

    /**
     * 加载更多的数据，当recyclerView滑动到最后一个子元素时调用
     *
     * @param nextPageUrl nextPageUrl
     * @param newsId      newsId
     */
    private void loadMoreDataFromNet(String nextPageUrl, final int newsId) {
        MyRetrofit.getMyRetrofit().getApiService().getMoreNews(nextPageUrl).subscribeOn(Schedulers.io())
                .doOnNext(new Action1<News>() {
                    @Override
                    public void call(News news) {
                        Log.d("rxjava", "doOnNext thread " + Thread.currentThread().getName());
                        updateNextUrlInDB(news.getNext(), newsId);//更新下一页的url
                    }
                })
                .flatMap(new Func1<News, Observable<Article>>() {
                    @Override
                    public Observable<Article> call(News news) {
                        return Observable.from(news.getArticles());
                    }
                }).map(new Func1<Article, Article>() {
            @Override
            public Article call(Article article) {
                Log.d("rxjava", "call thread " + Thread.currentThread().getName());
                article.setNewsId(newsId);
                return article;
            }
        }).doOnNext(new Action1<Article>() {
            @Override
            public void call(Article article) {
                Log.d("rxjava", "doOnNext2 thread " + Thread.currentThread().getName());
                addArticleToDB(article);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Article>() {
            List<Article> articles = new ArrayList<Article>();

            @Override
            public void onCompleted() {
                if (isViewAttached()) {
                    getView().addData(articles);
                    getView().haveLoadMore(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), true);
                    getView().haveLoadMore(false);
                }
            }

            @Override
            public void onNext(Article article) {
                Log.d("rxjava", "onNext thread " + Thread.currentThread().getName());
                articles.add(article);
            }
        });


//                .enqueue(new Callback<News>() {
//            @Override
//            public void onResponse(Call<News> call, Response<News> response) {
//                if (isViewAttached()) {
//                    try {
//                        News news = response.body();
//                        List<Article> articles = news.getArticles();
//                        for (int i = 0; i < articles.size(); i++) {
//                            articles.get(i).setNewsId(newsId);
//                        }
//                        updateNextUrlInDB(news.getNext(), newsId);//更新下一页的url
//                        addDataToDB(articles);//添加数据
//                        getView().addData(articles);
//                        getView().haveLoadMore(true);
//                    } catch (Exception e) {
//                        getView().showError(new Exception("数据解析错误"), true);
//                        getView().haveLoadMore(false);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<News> call, Throwable t) {
//                if (isViewAttached()) {
//                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), true);
//                    getView().haveLoadMore(false);
//                }
//            }
//        });
    }

    /**
     * 从数据库加载数据，如果没有数据就网络加载
     *
     * @param newsId
     */
    private void loadDataFromDB(int newsId) {
        List<Article> list = queryDataFromDB(newsId);
        if (list.size() <= 0) {
            //第一次加载新闻
            loadDataFromNet(newsId, false);
        } else {
            if (isViewAttached()) {
                getView().setData(list);
                getView().showContent();
            }
        }
    }


    private void addArticleToDB(Article article) {
        dao.insert(article);
    }

    private void updateNextUrlInDB(String nextPageUrl, int newsId) {
//        DaoSession daoSession = App.getApp().getDaoSession();
        NextUrlDao dao = daoSession.getNextUrlDao();
        List<NextUrl> nextUrls = dao.queryBuilder().where(NextUrlDao.Properties.NewsId.eq(newsId)).list();
        if (nextUrls.size() == 0) {
            NextUrl url = new NextUrl((long) newsId, nextPageUrl);
            dao.insert(url);
        } else {
            NextUrl url = nextUrls.get(0);
            url.setNextUrl(nextPageUrl);
            dao.update(url);
        }

    }

    public void updateArticleIsViewed(Article article) {
        DaoSession daoSession = App.getApp().getDaoSession();
        ArticleDao dao = daoSession.getArticleDao();
        List<Article> mArticles = dao.queryBuilder().where(ArticleDao.Properties.Id.eq(article.getId())).list();
        Article m = mArticles.get(0);
        m.setIsViewed(article.getIsViewed());
        dao.update(m);
    }


    private String getNextUrlFromDB(int newsId) {
        DaoSession daoSession = App.getApp().getDaoSession();
        NextUrlDao dao = daoSession.getNextUrlDao();
        List<NextUrl> nextUrls = dao.queryBuilder().where(NextUrlDao.Properties.NewsId.eq(newsId)).list();
        if (nextUrls.size() == 0) {
            if (isViewAttached()) {
                getView().showError(new Exception("数据从数据库加载错误"), true);
            }
        }
        return nextUrls.get(0).getNextUrl();
    }

    private List<Article> queryDataFromDB(int newsId) {
        DaoSession daoSession = App.getApp().getDaoSession();
        ArticleDao dao = daoSession.getArticleDao();
        //        WhereCondition wc = new WhereCondition.PropertyCondition();
        Query<Article> query = dao.queryBuilder().where(ArticleDao.Properties.NewsId.eq(newsId)).orderAsc(ArticleDao
                .Properties._id).build();
        return query.list();
    }

    private void deleteAllDataInDB(int newsId) {
        DaoSession daoSession = App.getApp().getDaoSession();
        ArticleDao dao = daoSession.getArticleDao();
        DeleteQuery<Article> query = dao.queryBuilder().where(ArticleDao.Properties.NewsId.eq(newsId)).buildDelete();
        query.executeDeleteWithoutDetachingEntities();
    }
}

