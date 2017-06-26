package com.msisuzney.minisoccer.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.DQDApi.model.leagueRanking.LeagueRanking;
import com.msisuzney.minisoccer.DQDApi.MyRetrofit;
import com.msisuzney.minisoccer.view.TeamRankingsView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/9.
 * Time: 12:57.
 */
public class TeamRankingPresenter extends MvpBasePresenter<TeamRankingsView> {

    public void loadData(int id, final boolean pullToRefresh) {
        String idStr = String.valueOf(id);
        MyRetrofit.getMyRetrofit().getApiService().getLeagueRanking(idStr).enqueue(new Callback<LeagueRanking>() {
            @Override
            public void onResponse(Call<LeagueRanking> call, Response<LeagueRanking> response) {
                if (isViewAttached()) {
                    try {
                        LeagueRanking ranking = response.body();
                        getView().setData(ranking);
                        getView().showContent();
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), pullToRefresh);
                    }
                }
            }

            @Override
            public void onFailure(Call<LeagueRanking> call, Throwable t) {
                if (isViewAttached()) {
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);
                }
            }
        });
    }
}
