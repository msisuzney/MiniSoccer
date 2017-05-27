package com.msisuzney.minisoccer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.msisuzney.minisoccer.BlankFragment;
import com.msisuzney.minisoccer.view.fragments.NewsFragment;
import com.msisuzney.minisoccer.view.fragments.PersonRankingFragment;
import com.msisuzney.minisoccer.view.fragments.ScheduleFragment;
import com.msisuzney.minisoccer.view.fragments.TeamRankingsFragment;

import java.util.HashMap;

/**
 * Created by chenxin.
 * Date: 2017/5/7.
 * Time: 22:40.
 */

public class MyTabFragmentAdapter extends FragmentStatePagerAdapter {

    private String[] tabs;
    private HashMap<Integer, Fragment> fragments;

    public MyTabFragmentAdapter(FragmentManager fm, String[] tabsName) {
        super(fm);
        this.tabs = tabsName;
        fragments = new HashMap<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if (position == 0) {
            fragment = new NewsFragment();
        } else if (position == 1) {
            fragment = new TeamRankingsFragment();
        } else if (position == 2) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_GOAL);
        } else if (position == 3) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_ASSIST);
        } else if (position == 4) {
            fragment = new ScheduleFragment();
        } else if (position == 5) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_SUCCESS_PASS);
        } else if (position == 6) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_KEY_PASSES);
        } else if (position == 7) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_INTERCEPTIONS);
        } else if (position == 8) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_TACKLES);
        } else if (position == 9) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_CLEARANCES);
        } else if (position == 10) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_FOULS);
        } else if (position == 11) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_FOULED);
        } else if (position == 12) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_SAVES);
        } else if (position == 13) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_YELLOW_CARDS);
        } else if (position == 14) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_RED_CRADS);
        } else if (position == 15) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_APPEARANCES);
        } else if (position == 16) {
            fragment = PersonRankingFragment.newInstance(PersonRankingFragment.TYPE_TIME_PLAYED);
        } else {
            fragment = BlankFragment.newInstance(position);
        }
        fragments.put(position, fragment);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
}

    @Override
    public int getCount() {
        return tabs.length;
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }

}
