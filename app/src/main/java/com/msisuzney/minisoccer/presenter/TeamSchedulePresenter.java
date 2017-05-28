package com.msisuzney.minisoccer.presenter;

import android.os.Bundle;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.App;
import com.msisuzney.minisoccer.DQDApi.model.TeamSchedule;
import com.msisuzney.minisoccer.utils.DateTransfer;
import com.msisuzney.minisoccer.utils.MyRetrofit;
import com.msisuzney.minisoccer.view.TeamScheduleView;
import com.msisuzney.minisoccer.view.fragments.TeamScheduleFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/10.
 * Time: 15:40.
 */

public class TeamSchedulePresenter extends MvpBasePresenter<TeamScheduleView> {

    public void loadData(Bundle bundle, final boolean pullToRefresh) {
        String id;
        if (bundle == null || (id = bundle.getString(TeamScheduleFragment.TEAM_ID)) == null) {
            getView().showError(new Exception("请求参数错误"), pullToRefresh);
        } else {
            App.getApp().getMyRetrofit().getApiService().getTeamSchedule(id).enqueue(new Callback<TeamSchedule>() {
                @Override
                public void onResponse(Call<TeamSchedule> call, Response<TeamSchedule> response) {
                    try {
                        if (isViewAttached()) {
                            List<TeamSchedule.Item> list = response.body().getList();
                            for (int i = 0; i < list.size(); i++) {
                                list.get(i).setStart_play(DateTransfer.transfer(list.get(i).getStart_play()));
                            }
                            getView().setData(response.body());
                            getView().showContent();
                        }
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), pullToRefresh);
                    }
                }

                @Override
                public void onFailure(Call<TeamSchedule> call, Throwable t) {
                    if (isViewAttached())
                        getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);
                }
            });
        }
    }

}
