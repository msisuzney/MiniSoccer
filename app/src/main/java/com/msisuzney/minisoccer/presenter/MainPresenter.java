package com.msisuzney.minisoccer.presenter;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.adapter.MyTabFragmentAdapter;
import com.msisuzney.minisoccer.view.MainView;
import com.msisuzney.minisoccer.view.fragments.OnBottomBarPositionChangeListener;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Created by chenxin.
 * Date: 2017/5/7.
 * Time: 21:18.
 */

/**
 * 设置viewPager与bottomBar,每个viewPager中的fragment都实现了OnBottomBarPositionChangeListener接口
 * ，不管是通过点击bottomBar切换联赛还是滑动viewPager切换内容最后都通过回调OnBottomBarPositionChangeListener接口通知
 * fragment.
 * <br/>
 * bug，在按home键回到桌面后，立即回到应用点击bottomBar切换联赛正常，但过很久回来点击点击bottomBar没用
 */
public class MainPresenter extends MvpBasePresenter<MainView> {

    private ViewPager viewPager;
    private MyTabFragmentAdapter adapter;
    private FragmentManager fragmentManager;
    private BottomBar bottomBar;
    private int mPagePosition;
    private int mBottomBarPosition;

    @Override
    public void attachView(MainView view) {
        super.attachView(view);
    }

    public void init() {
        if (isViewAttached()) {
            viewPager = getView().getViewPager();
            fragmentManager = getView().getFragmentManagerInMvpView();
            bottomBar = getView().getBottomBar();
            bottomBar.setOnTabSelectListener(new MyOnTabSelectListener());
            adapter = new MyTabFragmentAdapter(fragmentManager, getView().getTabsName());
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new FragmentPagerChangeListener());
        }
    }


    private class FragmentPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            mPagePosition = position;
            //page一旦选定后根据当前的bottomBar的位置加载数据
            Fragment f;
            if ((f = adapter.getFragment(mPagePosition)) instanceof OnBottomBarPositionChangeListener) {
                Log.d("cx", "instanceof OnBottomBarPositionChangeListener true");
                OnBottomBarPositionChangeListener listener = (OnBottomBarPositionChangeListener) f;
                listener.onBottomBarPositionChange(mBottomBarPosition);
            }

    }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    private class MyOnTabSelectListener implements OnTabSelectListener {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            if (adapter != null) {
                mBottomBarPosition = getView().getBottomBarPosition(tabId);
                Fragment f;
                if ((f = adapter.getFragment(mPagePosition)) instanceof OnBottomBarPositionChangeListener) {
                    OnBottomBarPositionChangeListener listener = (OnBottomBarPositionChangeListener) f;
                    listener.onBottomBarPositionChange(mBottomBarPosition);
                }
            }
        }


    }
}
