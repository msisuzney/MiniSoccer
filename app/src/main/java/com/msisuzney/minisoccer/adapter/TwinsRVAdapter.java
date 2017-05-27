package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msisuzney.minisoccer.DQDApi.model.twins.Feedlist;
import com.msisuzney.minisoccer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/21.
 * Time: 13:56.
 */

public class TwinsRVAdapter extends RecyclerView.Adapter<TwinsRVAdapter.MyViewHolder> {


    private List<Feedlist> list;
    private int picWidthPx;

    private Context context;

    public TwinsRVAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int margin = (int) (30* (metrics.densityDpi*1.0 / 160)); // 30dp
        picWidthPx = metrics.widthPixels - margin;
    }

    public void setData(List<Feedlist> list) {
        this.list = list;
    }

    public void addData(List<Feedlist> list) {
        this.list.addAll(list);
    }

    @Override
    public TwinsRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TwinsRVAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_twins, parent,
                false));
    }

    @Override
    public void onBindViewHolder(TwinsRVAdapter.MyViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        Glide.with(context).load(list.get(pos).getAvatar()).error(R.drawable.ic_insert_emoticon_black_24dp).into
                (holder.icon);

        if (list.get(pos).getPic_url() != null) {
            holder.contentPic.setVisibility(View.VISIBLE);
            int picHeightPx = (int) ((list.get(pos).getPic_height() / (list.get(pos).getPic_width()*1.0)) * picWidthPx);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(picWidthPx, picHeightPx);
//            params.setMargins(20,0,20,0);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            holder.contentPic.setLayoutParams(params);
            Glide.with(context).load(list.get(pos).getPic_url()).into(holder.contentPic);
        } else {
            holder.contentPic.setVisibility(View.GONE);
        }

        if(list.get(pos).getOriginal_text() == null){
            holder.contentRaw.setVisibility(View.GONE);
        }else {
            holder.contentRaw.setVisibility(View.VISIBLE);
            holder.contentRaw.setText(list.get(pos).getOriginal_text());
        }

        if(list.get(pos).getTranslation_text() == null){
            holder.contentZh.setVisibility(View.GONE);
        }else {
            holder.contentZh.setVisibility(View.VISIBLE);
            holder.contentZh.setText(list.get(pos).getTranslation_text());
        }
        holder.nameRaw.setText(list.get(pos).getAccount());
        holder.nameZh.setText(list.get(pos).getNote());
        Glide.with(context).load(list.get(pos).getRelate_ico()).into(holder.type);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView nameRaw;
        TextView nameZh;
        TextView contentZh;
        TextView contentRaw;
        ImageView contentPic;
        ImageView type;

        public MyViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            nameRaw = (TextView) itemView.findViewById(R.id.nameRaw);
            nameZh = (TextView) itemView.findViewById(R.id.nameZh);
            contentZh = (TextView) itemView.findViewById(R.id.contentZh);
            contentRaw = (TextView) itemView.findViewById(R.id.contentRaw);
            contentPic = (ImageView) itemView.findViewById(R.id.twinsPic);
            type = (ImageView) itemView.findViewById(R.id.type);
        }
    }
}
