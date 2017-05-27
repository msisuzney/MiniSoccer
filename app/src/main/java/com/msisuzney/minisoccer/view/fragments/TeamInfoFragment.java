package com.msisuzney.minisoccer.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.msisuzney.minisoccer.DQDApi.model.TeamDetail;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.TeamTrophyRVAdapter;
import com.msisuzney.minisoccer.presenter.TeamInfoPresenter;
import com.msisuzney.minisoccer.utils.DividerItemDecoration;
import com.msisuzney.minisoccer.view.TeamInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamInfoFragment extends MvpLceFragment<SwipeRefreshLayout, TeamDetail, TeamInfoView, TeamInfoPresenter>
        implements TeamInfoView {

    public static final String TEAM_ID = "team_id";
    @BindView(R.id.team_detail_name)
    TextView team_detail_name;
    @BindView(R.id.team_detail_en_name)
    TextView team_detail_en_name;
    @BindView(R.id.team_detail_founded)
    TextView team_detail_founded;
    @BindView(R.id.team_detail_country)
    TextView team_detail_country;
    @BindView(R.id.team_detail_city)
    TextView team_detail_city;
    @BindView(R.id.team_detail_venue_name)
    TextView team_detail_venue_name;
    @BindView(R.id.team_detail_venue_capacity)
    TextView team_detail_venue_capacity;
    @BindView(R.id.team_detail_info_rv)
    RecyclerView rv;
    private TeamTrophyRVAdapter adapter;
    private Bundle b;


    public TeamInfoFragment() {
        // Required empty public constructor
    }

    public static TeamInfoFragment newInstance(String teamId) {
        Bundle args = new Bundle();
        args.putString(TEAM_ID, teamId);
        TeamInfoFragment fragment = new TeamInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        rv.setNestedScrollingEnabled(false);
        adapter = new TeamTrophyRVAdapter(getActivity());
        rv.setAdapter(adapter);
        b = getArguments();
        loadData(b, false);
    }


    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void showContent() {
        super.showContent();
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
    }

    @Override
    public TeamInfoPresenter createPresenter() {
        return new TeamInfoPresenter();
    }

    @Override
    public void setData(TeamDetail data) {
        TeamDetail.Base_info base_info = data.getBase_info();
        team_detail_city.setText(base_info.getCity());
        team_detail_country.setText(base_info.getCountry());
        team_detail_en_name.setText(base_info.getTeam_en_name());
        team_detail_founded.setText(base_info.getFounded());
        team_detail_venue_capacity.setText(base_info.getVenue_capacity());
        team_detail_venue_name.setText(base_info.getVenue_name());
        team_detail_name.setText(base_info.getTeam_name());
        adapter.setData(data.getTrophyInfo());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

    public void loadData(Bundle b, boolean pullToRefresh) {
        presenter.loadData(b, pullToRefresh);
    }
}
