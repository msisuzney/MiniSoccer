package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msisuzney.minisoccer.DQDApi.model.PersonRanking;
import com.msisuzney.minisoccer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/1/20.
 * Time: 21:23.
 */

public class PersonRankingRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0x02;
    private static final int TYPE_HEADER = 0x01;
    private Context mCon;
    private PersonRanking mRanking;
    private OnItemClickListener listener;
    private List<PersonRanking.Content.Person> persons;

    public PersonRankingRVAdapter(Context mCon) {
        this.mCon = mCon;
        this.persons = new ArrayList<>();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(PersonRanking ranking) {
        mRanking = ranking;
        persons = mRanking.getContent().getData();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (TYPE_HEADER == viewType) {
            return new HeaderViewHolder(LayoutInflater.from(mCon).inflate(R.layout.rv_item_header, parent, false));
        }
        return new PersonRankingItemRVHolder(LayoutInflater.from(mCon).inflate(R.layout.rv_ranking_item, parent,
                false));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int i) {
        if (holder instanceof PersonRankingItemRVHolder) {
            final int listPos = holder.getAdapterPosition() - 1;
            PersonRankingItemRVHolder viewHolder = (PersonRankingItemRVHolder) holder;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(persons.get(listPos).getPerson_id());
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null)
                        listener.onItemLongClick();
                    return true;
                }
            });
            viewHolder.item_rank.setText(String.valueOf(listPos + 1));
            Glide.with(mCon).load(persons.get(listPos).getPerson_logo()).error(R.drawable.ic_person_black_24dp).into(viewHolder.item_rank_img);
            viewHolder.item_name.setText(persons.get(listPos).getPerson_name());
            viewHolder.item_team.setText(persons.get(listPos).getTeam_name());
            viewHolder.item_count.setText(persons.get(listPos).getCount());
        } else if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            viewHolder.header_text_1.setText(mRanking.getContent().getHeader().get(0));
            viewHolder.header_text_2.setText(mRanking.getContent().getHeader().get(1));
            viewHolder.header_text_3.setText(mRanking.getContent().getHeader().get(2));
        }
    }

    @Override
    public int getItemCount() {
        return persons.size() == 0 ? 0 : persons.size() + 1;
    }

    public interface OnItemClickListener {
        void onItemClick(String playerId);

        void onItemLongClick();
    }

    static class PersonRankingItemRVHolder extends RecyclerView.ViewHolder {

        TextView item_rank;
        ImageView item_rank_img;
        TextView item_name;
        TextView item_team;
        TextView item_count;

        public PersonRankingItemRVHolder(View itemView) {
            super(itemView);
            item_rank = (TextView) itemView.findViewById(R.id.rv_item_person_rank);
            item_rank_img = (ImageView) itemView.findViewById(R.id.rv_item_person_img);
            item_name = (TextView) itemView.findViewById(R.id.rv_item_person_name);
            item_team = (TextView) itemView.findViewById(R.id.rv_item_person_team);
            item_count = (TextView) itemView.findViewById(R.id.rv_item_person_count);
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView header_text_1;
        TextView header_text_2;
        TextView header_text_3;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            header_text_1 = (TextView) itemView.findViewById(R.id.rv_item_header_1);
            header_text_2 = (TextView) itemView.findViewById(R.id.rv_item_header_2);
            header_text_3 = (TextView) itemView.findViewById(R.id.rv_item_header_3);
        }
    }
}



