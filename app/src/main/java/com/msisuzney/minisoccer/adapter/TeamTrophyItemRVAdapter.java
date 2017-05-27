package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msisuzney.minisoccer.DQDApi.model.TeamDetail;
import com.msisuzney.minisoccer.R;

/**
 * Created by chenxin.
 * Date: 2017/5/11.
 * Time: 16:05.
 */

public class TeamTrophyItemRVAdapter extends RecyclerView.Adapter<TeamTrophyItemRVAdapter.MyViewHolder> {

    private Context mCon;
    private TeamDetail.Trophy_info info;

    public TeamTrophyItemRVAdapter(Context mCon,TeamDetail.Trophy_info info) {
        this.mCon = mCon;
        this.info = info;
    }

    public void setData(TeamDetail.Trophy_info info) {
        this.info = info;
    }

    @Override
    public TeamTrophyItemRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mCon).inflate(R.layout.trophy_view, parent, false));
    }

    @Override
    public void onBindViewHolder(TeamTrophyItemRVAdapter.MyViewHolder holder, int position) {
        holder.tv.setText(info.getLists().get(position).getSeason_name());
        Glide.with(mCon).load(info.getTrophy_img()).error(R.drawable.cup).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return info.getLists().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.cup_img);
            tv = (TextView) itemView.findViewById(R.id.year);
            //            WindowManager wm = (WindowManager) mCon.getSystemService(Context.WINDOW_SERVICE);
            //            DisplayMetrics metrics = new DisplayMetrics();
            //            wm.getDefaultDisplay().getMetrics(metrics);
            //            int width = (int) (160 * metrics.density); //160px 用 多少 dp表示
            //            int height =  (int) (160 * metrics.density);
            //            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width,height);
            //            itemView.setLayoutParams(lp);
        }
    }
}
