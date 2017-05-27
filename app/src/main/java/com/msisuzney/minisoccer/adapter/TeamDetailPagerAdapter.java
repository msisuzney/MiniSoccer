package com.msisuzney.minisoccer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.msisuzney.minisoccer.BlankFragment;
import com.msisuzney.minisoccer.view.fragments.TeamInfoFragment;
import com.msisuzney.minisoccer.view.fragments.TeamMembersFragment;
import com.msisuzney.minisoccer.view.fragments.TeamScheduleFragment;

/**
 * Created by chenxin.
 * Date: 2017/5/10.
 * Time: 14:56.
 */

public class TeamDetailPagerAdapter extends FragmentStatePagerAdapter {

    private String tabs[];
    private String teamId;

    public TeamDetailPagerAdapter(FragmentManager fm, String[] tabs, String teamId) {
        super(fm);
        this.tabs = tabs;
        this.teamId = teamId;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return TeamScheduleFragment.newInstance(teamId);
        } else if (position == 1) {
            return TeamMembersFragment.newInstance(teamId);
        } else
            return TeamInfoFragment.newInstance(teamId);
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

}
