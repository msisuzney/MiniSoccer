package com.msisuzney.minisoccer.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.TeamDetailPagerAdapter;
import com.msisuzney.minisoccer.presenter.TeamDetailPresenter;
import com.msisuzney.minisoccer.view.TeamDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamDetailActivity extends MvpActivity<TeamDetailView, TeamDetailPresenter> implements TeamDetailView {

    public static final String TEAM_NAME = "name";
    public static final String TEAM_LOGO_URL = "logo";
    public static final String TEAM_Id = "id";
    @BindView(R.id.team_detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_img)
    ImageView img;
    @BindView(R.id.team_detail_viewpager)
    ViewPager viewPager;
    @BindView(R.id.team_detail_tab_layout)
    TabLayout tabLayout;
    String[] tabs;
    TeamDetailPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String name;
        if (intent == null || (name = intent.getStringExtra(TEAM_NAME)) == null) {
            toolbar.setTitle("none");
        } else {
            toolbar.setTitle(name);
        }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabs = getResources().getStringArray(R.array.team_detail_tabs);
        String url = intent.getStringExtra(TEAM_LOGO_URL);
        String teamId = intent.getStringExtra(TEAM_Id);
        if (url != null) {
            Glide.with(this).load(url).into(img);
        }
        adapter = new TeamDetailPagerAdapter(getSupportFragmentManager(), tabs, teamId);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        presenter.init();
    }

    @NonNull
    @Override
    public TeamDetailPresenter createPresenter() {
        return new TeamDetailPresenter();
    }

    @Override
    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public ImageView getTeamLogo() {
        return img;
    }
}
