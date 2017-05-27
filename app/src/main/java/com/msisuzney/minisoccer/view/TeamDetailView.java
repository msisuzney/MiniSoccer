package com.msisuzney.minisoccer.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by chenxin.
 * Date: 2017/5/10.
 * Time: 13:47.
 */

public interface TeamDetailView extends MvpView{
    ViewPager getViewPager();
    ImageView getTeamLogo();
}
