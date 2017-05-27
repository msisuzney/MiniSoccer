package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.msisuzney.minisoccer.DQDApi.model.news.Article;
import com.msisuzney.minisoccer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/1/20.
 * Time: 11:34.
 */

public class NewsItemRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0x01;
    private static final int TYPE_FOOTER = 0x02;
    private List<Article> mArticles;
    private Context mCon;
    private RVOnItemClickListener itemClickListener;


    public NewsItemRVAdapter(Context mCon) {
        this.mCon = mCon;
        mArticles = new ArrayList<>();
    }

    public void setItemClickListener(RVOnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void addData(List<Article> articles) {
        mArticles.addAll(articles);
    }

    public void setupData(List<Article> articles) {
        mArticles = new ArrayList<>();
        mArticles.addAll(articles);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(mCon).inflate(R.layout.rv_item_footer, parent, false);
            return new MyFooterViewHolder(view);
        } else {
            View view = LayoutInflater.from(mCon).inflate(R.layout.news_item, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            final int posAdapter = holder.getAdapterPosition();
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClickListener(mArticles.get(posAdapter));
                    }
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemClickListener != null)
                        itemClickListener.onItemLongClickListener("long click");
                    return true;
                }
            });
            viewHolder.description.setText(mArticles.get(posAdapter).getDescription());
            viewHolder.title.setText(mArticles.get(posAdapter).getTitle());
            Glide.with(mCon).load(mArticles.get(posAdapter).getThumb()).asBitmap().into(viewHolder.iv);
            if(mArticles.get(posAdapter).getIsViewed()){
                viewHolder.title.setTextColor(Color.GRAY);
            }else{
                viewHolder.title.setTextColor(Color.BLACK);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mArticles.size() == 0 ? 0 : mArticles.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mArticles.size()) {
            return TYPE_ITEM;
        } else
            return TYPE_FOOTER;
    }

    public interface RVOnItemClickListener {
        void onItemClickListener(Article article);
        void onItemLongClickListener(String url);
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        ImageView iv;

        MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_item_title);
            description = (TextView) itemView.findViewById(R.id.news_item_description);
            iv = (ImageView) itemView.findViewById(R.id.news_item_iv);
        }
    }

    private static class MyFooterViewHolder extends RecyclerView.ViewHolder {

        MyFooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}




