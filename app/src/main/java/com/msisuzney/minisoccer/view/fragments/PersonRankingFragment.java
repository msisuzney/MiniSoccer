package com.msisuzney.minisoccer.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.msisuzney.minisoccer.DQDApi.model.PersonRanking;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.PersonRankingRVAdapter;
import com.msisuzney.minisoccer.presenter.PersonRankingPresenter;
import com.msisuzney.minisoccer.view.PersonRankingView;
import com.msisuzney.minisoccer.view.activities.PlayerDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PersonRankingFragment extends MvpLceFragment<SwipeRefreshLayout, PersonRanking, PersonRankingView,
        PersonRankingPresenter> implements PersonRankingView, SwipeRefreshLayout.OnRefreshListener,
        OnBottomBarPositionChangeListener {

    public static final String TYPE_ASSIST = "assists";
    public static final String TYPE_GOAL = "goals";
    public static final String TYPE_KEY_PASSES = "key_passes";
    public static final String TYPE_SHOT = "shots";
    public static final String TYPE_SHOT_ON_TARGET = "shots_on_target";
    public static final String TYPE_OFFSIDES = "offsides";
    public static final String TYPE_PASSES = "passes";
    public static final String TYPE_SUCCESS_PASS = "success_passes";
    public static final String TYPE_INTERCEPTIONS = "interceptions";
    public static final String TYPE_TACKLES = "tackles";
    public static final String TYPE_CLEARANCES = "clearances";
    public static final String TYPE_FOULS = "fouls";
    public static final String TYPE_FOULED = "fouled";
    public static final String TYPE_RED_CRADS = "red_cards";
    public static final String TYPE_YELLOW_CARDS = "yellow_cards";
    public static final String TYPE_SAVES = "saves";
    public static final String TYPE_APPEARANCES = "appearances";
    public static final String TYPE_TIME_PLAYED = "time_played";
    private static final String TYPE = "TYPE";
    @BindView(R.id.personRankingRV)
    RecyclerView recyclerView;
    PersonRankingRVAdapter adapter;
    int bottomBarPosition;
    LinearLayoutManager linearLayoutManager;
    private String type;
    private int[] ids;
//    造成了内存泄漏。。。MainActivity
//    private static RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();

    public static PersonRankingFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        PersonRankingFragment fragment = new PersonRankingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(TYPE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_ranking, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        ids = getResources().getIntArray(R.array.league_rank_id);
        contentView.setOnRefreshListener(this);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(type, bottomBarPosition, false);
            }
        });
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PersonRankingRVAdapter(getActivity());
        adapter.setListener(new PersonRankingRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String playerId) {
                Intent intent = new Intent(getActivity(), PlayerDetailActivity.class);
                intent.putExtra(PlayerDetailActivity.PLAYER_ID, playerId);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick() {
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
//        recyclerView.setRecycledViewPool(pool);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public PersonRankingPresenter createPresenter() {
        return new PersonRankingPresenter();
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
        contentView.setRefreshing(pullToRefresh);
    }

    @Override
    public void setData(PersonRanking data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

    /**
     * @param type          请求数据的类型
     * @param position      bottomBar的位置
     * @param pullToRefresh 是否是下拉刷新
     */
    private void loadData(String type, int position, boolean pullToRefresh) {
        showLoading(pullToRefresh);
        presenter.loadData(type, ids[position], pullToRefresh);
    }

    @Override
    public void onRefresh() {
        loadData(type, bottomBarPosition, true);
    }

    @Override
    public void onBottomBarPositionChange(int position) {
        bottomBarPosition = position;
        loadData(type, position, false);
        recyclerView.smoothScrollToPosition(0);
    }
}
