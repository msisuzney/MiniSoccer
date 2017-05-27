package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msisuzney.minisoccer.DQDApi.model.Schedule.Match;
import com.msisuzney.minisoccer.DQDApi.model.Schedule.Schedule;
import com.msisuzney.minisoccer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/9.
 * Time: 22:24.
 */

public class ScheduleRVAdapter extends RecyclerView.Adapter<ScheduleRVAdapter.ScheduleItemViewHolder> {


    private Context mCon;
    private List<Match> matches;

    public ScheduleRVAdapter(Context mCon) {
        this.mCon = mCon;
        this.matches = new ArrayList<>();
    }

    public void setData(Schedule scheduleData){
        this.matches = scheduleData.getContent().getMatches();
    }
    
    @Override
    public ScheduleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScheduleItemViewHolder(LayoutInflater.from(mCon).inflate(R.layout.rv_item_schedule,parent,false));
    }

    @Override
    public void onBindViewHolder(ScheduleItemViewHolder holder, int i) {
        holder.item_schedule_home.setText(matches.get(i).getTeam_A_name());
        holder.item_schedule_visit.setText(matches.get(i).getTeam_B_name());
        holder.item_schedule_time.setText(matches.get(i).getStart_play());
        if(matches.get(i).getStatus().equals( "Played")) {
            String ss = matches.get(i).getFs_A() + ":" + matches.get(i).getFs_B();
            holder.item_schedule_score.setText(ss);
        }else {
            holder.item_schedule_score.setText("VS");
        }
        Glide.with(mCon).load(matches.get(i).getTeam_A_logo()).into(holder.item_schedule_home_img);
        Glide.with(mCon).load(matches.get(i).getTeam_B_logo()).into(holder.item_schedule_visit_img);
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    static class ScheduleItemViewHolder extends RecyclerView.ViewHolder {


        TextView item_schedule_time;
        ImageView item_schedule_home_img;
        TextView item_schedule_home;
        TextView item_schedule_score;
        TextView item_schedule_visit;
        ImageView item_schedule_visit_img;

        public ScheduleItemViewHolder(View itemView) {
            super(itemView);
            item_schedule_time = (TextView) itemView.findViewById(R.id.item_schedule_time);
            item_schedule_home_img = (ImageView) itemView.findViewById(R.id.item_schedule_home_img);
            item_schedule_home = (TextView) itemView.findViewById(R.id.item_schedule_home);
            item_schedule_score = (TextView) itemView.findViewById(R.id.item_schedule_score);
            item_schedule_visit = (TextView) itemView.findViewById(R.id.item_schedule_visit);
            item_schedule_visit_img = (ImageView) itemView.findViewById(R.id.item_schedule_visit_img);
        }
    }
}
