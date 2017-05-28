package com.msisuzney.minisoccer.view.fragments;


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
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.msisuzney.minisoccer.DQDApi.model.Schedule.Schedule;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.ScheduleRVAdapter;
import com.msisuzney.minisoccer.presenter.SchedulePresenter;
import com.msisuzney.minisoccer.view.ScheduleView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends MvpLceFragment<SwipeRefreshLayout, Schedule, ScheduleView, SchedulePresenter>
        implements ScheduleView, OnBottomBarPositionChangeListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.current_round)
    TextView currentRoundTV;
    @BindView(R.id.previous_round)
    TextView previousRound;
    @BindView(R.id.next_round)
    TextView nextRound;
    @BindView(R.id.scheduleRV)
    RecyclerView recyclerView;
    private int[] ids;
    private int bottomBarPosition;
    private ScheduleRVAdapter adapter;


    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        contentView.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new ScheduleRVAdapter(getActivity());
        ids = getResources().getIntArray(R.array.league_team_rank_id);
        recyclerView.setAdapter(adapter);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(bottomBarPosition, false);
            }
        });
        previousRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData2(true);
            }
        });
        nextRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData2(false);
            }
        });
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public SchedulePresenter createPresenter() {
        return new SchedulePresenter();
    }

    @Override
    public void setData(Schedule data) {
    }

    @Override
    public void setData2(Schedule schedule, int currentRound) {
        adapter.setData(schedule);
        adapter.notifyDataSetChanged();
        currentRoundTV.setText(String.valueOf(currentRound));
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

    @Override
    public void onBottomBarPositionChange(int position) {
        bottomBarPosition = position;
        loadData(bottomBarPosition, false);
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

    /**
     * 第一次进入时或刷新时，加载当前联赛的赛程
     *
     * @param bottomBarPos  bottomBarPos
     * @param pullToRefresh pullToRefresh
     */
    private void loadData(int bottomBarPos, boolean pullToRefresh) {
        showLoading(pullToRefresh);
        presenter.loadData(ids[bottomBarPos], pullToRefresh);
    }

    /**
     * 用来在点击上一轮或下一轮时加载数据
     *true 上一轮，false下一轮
     * @param isPrevious 是否是点击的 上一轮按钮
     */
    private void loadData2(boolean isPrevious) {
        showLoading(true);
        presenter.loadData2(isPrevious);
    }

    @Override
    public void onRefresh() {
        loadData(bottomBarPosition, true);
    }

}
