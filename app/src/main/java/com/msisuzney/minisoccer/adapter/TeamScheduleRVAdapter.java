package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msisuzney.minisoccer.DQDApi.model.TeamSchedule;
import com.msisuzney.minisoccer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/10.
 * Time: 15:52.
 */

public class TeamScheduleRVAdapter extends RecyclerView.Adapter<TeamScheduleRVAdapter.MyViewHolder> {

    Context mCon;
    private List<TeamSchedule.Item> items;
    private static final String img_base_url = "http://img.dongqiudi.com/data/pic/";
    public TeamScheduleRVAdapter(Context mCon) {
        this.mCon = mCon;
        items = new ArrayList<>();

    }
    public void setData(TeamSchedule schedules){
        items = schedules.getList();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCon).inflate(R.layout.team_schedule_detail,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        holder.item_team_schedule_detail_home.setText(items.get(i).getTeam_A_name());
        holder.item_team_schedule_detail_visit.setText(items.get(i).getTeam_B_name());
        holder.item_team_schedule_detail_time.setText(items.get(i).getStart_play());
        holder.item_team_schedule_detail_competition_name.setText(items.get(i).getCompetition_name());

        if(items.get(i).getStatus().equals( "Played")) {

            String ss = items.get(i).getAs_A() + ":" + items.get(i).getAs_B();
            holder.item_team_schedule_detail_score.setText(ss);
            //                Log.d(TAG,"status " +schedules.get(i).getStatus() +"    "+ ss+"       "+ i);
        }else if(items.get(i).getStatus().equals( "Fixture")){
            //                Log.d(TAG,"status " +schedules.get(i).getStatus() +"      "+"       "+ i);
            holder.item_team_schedule_detail_score.setText("VS");
        }
        Glide.with(mCon).load(img_base_url + items.get(i).getTeam_A_id()+ ".png").into(holder.item_team_schedule_detail_img);
        Glide.with(mCon).load(img_base_url + items.get(i).getTeam_B_id()+ ".png").into(holder.item_team_schedule_detail_visit_img);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView item_team_schedule_detail_time;
        TextView item_team_schedule_detail_competition_name;
        TextView item_team_schedule_detail_home;
        ImageView item_team_schedule_detail_img;
        TextView item_team_schedule_detail_score;
        ImageView item_team_schedule_detail_visit_img;
        TextView item_team_schedule_detail_visit;
        public MyViewHolder(View itemView) {
            super(itemView);
            item_team_schedule_detail_time = (TextView) itemView.findViewById(R.id.item_team_schedule_detail_time);
            item_team_schedule_detail_competition_name = (TextView) itemView.findViewById(R.id.item_team_schedule_detail_competition_name);
            item_team_schedule_detail_home = (TextView) itemView.findViewById(R.id.item_team_schedule_detail_home);
            item_team_schedule_detail_score = (TextView) itemView.findViewById(R.id.item_team_schedule_detail_score);
            item_team_schedule_detail_visit = (TextView) itemView.findViewById(R.id.item_team_schedule_detail_visit);
            item_team_schedule_detail_img = (ImageView) itemView.findViewById(R.id.item_team_schedule_detail_img);
            item_team_schedule_detail_visit_img = (ImageView) itemView.findViewById(R.id.item_team_schedule_detail_visit_img);
        }
    }
}
