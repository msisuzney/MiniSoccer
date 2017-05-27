package com.msisuzney.minisoccer.view.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.msisuzney.minisoccer.DQDApi.model.specialNews.ArticleSpecial;
import com.msisuzney.minisoccer.DQDApi.model.specialNewsColumn.SpecialNewsColumn;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.ArticleSpecialRVAdapter;
import com.msisuzney.minisoccer.presenter.SpecialNewsPresenter;
import com.msisuzney.minisoccer.view.SpecialNewsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialNewsActivity extends MvpLceActivity<SwipeRefreshLayout, List<ArticleSpecial>, SpecialNewsView,
        SpecialNewsPresenter> implements SpecialNewsView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    ArticleSpecialRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_news);
        ButterKnife.bind(this);
        toolbar.setTitle("专题");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ArticleSpecialRVAdapter(this);
        adapter.setOnItemClickListener(new ArticleSpecialRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String id) {
                Intent intent = new Intent(SpecialNewsActivity.this, SpecialNewsColumnActivity.class);
                intent.putExtra(SpecialNewsColumnActivity.ID,id);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        contentView.setOnRefreshListener(this);
        loadData(false);//优先数据库加载
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @NonNull
    @Override
    public SpecialNewsPresenter createPresenter() {
        return new SpecialNewsPresenter();
    }

    @Override
    public void setData(List<ArticleSpecial> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        if (pullToRefresh)
            presenter.loadData(SpecialNewsPresenter.LOAD_REFRESH);
        else
            presenter.loadData(SpecialNewsPresenter.LOAD_FROM_DB);
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
}
