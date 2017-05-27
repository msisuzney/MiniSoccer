package com.msisuzney.minisoccer.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.msisuzney.minisoccer.DQDApi.model.specialNewsColumn.SpecialNewsColumnArticle;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.SpecialNewsColumnAdapter;
import com.msisuzney.minisoccer.presenter.SpecialNewsColumnPresenter;
import com.msisuzney.minisoccer.view.SpecialNewsColumnView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialNewsColumnActivity extends MvpLceActivity<SwipeRefreshLayout, List<SpecialNewsColumnArticle>,
        SpecialNewsColumnView, SpecialNewsColumnPresenter> implements SpecialNewsColumnView, SwipeRefreshLayout
        .OnRefreshListener {
    public static final String ID = "id";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.subtitle)
    TextView subtitle;
    SpecialNewsColumnAdapter adapter;
    @BindView(R.id.image)
    ImageView image;
    String id;
    private boolean isLoadingMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_news_column);
        ButterKnife.bind(this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new SpecialNewsColumnAdapter(this);
        adapter.setListener(new SpecialNewsColumnAdapter.OnClickListener() {
            @Override
            public void onClick(SpecialNewsColumnArticle article) {
                String url = "https://api.dongqiudi.com/article/" + article.getAid() + ".html";
                article.setIsViewed(true);
                presenter.updateArticleIsViewed(article);
                Intent intent = new Intent(SpecialNewsColumnActivity.this, NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.URL, url);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPos = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPos + 1 == adapter.getItemCount()) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        loadData(SpecialNewsColumnPresenter.LOAD_MORE, id);
                    }
                }
            }
        });
        if (getIntent() == null || (id = getIntent().getStringExtra(ID)) == null) {
            showError(new Exception("请求数据错误"), false);
        } else {
            loadData(SpecialNewsColumnPresenter.LOAD_FROM_DB, id);
        }
        contentView.setOnRefreshListener(this);
    }

    private void loadData(int mode, String id) {
        presenter.loadData(mode, id);
    }

    @Override
    public void onRefresh() {
        loadData(SpecialNewsColumnPresenter.LOAD_REFRESH, id);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @NonNull
    @Override
    public SpecialNewsColumnPresenter createPresenter() {
        return new SpecialNewsColumnPresenter();
    }

    @Override
    public void setData(List<SpecialNewsColumnArticle> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
        toolbar.setTitle(data.get(0).getMain_title());
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        subtitle.setText(data.get(0).getMain_description());
        Glide.with(this).load(data.get(0).getMain_logo()).into(image);
    }

    @Override
    public void addData(List<SpecialNewsColumnArticle> data) {
        adapter.addData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
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
    public void haveLoadMore(boolean b) {
        isLoadingMore = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }
}
