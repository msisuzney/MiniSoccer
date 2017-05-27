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
import com.msisuzney.minisoccer.DQDApi.model.specialNewsColumn.SpecialNewsColumnArticle;
import com.msisuzney.minisoccer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/24.
 * Time: 16:07.
 */

public class SpecialNewsColumnAdapter extends RecyclerView.Adapter<SpecialNewsColumnAdapter.MyViewHolder> {

    private OnClickListener listener;
    private Context mCon;
    private List<SpecialNewsColumnArticle> articles;

    public SpecialNewsColumnAdapter(Context mCon) {
        this.mCon = mCon;
        articles = new ArrayList<>();
    }

    public void setListener(OnClickListener listener){
        this.listener = listener;
    }

    public void setData(List<SpecialNewsColumnArticle> articles) {
        this.articles = articles;
    }

    public void addData(List<SpecialNewsColumnArticle> articles) {
        this.articles.addAll(articles);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCon).inflate(R.layout.news_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        final int posAdapter = viewHolder.getAdapterPosition();
        viewHolder.description.setText(articles.get(posAdapter).getDescription());
        viewHolder.title.setText(articles.get(posAdapter).getTitle());
        Glide.with(mCon).load(articles.get(posAdapter).getLitpic()).asBitmap().into(viewHolder.iv);
        if (articles.get(posAdapter).getIsViewed()) {
            viewHolder.title.setTextColor(Color.GRAY);
        } else {
            viewHolder.title.setTextColor(Color.BLACK);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(articles.get(posAdapter));
                    articles.get(posAdapter).setIsViewed(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public interface OnClickListener {
        void onClick(SpecialNewsColumnArticle  article);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

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
}
