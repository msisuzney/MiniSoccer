package com.msisuzney.minisoccer.presenter;

import android.os.Bundle;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.App;
import com.msisuzney.minisoccer.DQDApi.model.TeamDetail;
import com.msisuzney.minisoccer.utils.MyRetrofit;
import com.msisuzney.minisoccer.view.TeamInfoView;
import com.msisuzney.minisoccer.view.fragments.TeamInfoFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/11.
 * Time: 10:51.
 */

public class TeamInfoPresenter extends MvpBasePresenter<TeamInfoView> {


    public void loadData(Bundle b, final boolean pullToRefresh) {
        String id;
        if (isViewAttached()) {
            if (b == null || (id = b.getString(TeamInfoFragment.TEAM_ID)) == null) {
                getView().showError(new Exception("请求参数错误"), pullToRefresh);
            } else {
                App.getApp().getMyRetrofit().getApiService().getTeamDetail(id).enqueue(new Callback<TeamDetail>() {
                    @Override
                    public void onResponse(Call<TeamDetail> call, Response<TeamDetail> response) {
                        try {
                            getView().setData(response.body());
                            getView().showContent();
                        } catch (Exception e) {
                            getView().showError(new Exception("数据解析错误"), pullToRefresh);
                        }
                    }

                    @Override
                    public void onFailure(Call<TeamDetail> call, Throwable t) {
                        if (isViewAttached()) {
                            getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);
                        }
                    }
                });
            }
        }
    }
}
