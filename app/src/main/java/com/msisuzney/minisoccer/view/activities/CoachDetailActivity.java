package com.msisuzney.minisoccer.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.msisuzney.minisoccer.DQDApi.model.PlayerDetail;
import com.msisuzney.minisoccer.DQDApi.model.TeamDetail;
import com.msisuzney.minisoccer.DQDApi.model.coach.Coach;
import com.msisuzney.minisoccer.DQDApi.model.coach.TrophyItemTime;
import com.msisuzney.minisoccer.DQDApi.model.coach.Trophy_info;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.CoachCareerRVAdapter;
import com.msisuzney.minisoccer.adapter.PlayerTrophyRVAdapter;
import com.msisuzney.minisoccer.presenter.CoachDetailPresenter;
import com.msisuzney.minisoccer.utils.DateTransfer;
import com.msisuzney.minisoccer.utils.DividerItemDecoration;
import com.msisuzney.minisoccer.view.CoachDetailView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoachDetailActivity extends MvpLceActivity<LinearLayout, Coach, CoachDetailView, CoachDetailPresenter>
        implements CoachDetailView {

    public static final String ID = "id";

    @BindView(R.id.career_rv)
    RecyclerView rv_career;
    @BindView(R.id.trophy_rv)
    RecyclerView rv_trophy;
    @BindView(R.id.coach_detail_image)
    ImageView detail_image;
    @BindView(R.id.coach_detail_subtitle)
    TextView subTitle;
    @BindView(R.id.coach_detail_toolbar)
    Toolbar toolbar;

    CoachCareerRVAdapter careerAdapter;
    PlayerTrophyRVAdapter trophyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_detail);
        ButterKnife.bind(this);
        careerAdapter = new CoachCareerRVAdapter(this);
        trophyAdapter = new PlayerTrophyRVAdapter(this);
        rv_career.setLayoutManager(new LinearLayoutManager(this));
        rv_trophy.setLayoutManager(new LinearLayoutManager(this));
        rv_career.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        rv_trophy.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        rv_trophy.setNestedScrollingEnabled(false);
        rv_career.setNestedScrollingEnabled(false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(getIntent());
            }
        });
        loadData(getIntent());
    }

    private void loadData(Intent intent) {
        showLoading(false);
        presenter.ladData(intent);
    }

    @NonNull
    @Override
    public CoachDetailPresenter createPresenter() {
        return new CoachDetailPresenter();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

    @Override
    public void setData(Coach data) {

        toolbar.setTitle(data.getBase_info().getName());
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Glide.with(this).load(data.getBase_info().getPerson_img()).into(detail_image);
        careerAdapter.setInfo(data.getCareer_info());
        if (rv_career.getAdapter() == null) {
            rv_career.setAdapter(careerAdapter);
        } else {
            careerAdapter.notifyDataSetChanged();
        }
        List<PlayerDetail.Trophy_info> trophies = new ArrayList<>();
        List<Trophy_info> t = data.getTrophy_info();
        for (int i = 0; i < t.size(); i++) {
            PlayerDetail.Trophy_info trophy = new PlayerDetail.Trophy_info();
            trophy.setCompetition_name(t.get(i).getCompetition_name());
            trophy.setTimes(t.get(i).getTimes());
            trophy.setTrophy_img(t.get(i).getTrophy_img());
            List<TeamDetail.Trophy_info.Year> years = new ArrayList<>();
            List<TrophyItemTime> years2 = data.getTrophy_info().get(i).getLists();
            for (int j = 0; j < years2.size(); j++) {
                TeamDetail.Trophy_info.Year year = new TeamDetail.Trophy_info.Year();
                year.setSeason_id(years2.get(j).getSeason_id());
                year.setSeason_name(years2.get(j).getSeason_name());
                year.setTeam_name(years2.get(j).getTeam_name());
                years.add(year);
            }
            trophy.setLists(years);
            trophies.add(trophy);
        }
        trophyAdapter.setData(trophies);
        if (rv_trophy.getAdapter() == null) {
            rv_trophy.setAdapter(trophyAdapter);
        } else {
            trophyAdapter.notifyDataSetChanged();
        }

        try {
            StringBuilder sb = new StringBuilder(data.getBase_info().getEn_name());
            sb.append("/");
            sb.append(data.getBase_info().getNationality());
            sb.append("/");
            sb.append(data.getTeam_info().getTeam_name());
            sb.append("/");
            sb.append(data.getTeam_info().getType());
            sb.append("/");
            sb.append(DateTransfer.getAgeByBirthday(data.getBase_info().getDate_of_birth()));
            sb.append("岁");
            subTitle.setText(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
