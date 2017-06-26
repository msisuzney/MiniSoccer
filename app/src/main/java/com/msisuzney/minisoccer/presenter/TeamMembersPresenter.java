package com.msisuzney.minisoccer.presenter;

import android.os.Bundle;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.DQDApi.model.TeamMembers;
import com.msisuzney.minisoccer.DQDApi.MyRetrofit;
import com.msisuzney.minisoccer.view.TeamMembersView;
import com.msisuzney.minisoccer.view.fragments.TeamMembersFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/10.
 * Time: 21:31.
 */

public class TeamMembersPresenter extends MvpBasePresenter<TeamMembersView> {
    MyRetrofit myRetrofit = MyRetrofit.getMyRetrofit();
    public void loadData(Bundle bundle, final boolean pullToRefresh){
        if (isViewAttached()) {
            String teamId;
            if (bundle == null || (teamId = bundle.getString(TeamMembersFragment.TEAM_ID)) == null) {
                getView().showError(new Exception("请求参数错误"),pullToRefresh);
            }else {
                MyRetrofit.getMyRetrofit().getApiService().getTeamMembers(teamId).enqueue(new Callback<TeamMembers>() {
                    @Override
                    public void onResponse(Call<TeamMembers> call, Response<TeamMembers> response) {
                        try {
                            getView().setData(response.body());
                            getView().showContent();
                        }catch (Exception e){
                            getView().showError(new Exception("数据解析错误"), pullToRefresh);
                        }
                    }

                    @Override
                    public void onFailure(Call<TeamMembers> call, Throwable t) {
                        getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);
                    }
                });
            }

        }
    }
}
