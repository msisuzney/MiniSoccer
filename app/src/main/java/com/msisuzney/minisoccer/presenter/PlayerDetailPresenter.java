package com.msisuzney.minisoccer.presenter;

import android.content.Intent;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.DQDApi.model.PlayerDetailBase;
import com.msisuzney.minisoccer.DQDApi.MyRetrofit;
import com.msisuzney.minisoccer.view.PlayerDetailView;
import com.msisuzney.minisoccer.view.activities.PlayerDetailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/11.
 * Time: 20:50.
 */

public class PlayerDetailPresenter extends MvpBasePresenter<PlayerDetailView> {
    public void loadData(Intent intent){
        String id = intent.getStringExtra(PlayerDetailActivity.PLAYER_ID);
        MyRetrofit.getMyRetrofit().getApiService().getPlayerBasicInfo(id).enqueue(new Callback<PlayerDetailBase>() {
            @Override
            public void onResponse(Call<PlayerDetailBase> call, Response<PlayerDetailBase> response) {
                if(isViewAttached())
                getView().setData(response.body());
            }

            @Override
            public void onFailure(Call<PlayerDetailBase> call, Throwable t) {
                if (isViewAttached())
                    getView().setTitleIfNoData();
            }
        });
    }
}
