package com.msisuzney.minisoccer.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.DQDApi.model.search.Search;
import com.msisuzney.minisoccer.DQDApi.MyRetrofit;
import com.msisuzney.minisoccer.view.SearchView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/25.
 * Time: 22:43.
 */

public class SearchPresenter extends MvpBasePresenter<SearchView> {

    private int page = 0;

    public void loadData(String keyword, final boolean pullToRefresh) {
        page = 0;
        String pageStr = String.valueOf(++page);
        MyRetrofit.getMyRetrofit().getApiService().getSearchResult(pageStr, keyword).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (isViewAttached()) {
                    try {
                        getView().setData(response.body());
                        getView().showContent();
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), pullToRefresh);
                    }
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                if (isViewAttached()) {
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);
                }
            }
        });
    }

    public void loadMoreNews(String keyword) {
        Log.d("SearchPresenter", "loadMoreNews");
        String pageStr = String.valueOf(++page);
        MyRetrofit.getMyRetrofit().getApiService().getSearchResult(pageStr, keyword).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (isViewAttached()) {
                    try {
                        getView().addNews(response.body().getNews());
                        getView().showContent();
                        getView().haveLoadMore(true);
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), true);
                    }
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                if (isViewAttached()) {
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), true);
                    getView().haveLoadMore(false);
                }
            }
        });
    }
}
