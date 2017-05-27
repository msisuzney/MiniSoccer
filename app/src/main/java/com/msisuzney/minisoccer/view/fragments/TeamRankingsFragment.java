package com.msisuzney.minisoccer.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.msisuzney.minisoccer.DQDApi.model.leagueRanking.Datum;
import com.msisuzney.minisoccer.DQDApi.model.leagueRanking.LeagueRanking;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.TeamRankingItemRVAdapter;
import com.msisuzney.minisoccer.presenter.TeamRankingPresenter;
import com.msisuzney.minisoccer.view.TeamRankingsView;
import com.msisuzney.minisoccer.view.activities.TeamDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamRankingsFragment extends MvpLceFragment<SwipeRefreshLayout, LeagueRanking, TeamRankingsView,
        TeamRankingPresenter> implements TeamRankingsView, OnBottomBarPositionChangeListener, SwipeRefreshLayout
        .OnRefreshListener {

    @BindView(R.id.rankingRV)
    RecyclerView recyclerView;
    private int ids[];
    private TeamRankingItemRVAdapter adapter;
    private int bottomBarPosition;

    public TeamRankingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "TeamRankingsFragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team_ranking, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = new TeamRankingItemRVAdapter(getActivity());
        adapter.setListener(new TeamRankingItemRVAdapter.TeamRankingItemOnClickListener() {
            @Override
            public void onItemClick(String teamLogo, String teamName, String teamId) {
                Intent intent = new Intent(getActivity(), TeamDetailActivity.class);
                intent.putExtra(TeamDetailActivity.TEAM_NAME, teamName);
                intent.putExtra(TeamDetailActivity.TEAM_LOGO_URL, teamLogo);
                intent.putExtra(TeamDetailActivity.TEAM_Id, teamId);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick() {
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(bottomBarPosition, false);
            }
        });
        contentView.setOnRefreshListener(this);
        ids = getResources().getIntArray(R.array.league_team_rank_id);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public TeamRankingPresenter createPresenter() {
        return new TeamRankingPresenter();
    }

    @Override
    public void setData(LeagueRanking data) {
        List<Datum> mData = data.getContent().getRounds().get(0).getContent().getData();
        adapter.setData(mData);
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);

    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

    private void loadData(int bottomBarPos, boolean pullToRefresh) {
        showLoading(pullToRefresh);
        presenter.loadData(ids[bottomBarPos], pullToRefresh);
    }

    @Override
    public void onBottomBarPositionChange(int position) {
        bottomBarPosition = position;
        loadData(bottomBarPosition, false);
    }

    @Override
    public void onRefresh() {
        loadData(bottomBarPosition, true);
    }

}
