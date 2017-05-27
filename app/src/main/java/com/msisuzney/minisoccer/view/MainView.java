package com.msisuzney.minisoccer.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.roughike.bottombar.BottomBar;

/**
 * Created by chenxin.
 * Date: 2017/5/7.
 * Time: 21:16.
 */

public interface MainView extends MvpView {
    ViewPager getViewPager();
    FragmentManager getFragmentManagerInMvpView();
    BottomBar getBottomBar();
    TabLayout getTabLayout();
    String[] getTabsName();
    int getBottomBarPosition(int resId);
}
