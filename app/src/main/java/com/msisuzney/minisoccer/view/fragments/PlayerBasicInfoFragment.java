package com.msisuzney.minisoccer.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.msisuzney.minisoccer.DQDApi.model.PlayerDetail;
import com.msisuzney.minisoccer.DQDApi.model.TeamDetail;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.PlayerInjuryAdapter;
import com.msisuzney.minisoccer.adapter.PlayerTransferAdapter;
import com.msisuzney.minisoccer.adapter.PlayerTrophyItemRVAdapter;
import com.msisuzney.minisoccer.adapter.PlayerTrophyRVAdapter;
import com.msisuzney.minisoccer.adapter.TeamTrophyRVAdapter;
import com.msisuzney.minisoccer.presenter.PlayerBasicInfoPresenter;
import com.msisuzney.minisoccer.utils.DividerItemDecoration;
import com.msisuzney.minisoccer.view.PlayerBasicInfoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerBasicInfoFragment extends MvpLceFragment<LinearLayout, PlayerDetail, PlayerBasicInfoView,
        PlayerBasicInfoPresenter> implements PlayerBasicInfoView {

    public static final String ID = "id";

    @BindView(R.id.trophy_rv)
    RecyclerView rv_trophy;

    @BindView(R.id.transfer_rv)
    RecyclerView rv_transfer;

    @BindView(R.id.injury_rv)
    RecyclerView rv_injury;

    PlayerTransferAdapter transferadapter;
    PlayerInjuryAdapter injuryAdapter;
    PlayerTrophyRVAdapter trophyAdapter;

    public PlayerBasicInfoFragment() {
        // Required empty public constructor
    }

    public static PlayerBasicInfoFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(ID, id);
        PlayerBasicInfoFragment fragment = new PlayerBasicInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player_basic_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        rv_injury.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_transfer.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_trophy.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_injury.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        rv_transfer.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        rv_trophy.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        rv_injury.setNestedScrollingEnabled(false);
        rv_transfer.setNestedScrollingEnabled(false);
        rv_trophy.setNestedScrollingEnabled(false);
        transferadapter = new PlayerTransferAdapter(getActivity());
        injuryAdapter = new PlayerInjuryAdapter(getActivity());
        trophyAdapter = new PlayerTrophyRVAdapter(getActivity());
        loadData(getArguments());
    }

    private void loadData(Bundle arguments) {
        presenter.loadData(arguments);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public PlayerBasicInfoPresenter createPresenter() {
        return new PlayerBasicInfoPresenter();
    }

    @Override
    public void setData(PlayerDetail data) {

        if(rv_trophy.getAdapter() == null){
            trophyAdapter.setData(data.getTrophy_info());
            rv_trophy.setAdapter(trophyAdapter);
        }else {
            trophyAdapter.setData(data.getTrophy_info());
            trophyAdapter.notifyDataSetChanged();
        }

        if (rv_transfer.getAdapter() == null) {
            transferadapter.setInfo(data.getTransfer_info());
            rv_transfer.setAdapter(transferadapter);
        } else {
            transferadapter.setInfo(data.getTransfer_info());
            transferadapter.notifyDataSetChanged();
        }

        if (rv_injury.getAdapter() == null){
            injuryAdapter.setData(data.getInjury_info());
            rv_injury.setAdapter(injuryAdapter);
        }else {
            injuryAdapter.setData(data.getInjury_info());
            injuryAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

}
