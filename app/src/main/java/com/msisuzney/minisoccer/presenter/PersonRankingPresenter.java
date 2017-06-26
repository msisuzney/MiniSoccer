package com.msisuzney.minisoccer.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.DQDApi.model.PersonRanking;
import com.msisuzney.minisoccer.DQDApi.MyRetrofit;
import com.msisuzney.minisoccer.view.PersonRankingView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenxin.
 * Date: 2017/5/9.
 * Time: 15:33.
 */

public class PersonRankingPresenter extends MvpBasePresenter<PersonRankingView> {
    public void loadData(String type, int leagueId, final boolean pullToRefresh) {
        String id = String.valueOf(leagueId);
        MyRetrofit.getMyRetrofit().getApiService().getPersonRanking(id, type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PersonRanking>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);
                }
            }

            @Override
            public void onNext(PersonRanking personRanking) {
                if (isViewAttached()) {
                    try {
                        getView().setData(personRanking);
                        getView().showContent();
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), pullToRefresh);
                    }
                }
            }
        });


//                .enqueue(new Callback<PersonRanking>() {
//            @Override
//            public void onResponse(Call<PersonRanking> call, Response<PersonRanking> response) {
//                if (isViewAttached()) {
//                    try {
//                        PersonRanking personRanking = response.body();
//                        getView().setData(personRanking);
//                        getView().showContent();
//                    } catch (Exception e) {
//                        getView().showError(new Exception("数据解析错误"), pullToRefresh);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PersonRanking> call, Throwable t) {
//                if (isViewAttached()) {
//                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);
//                }
//            }
//        });
    }
}
