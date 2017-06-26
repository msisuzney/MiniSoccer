package com.msisuzney.minisoccer.presenter;

import android.os.Bundle;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.DQDApi.model.PlayerDetail;
import com.msisuzney.minisoccer.DQDApi.MyRetrofit;
import com.msisuzney.minisoccer.view.PlayerBasicInfoView;
import com.msisuzney.minisoccer.view.fragments.PlayerBasicInfoFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/12.
 * Time: 11:36.
 */

public class PlayerBasicInfoPresenter extends MvpBasePresenter<PlayerBasicInfoView> {
    public void loadData(Bundle bundle) {
        String id = "";
        if (bundle == null || (id = bundle.getString(PlayerBasicInfoFragment.ID)) == null || id.equals("")) {
            getView().showError(new Exception("请求数据错误"), false);
        } else {
            MyRetrofit.getMyRetrofit().getApiService().getPlayerDetail(id).enqueue(new Callback<PlayerDetail>() {
                @Override
                public void onResponse(Call<PlayerDetail> call, Response<PlayerDetail> response) {
                    try {
                        getView().setData(response.body());
                        getView().showContent();
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), false);
                    }
                }

                @Override
                public void onFailure(Call<PlayerDetail> call, Throwable t) {
                    if (isViewAttached()) {
                        getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), false);
                    }
                }
            });
        }
    }
}
