package com.msisuzney.minisoccer.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.msisuzney.minisoccer.DQDApi.model.PlayerDetailBase;

/**
 * Created by chenxin.
 * Date: 2017/5/11.
 * Time: 20:49.
 */

public interface PlayerDetailView extends MvpView {
    void setData(PlayerDetailBase data);
    void setTitleIfNoData();
}
