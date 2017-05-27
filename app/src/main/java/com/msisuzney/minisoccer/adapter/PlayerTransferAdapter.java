package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msisuzney.minisoccer.DQDApi.model.PlayerDetail;
import com.msisuzney.minisoccer.R;

import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/12.
 * Time: 12:11.
 */

public class PlayerTransferAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mCon;

    private List<PlayerDetail.Transfer_info> info;

    public PlayerTransferAdapter(Context mCon) {
        this.mCon = mCon;
    }

    public void setInfo(List<PlayerDetail.Transfer_info> info) {
        this.info = info;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (size() == 0){
            return new NoDataViewHolder(LayoutInflater.from(mCon).inflate(R.layout.no_data_view,parent,false));
        }
        return new TransferViewHolder(LayoutInflater.from(mCon).inflate(R.layout.item_player_detail_transfer, parent,
                false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (size() == 0){
            NoDataViewHolder viewHolder = (NoDataViewHolder) holder;
            viewHolder.noData.setText("没有转会数据");
        }else {
            TransferViewHolder viewHolder = (TransferViewHolder) holder;
            int pos = holder.getAdapterPosition();
            viewHolder.announced_date.setText(info.get(pos).getAnnounced_date());
            viewHolder.from.setText(info.get(pos).getFrom_club_name());
            viewHolder.to.setText(info.get(pos).getTo_club_name());
            viewHolder.type.setText(info.get(pos).getType());
            viewHolder.money.setText(info.get(pos).getMoney());
        }
    }

    @Override
    public int getItemCount() {
        return size() == 0 ? 1 : info.size();
    }

    private int size() {
        return info == null ? 0 : info.size();
    }


    private static class NoDataViewHolder extends RecyclerView.ViewHolder{
        TextView noData;
        public NoDataViewHolder(View itemView) {
            super(itemView);
            noData = (TextView) itemView.findViewById(R.id.no_data);
        }
    }

    private static class TransferViewHolder extends RecyclerView.ViewHolder {
        TextView announced_date;
        TextView from;
        TextView to;
        TextView money;
        TextView type;

        public TransferViewHolder(View itemView) {
            super(itemView);
            announced_date = (TextView) itemView.findViewById(R.id.player_detail_announced_date);
            from = (TextView) itemView.findViewById(R.id.player_detail_from);
            to = (TextView) itemView.findViewById(R.id.player_detail_to);
            type = (TextView) itemView.findViewById(R.id.player_detail_transfer_type);
            money = (TextView) itemView.findViewById(R.id.player_detail_transfer_money);
        }
    }
}
