package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msisuzney.minisoccer.DQDApi.model.specialNews.ArticleSpecial;
import com.msisuzney.minisoccer.R;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropTransformation;

/**
 * Created by chenxin.
 * Date: 2017/5/24.
 * Time: 14:03.
 */

public class ArticleSpecialRVAdapter extends RecyclerView.Adapter<ArticleSpecialRVAdapter.ArticleViewHolder> {


    public OnItemClickListener onItemClickListener;
    private List<ArticleSpecial> articles;
    private Context mCon;

    public ArticleSpecialRVAdapter(Context mCon) {
        articles = new ArrayList<>();
        this.mCon = mCon;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void setData(List<ArticleSpecial> articles) {
        this.articles = articles;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(mCon).inflate(R.layout.rv_tem_special_article, parent, false));
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        final int pos = holder.getAdapterPosition();
        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(articles.get(pos).getId());
                }
            });
        }
        holder.title.setText(articles.get(pos).getTitle());
        Glide.with(mCon).load(articles.get(pos).getThumb()).bitmapTransform(new CropTransformation(mCon,470, 250, CropTransformation.CropType.TOP)).into(holder.background);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String id);
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView background;
        public ArticleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_special_title);
            background = (ImageView) itemView.findViewById(R.id.special_item);
        }
    }
}
