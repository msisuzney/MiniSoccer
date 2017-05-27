package com.msisuzney.minisoccer.presenter;

import android.os.Bundle;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.DQDApi.model.PlayerLeagueData;
import com.msisuzney.minisoccer.utils.MyRetrofit;
import com.msisuzney.minisoccer.view.PlayerLeagueDataView;
import com.msisuzney.minisoccer.view.fragments.PlayerLeagueDataFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/11.
 * Time: 22:15.
 */

public class PlayerLeagueDataPresenter extends MvpBasePresenter<PlayerLeagueDataView> {
    private MyRetrofit myRetrofit = MyRetrofit.getMyRetrofit();

    public void loadData(Bundle b) {
        String id;
        if (b == null || (id = b.getString(PlayerLeagueDataFragment.ID)) == null || id.equals("") ){
            getView().showError(new Exception("请求参数错误"), false);
        } else {
            if (isViewAttached()) {
                myRetrofit.getApiService().getPlayerLeagueData(id).enqueue(new Callback<List<PlayerLeagueData>>() {
                    @Override
                    public void onResponse(Call<List<PlayerLeagueData>> call, Response<List<PlayerLeagueData>>
                            response) {
                        try {
                            getView().setData(response.body());
                            getView().showContent();
                        } catch (Exception e) {
                            getView().showError(new Exception("数据解析错误"), false);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<PlayerLeagueData>> call, Throwable t) {
                        if (isViewAttached()) {
                            getView().showError(new Exception("请求参数错误"), false);
                        }
                    }
                });
            }
        }
    }
}

