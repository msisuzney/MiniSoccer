package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msisuzney.minisoccer.DQDApi.model.TeamMembers;
import com.msisuzney.minisoccer.DQDApi.model.search.News;
import com.msisuzney.minisoccer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/10.
 * Time: 21:44.
 */

public class TeamMembersRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_COACH = 0x00;
    private static final int TYPE_PLAYER = 0x01;
    private static final int TYPE_TITLE = 0x06;


    private Context mCon;

    private String[] titles = {"教练", "前锋", "中场", "后卫", "守门员"};
    private ArrayList<Integer> typeAtPos;
    private int size;
    private List<TeamMembers.PlayerItem> items;
    private OnClickListener listener;

    public TeamMembersRVAdapter(Context mCon) {
        this.mCon = mCon;
        items = new ArrayList<>();
        typeAtPos = new ArrayList<>();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setData(TeamMembers members) {
        List<TeamMembers.PlayerItem> attackers = members.getAttacker();
        List<TeamMembers.PlayerItem> goalkeepers = members.getGoalkeeper();
        List<TeamMembers.PlayerItem> defenders = members.getDefender();
        List<TeamMembers.PlayerItem> midfielders = members.getMidfielder();
        List<TeamMembers.CoachItem> coaches = members.getCoach();
        size = attackers.size() + defenders.size() + midfielders.size() + goalkeepers.size() + coaches.size() +
                titles.length;
        //用一个数组保存每个位置的类型，并将所用的类型都转换成PlayerItem，方便在onBindViewHolder时根据位置获取数据
        typeAtPos.add(TYPE_TITLE);
        addItemTitle(0);
        for (int i = 0; i < coaches.size(); i++) {
            typeAtPos.add(TYPE_COACH);
            TeamMembers.PlayerItem item = new TeamMembers.PlayerItem();
            item.setPerson_id(coaches.get(i).getPerson_di());
            item.setName(coaches.get(i).getName());
            item.setPerson_img(coaches.get(i).getPerson_img());
            item.setShirtnumber(coaches.get(i).getType());
            items.add(item);
        }
        typeAtPos.add(TYPE_TITLE);
        addItemTitle(1);
        for (int i = 0; i < attackers.size(); i++) {
            typeAtPos.add(TYPE_PLAYER);
            items.add(attackers.get(i));
        }
        typeAtPos.add(TYPE_TITLE);
        addItemTitle(2);
        for (int i = 0; i < midfielders.size(); i++) {
            typeAtPos.add(TYPE_PLAYER);
            items.add(midfielders.get(i));
        }
        typeAtPos.add(TYPE_TITLE);
        addItemTitle(3);
        for (int i = 0; i < defenders.size(); i++) {
            typeAtPos.add(TYPE_PLAYER);
            items.add(defenders.get(i));
        }
        typeAtPos.add(TYPE_TITLE);
        addItemTitle(4);
        for (int i = 0; i < goalkeepers.size(); i++) {
            typeAtPos.add(TYPE_PLAYER);
            items.add(goalkeepers.get(i));
        }
    }

    private void addItemTitle(int pos) {
        TeamMembers.PlayerItem itemTitle = new TeamMembers.PlayerItem();
        itemTitle.setName(titles[pos]);
        items.add(itemTitle);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TITLE) {
            return new TitleViewHolder(LayoutInflater.from(mCon).inflate(R.layout.rv_item_team_title, parent, false));
        } else if (viewType == TYPE_COACH) {
            return new CoachViewHolder(LayoutInflater.from(mCon).inflate(R.layout.rv_item_team_member, parent, false));
        } else {
            return new PlayerViewHolder(LayoutInflater.from(mCon).inflate(R.layout.rv_item_team_member, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int adapterPos = holder.getAdapterPosition();
        if (holder instanceof TitleViewHolder) {
            TitleViewHolder viewHolder = (TitleViewHolder) holder;
            viewHolder.team_member_title.setText(items.get(adapterPos).getName());
        } else if (holder instanceof CoachViewHolder) {
            CoachViewHolder viewHolder = (CoachViewHolder) holder;
            viewHolder.goal_iv.setVisibility(View.GONE);
            viewHolder.team_member_number_suffix.setVisibility(View.GONE);
            viewHolder.team_member_goal_count.setVisibility(View.GONE);
            Glide.with(mCon).load(items.get(adapterPos).getPerson_img()).asBitmap().into(viewHolder.team_member_image);
            viewHolder.team_member_name.setText(items.get(adapterPos).getName());
            viewHolder.team_member_number.setText(items.get(adapterPos).getShirtnumber());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCoachItemClick(items.get(adapterPos).getPerson_id());
                    }
                }
            });
        } else {
            PlayerViewHolder viewHolder = (PlayerViewHolder) holder;
            final TeamMembers.PlayerItem item = items.get(adapterPos);
            viewHolder.team_member_number_suffix.setVisibility(View.VISIBLE);
            if (item.getGoals().equals("0")) {
                viewHolder.goal_iv.setVisibility(View.GONE);
                viewHolder.team_member_goal_count.setVisibility(View.GONE);
            } else {
                viewHolder.goal_iv.setVisibility(View.VISIBLE);
                viewHolder.team_member_goal_count.setVisibility(View.VISIBLE);
                viewHolder.team_member_goal_count.setText(item.getGoals());
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onPlayerItemClick(item.getPerson_id());
                    }
                }
            });
            Glide.with(mCon).load(item.getPerson_img()).asBitmap().into(viewHolder.team_member_image);
            viewHolder.team_member_name.setText(item.getName());
            viewHolder.team_member_number.setText(item.getShirtnumber());
        }
    }

    @Override
    public int getItemCount() {
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        if (typeAtPos.get(position) == TYPE_TITLE) {
            return TYPE_TITLE;
        } else if (typeAtPos.get(position) == TYPE_COACH) {
            return TYPE_COACH;
        } else {
            return TYPE_PLAYER;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (typeAtPos.get(position) == TYPE_TITLE) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (spanSizeLookup != null) {
                        return spanSizeLookup.getSpanSize(position);
                    }
                    return 0;
                }
            });

        }

    }

    public interface OnClickListener {
        void onPlayerItemClick(String id);

        void onCoachItemClick(String id);
    }

    private static class PlayerViewHolder extends RecyclerView.ViewHolder {
        ImageView team_member_image, goal_iv;
        TextView team_member_name;
        TextView team_member_number;
        TextView team_member_goal_count;
        TextView team_member_number_suffix;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            team_member_image = (ImageView) itemView.findViewById(R.id.team_member_image);
            team_member_name = (TextView) itemView.findViewById(R.id.team_member_name);
            team_member_number = (TextView) itemView.findViewById(R.id.team_member_number);
            team_member_goal_count = (TextView) itemView.findViewById(R.id.goal_count);
            team_member_number_suffix = (TextView) itemView.findViewById(R.id.number_suffix);
            goal_iv = (ImageView) itemView.findViewById(R.id.goal_iv);

        }
    }

    private static class CoachViewHolder extends RecyclerView.ViewHolder {
        ImageView team_member_image, goal_iv;
        TextView team_member_name;
        TextView team_member_number;
        TextView team_member_goal_count;
        TextView team_member_number_suffix;

        public CoachViewHolder(View itemView) {
            super(itemView);
            team_member_image = (ImageView) itemView.findViewById(R.id.team_member_image);
            team_member_name = (TextView) itemView.findViewById(R.id.team_member_name);
            team_member_number = (TextView) itemView.findViewById(R.id.team_member_number);
            team_member_goal_count = (TextView) itemView.findViewById(R.id.goal_count);
            team_member_number_suffix = (TextView) itemView.findViewById(R.id.number_suffix);
            goal_iv = (ImageView) itemView.findViewById(R.id.goal_iv);

        }
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView team_member_title;

        public TitleViewHolder(View itemView) {
            super(itemView);
            team_member_title = (TextView) itemView.findViewById(R.id.team_member_title);
        }
    }

}
