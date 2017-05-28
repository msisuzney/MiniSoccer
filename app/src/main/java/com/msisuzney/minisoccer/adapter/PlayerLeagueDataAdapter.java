package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.msisuzney.minisoccer.DQDApi.model.PlayerLeagueData;
import com.msisuzney.minisoccer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/11.
 * Time: 22:25.
 */

public class PlayerLeagueDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mCon;
    private List<PlayerLeagueData> data;

    public PlayerLeagueDataAdapter(Context mCon) {
        this.mCon = mCon;
        data = new ArrayList<>();
    }

    public void setData(List<PlayerLeagueData> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (size() == 0) {
            return new NoDataViewHolder(LayoutInflater.from(mCon).inflate(R.layout.no_data_view, parent, false));
        }
        return new MyViewHolder(LayoutInflater.from(mCon).inflate(R.layout.item_player_league_data, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (size() == 0) { //size() == 0 的时候有一个itemView，显示没有数据
            NoDataViewHolder viewHolder = (NoDataViewHolder) holder;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            viewHolder.itemView.setLayoutParams(params);
            viewHolder.noData.setText("没有联赛数据");
        } else {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.player_league_data_season.setText(data.get(position).getSeason_name());
            viewHolder.player_league_data_league.setText(data.get(position).getCompetition_name());
            viewHolder.player_league_data_team.setText(data.get(position).getTeam_name());
            viewHolder.player_league_data_appearances.setText(data.get(position).getAppearances());
            viewHolder.player_league_data_lineups.setText(data.get(position).getLineups());
            viewHolder.player_league_data_sub.setText(data.get(position).getSubstitute_in());
            viewHolder.player_league_data_goals.setText(data.get(position).getGoals());
            viewHolder.player_league_data_assists.setText(data.get(position).getAssists());
            viewHolder.player_league_data_yellows.setText(data.get(position).getYellow_cards());
            viewHolder.player_league_data_reds.setText(data.get(position).getRed_cards());

        }
    }


    @Override
    public int getItemCount() {
        return size() == 0 ? 1 : data.size();
    }

    private int size() {
        return data == null ? 0 : data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView player_league_data_season;
        TextView player_league_data_league;
        TextView player_league_data_team;
        TextView player_league_data_appearances;
        TextView player_league_data_lineups;
        TextView player_league_data_sub;
        TextView player_league_data_goals;
        TextView player_league_data_assists;
        TextView player_league_data_yellows;
        TextView player_league_data_reds;

        public MyViewHolder(View itemView) {
            super(itemView);
            player_league_data_season = (TextView) itemView.findViewById(R.id.player_league_data_season);
            player_league_data_league = (TextView) itemView.findViewById(R.id.player_league_data_league);
            player_league_data_team = (TextView) itemView.findViewById(R.id.player_league_data_team);
            player_league_data_appearances = (TextView) itemView.findViewById(R.id.player_league_data_appearances);
            player_league_data_lineups = (TextView) itemView.findViewById(R.id.player_league_data_lineups);
            player_league_data_sub = (TextView) itemView.findViewById(R.id.player_league_data_sub);
            player_league_data_goals = (TextView) itemView.findViewById(R.id.player_league_data_goals);
            player_league_data_assists = (TextView) itemView.findViewById(R.id.player_league_data_assists);
            player_league_data_yellows = (TextView) itemView.findViewById(R.id.player_league_data_yellows);
            player_league_data_reds = (TextView) itemView.findViewById(R.id.player_league_data_reds);
        }
    }

    static class NoDataViewHolder extends RecyclerView.ViewHolder {
        TextView noData;

        public NoDataViewHolder(View itemView) {
            super(itemView);
            noData = (TextView) itemView.findViewById(R.id.no_data);
        }
    }

}
