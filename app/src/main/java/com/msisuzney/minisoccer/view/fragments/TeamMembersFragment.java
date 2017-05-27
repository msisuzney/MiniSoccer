package com.msisuzney.minisoccer.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.msisuzney.minisoccer.DQDApi.model.TeamMembers;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.TeamMembersRVAdapter;
import com.msisuzney.minisoccer.presenter.TeamMembersPresenter;
import com.msisuzney.minisoccer.utils.DividerGridItemDecoration;
import com.msisuzney.minisoccer.utils.DividerItemDecoration;
import com.msisuzney.minisoccer.view.TeamMembersView;
import com.msisuzney.minisoccer.view.activities.CoachDetailActivity;
import com.msisuzney.minisoccer.view.activities.PlayerDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamMembersFragment extends MvpLceFragment<SwipeRefreshLayout,TeamMembers,TeamMembersView,TeamMembersPresenter> implements SwipeRefreshLayout.OnRefreshListener,TeamMembersView {

    public static final String TEAM_ID = "team_id";
    public TeamMembersFragment() {
        // Required empty public constructor
    }
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    TeamMembersRVAdapter adapter;

    public static TeamMembersFragment newInstance(String teamId) {

        Bundle args = new Bundle();
        args.putString(TEAM_ID,teamId);
        TeamMembersFragment fragment = new TeamMembersFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_team_members,container,false);
    }

    private Bundle b;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        contentView.setOnRefreshListener(this);
        adapter = new TeamMembersRVAdapter(getActivity());
        adapter.setOnClickListener(new MyListener());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(b,false);
            }
        });
        b = getArguments();
        loadData(b,false);
    }

    @Override
    public void onRefresh() {
        loadData(b,true);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public TeamMembersPresenter createPresenter() {
        return new TeamMembersPresenter();
    }

    @Override
    public void setData(TeamMembers data) {
        if(recyclerView.getAdapter() == null){
            adapter.setData(data);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.setData(data);
            adapter.notifyDataSetChanged();
        }
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

    private void loadData(Bundle bundle, boolean pullToRefresh){
        showLoading(pullToRefresh);
        presenter.loadData(bundle,pullToRefresh);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

    private class MyListener implements TeamMembersRVAdapter.OnClickListener {
        @Override
        public void onPlayerItemClick(String id) {
            Intent intent = new Intent(TeamMembersFragment.this.getActivity(), PlayerDetailActivity.class);
            intent.putExtra(PlayerDetailActivity.PLAYER_ID,id);
            startActivity(intent);
        }

        @Override
        public void onCoachItemClick(String id) {
            Intent intent = new Intent(TeamMembersFragment.this.getActivity(), CoachDetailActivity.class);
            intent.putExtra(CoachDetailActivity.ID,id);
            startActivity(intent);
        }
    }
}
