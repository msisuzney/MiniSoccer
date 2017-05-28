package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
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
 * Time: 13:12.
 */

public class PlayerTrophyRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mCon;
    private List<PlayerDetail.Trophy_info> info;

    public PlayerTrophyRVAdapter(Context mCon) {
        this.mCon = mCon;

    }

    public void setData(List<PlayerDetail.Trophy_info> info) {
        this.info = info;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (size() == 0) {
            return new NoDataViewHolder(LayoutInflater.from(mCon).inflate(R.layout.no_data_view, parent, false));
        }
        return new PlayerTrophyRVAdapter.MyViewHolder(LayoutInflater.from(mCon).inflate(R.layout
                .item_team_info_trophy, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (size() == 0) {
            NoDataViewHolder viewHolder = (NoDataViewHolder) holder;
            viewHolder.noData.setText("没有荣誉数据");
        } else {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.item_trophy_count.setText(info.get(position).getTimes());
            viewHolder.item_trophy_type.setText(info.get(position).getCompetition_name());
            if (viewHolder.rv.getAdapter() == null)
                viewHolder.rv.setAdapter(new PlayerTrophyItemRVAdapter(mCon, info.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return size() == 0 ? 1 : info.size();
    }

    private int size() {
        return info == null ? 0 : info.size();
    }

    private static class NoDataViewHolder extends RecyclerView.ViewHolder {
        TextView noData;

        public NoDataViewHolder(View itemView) {
            super(itemView);
            noData = (TextView) itemView.findViewById(R.id.no_data);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_trophy_type;
        TextView item_trophy_count;
        RecyclerView rv;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_trophy_type = (TextView) itemView.findViewById(R.id.item_trophy_type);
            item_trophy_count = (TextView) itemView.findViewById(R.id.item_trophy_count);
            rv = (RecyclerView) itemView.findViewById(R.id.container);
            int count = 4;
            rv.setLayoutManager(new GridLayoutManager(mCon, count));
            rv.setNestedScrollingEnabled(false);
        }
    }
}
