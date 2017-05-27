package com.msisuzney.minisoccer.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.msisuzney.minisoccer.DQDApi.model.PlayerDetailBase;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.PlayerDetailPagerAdapter;
import com.msisuzney.minisoccer.presenter.PlayerDetailPresenter;
import com.msisuzney.minisoccer.utils.DateTransfer;
import com.msisuzney.minisoccer.view.PlayerDetailView;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerDetailActivity extends MvpActivity<PlayerDetailView, PlayerDetailPresenter> implements
        PlayerDetailView {

    public static final String PLAYER_ID = "player_id";

    @BindView(R.id.player_detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.player_detail_image)
    CircularImageView player_detail_image;
    @BindView(R.id.player_detail_subtitle)
    TextView player_detail_subtitle;
    @BindView(R.id.player_detail_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.player_detail_viewpager)
    ViewPager viewPager;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        String id = "";
        if (getIntent() != null) {
            id = getIntent().getStringExtra(PLAYER_ID);
        }
        PlayerDetailPagerAdapter adapter = new PlayerDetailPagerAdapter(getSupportFragmentManager(), this, id);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        loadData(getIntent());
    }

    @NonNull
    @Override
    public PlayerDetailPresenter createPresenter() {
        return new PlayerDetailPresenter();
    }

    private void loadData(Intent intent) {
        presenter.loadData(intent);
    }

    @Override
    public void setData(PlayerDetailBase player) {
        toolbar.setTitle(player.getName());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Glide.with(this).load(player.getPerson_img()).into(player_detail_image);
        StringBuilder sb = new StringBuilder(player.getTeam_name());
        try {
            sb.append("/");
            sb.append(player.getPosition());
            sb.append("/");
            sb.append(player.getShirtnumber());
            sb.append("号/");
            sb.append(player.getNationality());
            sb.append("/");
            sb.append(player.getHeight());
            sb.append("cm/");
            sb.append(player.getWeight());
            sb.append("kg/");
            sb.append(DateTransfer.getAgeByBirthday(player.getDate_of_birth()));
            sb.append("岁");
        } catch (ParseException e) {
            Toast.makeText(this, "数据解析错误", Toast.LENGTH_SHORT).show();
        }
        player_detail_subtitle.setText(sb.toString());
    }

    @Override
    public void setTitleIfNoData() {
        toolbar.setTitle("null");
        toolbar.setSubtitle("null");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
