package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msisuzney.minisoccer.DQDApi.model.PlayerDetail;
import com.msisuzney.minisoccer.R;

import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/12.
 * Time: 12:38.
 */

public class PlayerInjuryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mCon;
    private List<PlayerDetail.Injury_info> info;

    public PlayerInjuryAdapter(Context mCon) {
        this.mCon = mCon;
    }

    public void setData(List<PlayerDetail.Injury_info> info) {
        this.info = info;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (size() == 0) {
            return new PlayerInjuryAdapter.NoDataViewHolder(LayoutInflater.from(mCon).inflate(R.layout.no_data_view,
                    parent, false));
        }
        return new PlayerInjuryViewHolder(LayoutInflater.from(mCon).inflate(R.layout.item_player_detail_injury,
                parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (size() == 0) {
            PlayerInjuryAdapter.NoDataViewHolder viewHolder = (PlayerInjuryAdapter.NoDataViewHolder) holder;
            viewHolder.noData.setText("没有伤病数据");
        } else {
            PlayerInjuryViewHolder viewHolder = (PlayerInjuryViewHolder) holder;
            int pos = holder.getAdapterPosition();
            viewHolder.player_detail_injury_end.setText(info.get(pos).getEnd_date());
            viewHolder.player_detail_injury_start.setText(info.get(pos).getStart_date());
            viewHolder.player_detail_injury_team_name.setText(info.get(pos).getTeam_name());
            viewHolder.player_detail_injury_type.setText(info.get(pos).getType());
        }
    }

    @Override
    public int getItemCount() {
        return size() == 0 ? 1 : info.size();
    }

    private int size() {
        return info == null ? 0 : info.size();
    }

    private static class PlayerInjuryViewHolder extends RecyclerView.ViewHolder {
        TextView player_detail_injury_end;
        TextView player_detail_injury_start;
        TextView player_detail_injury_type;
        TextView player_detail_injury_team_name;

        public PlayerInjuryViewHolder(View itemView) {
            super(itemView);
            player_detail_injury_end = (TextView) itemView.findViewById(R.id.player_detail_injury_end);
            player_detail_injury_start = (TextView) itemView.findViewById(R.id.player_detail_injury_start);
            player_detail_injury_type = (TextView) itemView.findViewById(R.id.player_detail_injury_type);
            player_detail_injury_team_name = (TextView) itemView.findViewById(R.id.player_detail_injury_team_name);
        }
    }

    private static class NoDataViewHolder extends RecyclerView.ViewHolder {
        TextView noData;

        public NoDataViewHolder(View itemView) {
            super(itemView);
            noData = (TextView) itemView.findViewById(R.id.no_data);
        }
    }


}
