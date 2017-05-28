package com.msisuzney.minisoccer.presenter;

import android.content.Intent;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.App;
import com.msisuzney.minisoccer.DQDApi.model.coach.Coach;
import com.msisuzney.minisoccer.utils.MyRetrofit;
import com.msisuzney.minisoccer.view.CoachDetailView;
import com.msisuzney.minisoccer.view.activities.CoachDetailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/12.
 * Time: 15:18.
 */

public class CoachDetailPresenter extends MvpBasePresenter<CoachDetailView> {
    public void ladData(Intent intent) {
        String id = "";
        if (intent == null || (id = intent.getStringExtra(CoachDetailActivity.ID)) == null || id.equals("")) {
            getView().showError(new Exception("请求参数错误"), false);
        } else {
            App.getApp().getMyRetrofit().getApiService().getCoachDetail(id).enqueue(new Callback<Coach>() {
                @Override
                public void onResponse(Call<Coach> call, Response<Coach> response) {
                    try {
                        if (isViewAttached()) {
                            getView().setData(response.body());
                            getView().showContent();
                        }
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), false);
                    }
                }

                @Override
                public void onFailure(Call<Coach> call, Throwable t) {
                    if (isViewAttached()) {
                        getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), false);
                    }
                }
            });
        }
    }
}
