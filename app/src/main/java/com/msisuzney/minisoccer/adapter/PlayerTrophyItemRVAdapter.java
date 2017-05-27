package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msisuzney.minisoccer.DQDApi.model.PlayerDetail;
import com.msisuzney.minisoccer.DQDApi.model.TeamDetail;
import com.msisuzney.minisoccer.R;

/**
 * Created by chenxin.
 * Date: 2017/5/12.
 * Time: 13:16.
 */

public class PlayerTrophyItemRVAdapter extends RecyclerView.Adapter<PlayerTrophyItemRVAdapter.MyViewHolder> {

    private Context mCon;
    private PlayerDetail.Trophy_info info;

    public PlayerTrophyItemRVAdapter(Context mCon, PlayerDetail.Trophy_info info) {
        this.mCon = mCon;
        this.info = info;
    }

    @Override
    public PlayerTrophyItemRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlayerTrophyItemRVAdapter.MyViewHolder(LayoutInflater.from(mCon).inflate(R.layout.player_trophy_view, parent, false));
    }

    @Override
    public void onBindViewHolder(PlayerTrophyItemRVAdapter.MyViewHolder holder, int position) {
        holder.tv.setText(info.getLists().get(position).getSeason_name());
        holder.team_name.setText(info.getLists().get(position).getTeam_name());
        Glide.with(mCon).load(info.getTrophy_img()).error(R.drawable.cup).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return info.getLists().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        TextView team_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.cup_img);
            tv = (TextView) itemView.findViewById(R.id.year);
            team_name = (TextView) itemView.findViewById(R.id.club);
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
