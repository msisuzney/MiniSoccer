package com.msisuzney.minisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msisuzney.minisoccer.DQDApi.model.search.News;
import com.msisuzney.minisoccer.DQDApi.model.search.Player;
import com.msisuzney.minisoccer.DQDApi.model.search.Search;
import com.msisuzney.minisoccer.DQDApi.model.search.Team;
import com.msisuzney.minisoccer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/26.
 * Time: 18:50.
 */

public class SearchResultRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_NEWS = 1;
    private static final int TYPE_PLAYER = 2;
    private static final int TYPE_TEAM = 3;
    private static final int TYPE_FOOTER = 4;

    private final String[] titles = {"相关球队", "相关球员", "相关新闻"};
    List<News> list;
    private ArrayList<Integer> typeAtPos;
    private Context mCon;
    private OnClickListener listener;

    public SearchResultRVAdapter(Context mCon) {
        this.mCon = mCon;
        list = new ArrayList<>();
        typeAtPos = new ArrayList<>();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void addNews(List<News> news) {
        if (news != null && news.size() > 0) {
            for (int i = 0; i < news.size(); i++) {
                list.add(news.get(i));
                typeAtPos.add(TYPE_NEWS);
            }
            list.add(new News());
            typeAtPos.add(TYPE_FOOTER);
        }

    }

    public void setData(Search search) {
        list = new ArrayList<>();
        typeAtPos = new ArrayList<>();
        List<Team> teams = search.getTeams();
        List<Player> players = search.getPlayers();
        List<News> news = search.getNews();
        if (teams != null && teams.size() > 0) {
            addTitle(0);
            typeAtPos.add(TYPE_TITLE);
            for (int i = 0; i < teams.size(); i++) {
                News n = new News();
                n.setTitle(teams.get(i).getTeam_name());
                StringBuilder sb = new StringBuilder();
                sb.append(teams.get(i).getTeam_en_name());
                sb.append("/");
                sb.append(teams.get(i).getCountry());
                sb.append("/");
                sb.append(teams.get(i).getVenue_name());
                sb.append("/容纳(");
                sb.append(teams.get(i).getVenue_capacity());
                sb.append("人)");
                n.setDescription(sb.toString());
                n.setThumb(teams.get(i).getTeam_img());
                n.setId(teams.get(i).getTeam_id()); // 设置team_id
                list.add(n);
                typeAtPos.add(TYPE_TEAM);
            }
        }
        if (players != null && players.size() > 0) {
            addTitle(1);
            typeAtPos.add(TYPE_TITLE);
            for (int i = 0; i < players.size(); i++) {
                News n = new News();
                StringBuilder sb = new StringBuilder();
                n.setTitle(players.get(i).getPerson_name());
                sb.append(players.get(i).getPerson_en_name());
                sb.append("/");
                sb.append(players.get(i).getNationality());
                sb.append("/");
                sb.append(players.get(i).getAge());
                sb.append("/");
                sb.append(players.get(i).getTeam());
                sb.append("/");
                sb.append(players.get(i).getPosition());
                n.setDescription(sb.toString());
                n.setThumb(players.get(i).getPerson_img());
                n.setId(players.get(i).getPerson_id());
                list.add(n);
                typeAtPos.add(TYPE_PLAYER);
            }
        }
        if (news != null && news.size() > 0) {
            addTitle(2);
            typeAtPos.add(TYPE_TITLE);
            for (int i = 0; i < news.size(); i++) {
                list.add(news.get(i));
                typeAtPos.add(TYPE_NEWS);
            }
            //有新闻才有footerview
            list.add(new News());
            typeAtPos.add(TYPE_FOOTER);
        }


    }

    private void addTitle(int pos) {
        News news = new News();
        news.setTitle(titles[pos]);
        list.add(news);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TITLE) {
            return new TitleViewHolder(LayoutInflater.from(mCon).inflate(R.layout.rv_item_search_title, parent, false));
        } else if (viewType == TYPE_TEAM) {
            return new SearchTeamViewHolder(LayoutInflater.from(mCon).inflate(R.layout.item_search_team, parent,
                    false));
        } else if (viewType == TYPE_PLAYER) {
            return new SearchPlayerViewHolder(LayoutInflater.from(mCon).inflate(R.layout.item_search_player, parent,
                    false));
        } else if (viewType == TYPE_NEWS) {
            return new SearchNewsViewHolder(LayoutInflater.from(mCon).inflate(R.layout.news_item, parent, false));
        } else { // footer
            return new FooterViewHolder(LayoutInflater.from(mCon).inflate(R.layout.rv_item_footer, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int pos = holder.getAdapterPosition();
        if (holder instanceof TitleViewHolder) {
            TitleViewHolder viewHolder = (TitleViewHolder) holder;
            viewHolder.title.setText(list.get(pos).getTitle());

        } else if (holder instanceof SearchNewsViewHolder) {
            SearchNewsViewHolder viewHolder = (SearchNewsViewHolder) holder;
            viewHolder.title.setText(Html.fromHtml(list.get(pos).getTitle()));
            viewHolder.description.setText(Html.fromHtml(list.get(pos).getDescription()));
            Glide.with(mCon).load(list.get(pos).getThumb()).into(viewHolder.iv);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onNewsClick(list.get(pos));
                    }
                }
            });

        } else if (holder instanceof SearchTeamViewHolder) {
            SearchTeamViewHolder viewHolder = (SearchTeamViewHolder) holder;
            viewHolder.name.setText(Html.fromHtml(list.get(pos).getTitle()));
            viewHolder.description.setText(Html.fromHtml(list.get(pos).getDescription()));
            Glide.with(mCon).load(list.get(pos).getThumb()).into(viewHolder.image);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onTeamClick(list.get(pos).getId(),list.get(pos).getThumb(),list.get(pos).getTitle());
                    }
                }
            });


        } else if (holder instanceof SearchPlayerViewHolder) {
            SearchPlayerViewHolder viewHolder = (SearchPlayerViewHolder) holder;
            viewHolder.name.setText(Html.fromHtml(list.get(pos).getTitle()));
            viewHolder.description.setText(Html.fromHtml(list.get(pos).getDescription()));
            Glide.with(mCon).load(list.get(pos).getThumb()).into(viewHolder.image);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onPlayerClick(list.get(pos).getId());
                    }
                }
            });

        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder viewHolder = (FooterViewHolder) holder;
            if (pos != size() - 1) {
                viewHolder.itemView.setVisibility(View.GONE);
            } else {
                viewHolder.itemView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return size();
    }

    @Override
    public int getItemViewType(int position) {
        if (typeAtPos.get(position) == TYPE_TITLE) {
            return TYPE_TITLE;
        } else if (typeAtPos.get(position) == TYPE_TEAM) {
            return TYPE_TEAM;
        } else if (typeAtPos.get(position) == TYPE_PLAYER) {
            return TYPE_PLAYER;
        } else if (typeAtPos.get(position) == TYPE_NEWS) {
            return TYPE_NEWS;
        } else {
            return TYPE_FOOTER;
        }

    }

    private int size() {
        return list.size();
    }

    public interface OnClickListener {
        void onPlayerClick(String player_id);

        void onTeamClick(String team_id, String team_logo, String team_name);

        void onNewsClick(News news);
    }

    static class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public TitleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.search_title);
        }
    }

    static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        ImageView iv;

        SearchNewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_item_title);
            description = (TextView) itemView.findViewById(R.id.news_item_description);
            iv = (ImageView) itemView.findViewById(R.id.news_item_iv);
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {

        FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class SearchPlayerViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;
        ImageView image;

        public SearchPlayerViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    static class SearchTeamViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;
        ImageView image;

        public SearchTeamViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

}

