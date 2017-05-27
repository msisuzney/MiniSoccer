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

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.msisuzney.minisoccer.DQDApi.model.TeamSchedule;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.TeamScheduleRVAdapter;
import com.msisuzney.minisoccer.presenter.TeamSchedulePresenter;
import com.msisuzney.minisoccer.utils.DividerItemDecoration;
import com.msisuzney.minisoccer.view.TeamScheduleView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamScheduleFragment extends MvpLceFragment<SwipeRefreshLayout, TeamSchedule, TeamScheduleView,
        TeamSchedulePresenter> implements SwipeRefreshLayout.OnRefreshListener, TeamScheduleView {


    public static final String TEAM_ID = "id";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Bundle b;
    TeamScheduleRVAdapter adapter;

    public TeamScheduleFragment() {
        // Required empty public constructor
    }

    public static TeamScheduleFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(TEAM_ID, id);
        TeamScheduleFragment fragment = new TeamScheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        contentView.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        adapter = new TeamScheduleRVAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(b, false);
            }
        });
        b = getArguments();
        loadData(b, false);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public TeamSchedulePresenter createPresenter() {
        return new TeamSchedulePresenter();
    }

    @Override
    public void setData(TeamSchedule data) {
        contentView.setRefreshing(false);
        adapter.setData(data);
        adapter.notifyDataSetChanged();
        int currentMatch = -1;
        for (int i = 0; i < data.getList().size(); i++) {
            if (data.getList().get(i).getStatus().equals("Played")) {
                currentMatch++;
            }
        }
        recyclerView.scrollToPosition(currentMatch);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        contentView.setRefreshing(pullToRefresh);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

    private void loadData(Bundle b, boolean pullToRefresh) {
        showLoading(pullToRefresh);
        presenter.loadData(b, pullToRefresh);
    }

    @Override
    public void onRefresh() {
        presenter.loadData(b, true);
    }
}
