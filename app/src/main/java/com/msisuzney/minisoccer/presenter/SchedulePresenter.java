package com.msisuzney.minisoccer.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.App;
import com.msisuzney.minisoccer.DQDApi.APIService;
import com.msisuzney.minisoccer.DQDApi.model.Schedule.Match;
import com.msisuzney.minisoccer.DQDApi.model.Schedule.Round;
import com.msisuzney.minisoccer.DQDApi.model.Schedule.Schedule;
import com.msisuzney.minisoccer.utils.DateTransfer;
import com.msisuzney.minisoccer.utils.MyRetrofit;
import com.msisuzney.minisoccer.view.ScheduleView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenxin.
 * Date: 2017/5/9.
 * Time: 22:19.
 */

public class SchedulePresenter extends MvpBasePresenter<ScheduleView> {
    List<Round> rounds;
    private int currentRound = -1;

    public void loadData(int id, final boolean pullToRefresh) {
        App.getApp().getMyRetrofit().getApiService().getLeagueSchedule(String.valueOf(id)).enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                if (isViewAttached()) {
                    try {
                        Schedule schedule = response.body();
                        rounds = schedule.getContent().getRounds();
                        //得到当前轮次
                        for (int i = 0; i < rounds.size(); i++) {
                            if (rounds.get(i).getCurrent() != null) {
                                currentRound = i;
                            }
                        }
                        List<Match> matches = schedule.getContent().getMatches();
                        //将开赛时间转换成北京时间
                        for (int i = 0; i < matches.size(); i++) {
                            matches.get(i).setStart_play(DateTransfer.transfer(matches.get(i).getStart_play()));
                        }
                        getView().setData2(schedule, currentRound + 1);
                        getView().showContent();
                    } catch (Exception e) {
                        if (isViewAttached())
                            getView().showError(new Exception("数据解析错误"), pullToRefresh);
                    }
                }
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {
                if (isViewAttached()) {
                    if (isViewAttached()) {
                        getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), pullToRefresh);
                    }
                }
            }
        });
    }

    /**
     * 加载其他轮次
     *
     * @param isPrevious 是否是加载前一轮，否则后一轮
     */
    public void loadData2(final boolean isPrevious) {
        String url = "";
        if (isPrevious) {//加载上一轮
            if (--currentRound < 0) {
                currentRound++;//保证是第一轮，防止用户多次点击
                if (isViewAttached()) {
                    getView().showToast("已是第一轮");
                    getView().showContent();
                }
                return;
            } else {
                url = rounds.get(currentRound).getUrl();
            }
        } else {//加载下一轮
            if (++currentRound == rounds.size()) {
                currentRound--;//保证是最后一轮，防止用户多次点击
                if (isViewAttached()) {
                    getView().showToast("已是最后一轮");
                    getView().showContent();
                }
                return;
            } else {
                url = rounds.get(currentRound).getUrl();
            }
        }
        App.getApp().getMyRetrofit().getApiService().getLeagueSchedule2(url).enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                if (isViewAttached()) {
                    try {
                        Schedule schedule = response.body();

                        //将开赛时间转换成北京时间
                        List<Match> matches = schedule.getContent().getMatches();
                        for (int i = 0; i < matches.size(); i++) {
                            matches.get(i).setStart_play(DateTransfer.transfer(matches.get(i).getStart_play()));
                        }
                        getView().setData2(schedule, currentRound + 1);
                        getView().showContent();
                    } catch (Exception e) {
                        getView().showError(new Exception("数据解析错误"), true);
                    }
                }
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {
                if (isViewAttached()) {
                    //网络出错时，请求失败还原为当前轮次
                    if (isPrevious) {
                        currentRound++;
                    } else {
                        currentRound--;
                    }
                    getView().showError(new Exception("网络请求错误\n请检查网络连接情况后\n点击重新加载"), true);
                }
            }
        });
    }
}

