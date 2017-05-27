package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.msisuzney.minisoccer.BlankFragment;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.view.fragments.PlayerAbilityFragment;
import com.msisuzney.minisoccer.view.fragments.PlayerBasicInfoFragment;
import com.msisuzney.minisoccer.view.fragments.PlayerLeagueDataFragment;

/**
 * Created by chenxin.
 * Date: 2017/5/11.
 * Time: 21:15.
 */

public class PlayerDetailPagerAdapter extends FragmentStatePagerAdapter {

    String[] tabs;
    String id;
    public PlayerDetailPagerAdapter(FragmentManager fm, Context context,String id) {
        super(fm);
        tabs = context.getResources().getStringArray(R.array.player_detail_tabs);
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return PlayerLeagueDataFragment.newInstance(id);
        }else if(position == 1){
            return PlayerAbilityFragment.newInstance(id);
        }else
        return PlayerBasicInfoFragment.newInstance(id);
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
