package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msisuzney.minisoccer.DQDApi.model.coach.Career_info;
import com.msisuzney.minisoccer.R;

import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/12.
 * Time: 16:10.
 */

public class CoachCareerRVAdapter extends RecyclerView.Adapter<CoachCareerRVAdapter.CareerViewHolder> {

    private Context mCon;
    private List<Career_info> info;

    public CoachCareerRVAdapter(Context mCon) {
        this.mCon = mCon;
    }

    public void setInfo(List<Career_info> info) {
        this.info = info;
    }

    @Override
    public CareerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CareerViewHolder(LayoutInflater.from(mCon).inflate(R.layout.item_coach_career, parent, false));
    }

    @Override
    public void onBindViewHolder(CareerViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        holder.club.setText(info.get(pos).getTeam_name());
        holder.from.setText(info.get(pos).getStart_date());
        holder.to.setText(info.get(pos).getEnd_date());
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    static class CareerViewHolder extends RecyclerView.ViewHolder {
        TextView from;
        TextView to;
        TextView club;

        public CareerViewHolder(View itemView) {
            super(itemView);
            from = (TextView) itemView.findViewById(R.id.from);
            to = (TextView) itemView.findViewById(R.id.to);
            club = (TextView) itemView.findViewById(R.id.club);
        }

    }
}
