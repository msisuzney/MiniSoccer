package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msisuzney.minisoccer.DQDApi.model.leagueRanking.Datum;
import com.msisuzney.minisoccer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/1/20.
 * Time: 19:46.
 */

public class TeamRankingItemRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0x01;
    private static final int TYPE_ITEM = 0x02;
    private List<Datum> rank;
    private Context mCom;
    private TeamRankingItemOnClickListener listener;

    public TeamRankingItemRVAdapter(Context mCom) {
        this.mCom = mCom;
        this.rank = new ArrayList<>();
    }

    public void setListener(TeamRankingItemOnClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Datum> rank) {
        this.rank = rank;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(mCom).inflate(R.layout.team_ranking_header, parent, false));
        }
        return new MyViewHolderTeamRanking(LayoutInflater.from(mCom).inflate(R.layout.team_ranking_item, parent,
                false));
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof MyViewHolderTeamRanking) {
            final int listPos = holder.getAdapterPosition() - 1;//itemView的位置减一
            MyViewHolderTeamRanking viewHolder = (MyViewHolderTeamRanking) holder;
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null)
                        listener.onItemLongClick();
                    return true;
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(rank.get(listPos).getTeam_logo(), rank.get(listPos).getTeam_name(),rank.get(listPos).getTeam_id());
                }
            });
            viewHolder.item_team_rank.setText(rank.get(listPos).getRank());
            Glide.with(mCom).load(rank.get(listPos).getTeam_logo()).into(viewHolder.item_team_rank_img);
            viewHolder.item_ranking_name.setText(rank.get(listPos).getTeam_name());
            viewHolder.item_team_matches_total.setText(rank.get(listPos).getMatches_total());
            viewHolder.item_team_matches_won.setText(rank.get(listPos).getMatches_won());
            viewHolder.item_team_matches_draw.setText(rank.get(listPos).getMatches_draw());
            viewHolder.item_team_matches_lost.setText(rank.get(listPos).getMatches_lost());
            String ss = rank.get(listPos).getGoals_pro() + "/" + rank.get(listPos).getGoals_against();
            viewHolder.item_team_matches_goals.setText(ss);
            viewHolder.item_team_matches_points.setText(rank.get(listPos).getPoints());
        }
    }

    @Override
    public int getItemCount() {
        return rank.size() + 1;
    }

    public interface TeamRankingItemOnClickListener {
        void onItemClick(String logoUrl, String teamName,String teamId);

        void onItemLongClick();
    }

    static class MyViewHolderTeamRanking extends RecyclerView.ViewHolder {
        TextView item_team_rank;
        ImageView item_team_rank_img;
        TextView item_ranking_name;
        TextView item_team_matches_total;
        TextView item_team_matches_won;
        TextView item_team_matches_draw;
        TextView item_team_matches_lost;
        TextView item_team_matches_goals;
        TextView item_team_matches_points;

        public MyViewHolderTeamRanking(View itemView) {
            super(itemView);
            item_team_rank = (TextView) itemView.findViewById(R.id.item_team_rank);
            item_team_rank_img = (ImageView) itemView.findViewById(R.id.item_team_rank_img);
            item_ranking_name = (TextView) itemView.findViewById(R.id.item_ranking_name);
            item_team_matches_total = (TextView) itemView.findViewById(R.id.item_team_matches_total);
            item_team_matches_won = (TextView) itemView.findViewById(R.id.item_team_matches_won);
            item_team_matches_draw = (TextView) itemView.findViewById(R.id.item_team_matches_draw);
            item_team_matches_lost = (TextView) itemView.findViewById(R.id.item_team_matches_lost);
            item_team_matches_goals = (TextView) itemView.findViewById(R.id.item_team_matches_goals);
            item_team_matches_points = (TextView) itemView.findViewById(R.id.item_team_matches_points);

        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }


}

