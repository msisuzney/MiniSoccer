package com.msisuzney.minisoccer.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.DQDApi.APIService;
import com.msisuzney.minisoccer.DQDApi.model.PersonRanking;
import com.msisuzney.minisoccer.utils.MyRetrofit;
import com.msisuzney.minisoccer.view.PersonRankingView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/9.
 * Time: 15:33.
 */

public class PersonRankingPresenter extends MvpBasePresenter<PersonRankingView> {
    private APIService api = MyRetrofit.getMyRetrofit().getApiService();
    public void loadData(String type,int leagueId, final boolean pullToRefresh) {
        String id = String.valueOf(leagueId);
        api.getPersonRanking(id,type).enqueue(new Callback<PersonRanking>() {
            @Override
            public void onResponse(Call<PersonRanking> call, Response<PersonRanking> response) {
                if (isViewAttached()) {
                    try {
                        PersonRanking personRanking = response.body();
                        getView().setData(personRanking);
                        getView().showContent();
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), pullToRefresh);
                    }
                }
            }

            @Override
            public void onFailure(Call<PersonRanking> call, Throwable t) {
                if (isViewAttached()) {
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);
                }
            }
        });
    }
}
