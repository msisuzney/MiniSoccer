package com.msisuzney.minisoccer.presenter;

import android.support.v4.view.ViewPager;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.view.TeamDetailView;

/**
 * Created by chenxin.
 * Date: 2017/5/10.
 * Time: 14:08.
 */

public class TeamDetailPresenter extends MvpBasePresenter<TeamDetailView> {

    public void init() {
        if (isViewAttached()) {
            getView().getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
    }

}
