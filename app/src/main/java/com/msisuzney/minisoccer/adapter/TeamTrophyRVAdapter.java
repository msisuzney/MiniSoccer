package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.msisuzney.minisoccer.DQDApi.model.TeamDetail;
import com.msisuzney.minisoccer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/11.
 * Time: 10:59.
 */

public class TeamTrophyRVAdapter extends RecyclerView.Adapter<TeamTrophyRVAdapter.MyViewHolder> {

    private Context mCon;
    private List<TeamDetail.Trophy_info> info;

    //所有的TeamTrophyRV共享一个pool
    private static RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();

    public TeamTrophyRVAdapter(Context mCon) {
        this.mCon = mCon;
        this.info = new ArrayList<>();
    }

    public void setData(List<TeamDetail.Trophy_info> info) {
        this.info = info;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mCon).inflate(R.layout.item_team_info_trophy, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.item_trophy_count.setText(info.get(position).getTimes());
        holder.item_trophy_type.setText(info.get(position).getCompetition_name());
        if (holder.rv.getAdapter() == null)
            holder.rv.setAdapter(new TeamTrophyItemRVAdapter(mCon, info.get(position)));
    }

    @Override
    public int getItemCount() {
        return info.size();
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
            WindowManager wm = (WindowManager) mCon.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(metrics);
            int count = 4 ;//每排奖杯的数量
            rv.setRecycledViewPool(pool);
            rv.setLayoutManager(new GridLayoutManager(mCon, count));
            rv.setNestedScrollingEnabled(false);
        }
    }
}
