package com.msisuzney.minisoccer.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.msisuzney.minisoccer.DQDApi.model.news.Article;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.NewsItemRVAdapter;
import com.msisuzney.minisoccer.presenter.NewsPresenter;
import com.msisuzney.minisoccer.view.NewsView;
import com.msisuzney.minisoccer.view.activities.NewsDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends MvpLceFragment<SwipeRefreshLayout, List<Article>, NewsView, NewsPresenter> implements
        NewsView, SwipeRefreshLayout.OnRefreshListener, OnBottomBarPositionChangeListener {

    @BindView(R.id.newsRV)
    RecyclerView recyclerView;
    NewsItemRVAdapter adapter;
    int[] ids;
    int bottomBarPosition;
    LinearLayoutManager linearLayoutManager;
    private boolean isLoadingMore;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "NewsFragment onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("test", "NewsFragment onDestroy");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        ids = getResources().getIntArray(R.array.league_news_id);
        contentView.setOnRefreshListener(this);
        adapter = new NewsItemRVAdapter(getActivity());
        adapter.setItemClickListener(new NewsItemRVAdapter.RVOnItemClickListener() {
            @Override
            public void onItemClickListener(Article article) {
                article.setIsViewed(true);
                presenter.updateArticleIsViewed(article);
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.URL, article.getUrl());
                startActivity(intent);
            }

            @Override
            public void onItemLongClickListener(String url) {
            }
        });
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(bottomBarPosition, NewsPresenter.LOAD_REFRESH);
            }
        });
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPos = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPos + 1 == adapter.getItemCount()) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        loadData(bottomBarPosition, NewsPresenter.LOAD_MORE);
                    }
                }
            }
        });
        recyclerView.setAdapter(adapter);
        //MainActivity的第一个Fragment，需要启动时加载，默认加载中超联赛
        loadData(bottomBarPosition, NewsPresenter.LOAD_FROM_DB);
    }


    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public void setData(List<Article> data) {
        adapter.setupData(data);
        adapter.notifyDataSetChanged();
    }

    //由于要根据联赛id显示不同的数据，而提供的这个loadData只有一个参数，不能用，改用loadData(int position,boolean pullToRefresh)
    @Override
    public void loadData(boolean pullToRefresh) {
    }


    @Override
    public void onRefresh() {
        loadData(bottomBarPosition, NewsPresenter.LOAD_REFRESH);
    }

    private void loadData(int position, int mode) {
        int newsId = ids[position];
        presenter.loadData(newsId, mode);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);

    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        contentView.setRefreshing(pullToRefresh);
    }

    @Override
    public void onBottomBarPositionChange(int position) {
        bottomBarPosition = position;
        Log.d("cx", "NewsFragment onBottomBarPositionChange " + position);
        loadData(position, NewsPresenter.LOAD_FROM_DB);
        recyclerView.smoothScrollToPosition(0);
    }


    @Override
    public void addData(List<Article> data) {
        //        isLoadingMore = false;
        adapter.addData(data);
        adapter.notifyDataSetChanged();
    }

    /**
     * @param b 是否完成了加载更多数据
     */
    @Override
    public void haveLoadMore(boolean b) {
//        Log.d("haveLoadMore","haveLoadMore " + b);
        //不管是否加载完成都结束这次isLoadingMore标志，让下一次加载能够正常进行
        //在加载失败比如断网的时候，如果不更新isLoadingMore，那么isLoadingMore一直为true，当网络恢复的时候就不会再通知presenter加载数据
        isLoadingMore = false;
    }

    /*
       在点击新闻返回后及时更新item的状态
     */
    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }
}
