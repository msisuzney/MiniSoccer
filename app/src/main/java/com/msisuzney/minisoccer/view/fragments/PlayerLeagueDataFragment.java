package com.msisuzney.minisoccer.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.msisuzney.minisoccer.DQDApi.model.PlayerLeagueData;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.PlayerLeagueDataAdapter;
import com.msisuzney.minisoccer.presenter.PlayerLeagueDataPresenter;
import com.msisuzney.minisoccer.utils.DividerItemDecoration;
import com.msisuzney.minisoccer.view.PlayerLeagueDataView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerLeagueDataFragment extends MvpLceFragment<RecyclerView, List<PlayerLeagueData>, PlayerLeagueDataView,
        PlayerLeagueDataPresenter> implements PlayerLeagueDataView {


    public static final String ID = "id";
    @BindView(R.id.contentView)
    RecyclerView rv;
    PlayerLeagueDataAdapter adapter;

    public PlayerLeagueDataFragment() {
        // Required empty public constructor
    }

    public static PlayerLeagueDataFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(ID, id);
        PlayerLeagueDataFragment fragment = new PlayerLeagueDataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_league_data, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PlayerLeagueDataAdapter(getActivity());
//        rv.setAdapter(adapter);在这里setAdapter存在两种情况，1）的确没有数据的情况，那么就复用位置为0的NoDataViewHolder
        // 2)如果有数据，那么就全部是MyViewHolder,第一个NoDataViewHolder就会被复用发生ClassCastException
        //所以不在这里setAdapter
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(getArguments());
            }
        });
        loadData(getArguments());
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public PlayerLeagueDataPresenter createPresenter() {
        return new PlayerLeagueDataPresenter();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

    private void loadData(Bundle b) {
        showLoading(false);
        presenter.loadData(b);
    }

    @Override
    public void setData(List<PlayerLeagueData> data) {
        if (rv.getAdapter() == null) {
            rv.setAdapter(adapter);
        }
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }
}
