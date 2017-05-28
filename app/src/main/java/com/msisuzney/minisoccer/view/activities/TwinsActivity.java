package com.msisuzney.minisoccer.view.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.msisuzney.minisoccer.DQDApi.model.twins.Feedlist;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.TwinsRVAdapter;
import com.msisuzney.minisoccer.presenter.TwinsPresenter;
import com.msisuzney.minisoccer.view.TwinsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TwinsActivity extends MvpLceActivity<SwipeRefreshLayout, List<Feedlist>, TwinsView, TwinsPresenter>
        implements TwinsView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    TwinsRVAdapter adapter;
    Intent intent;
    String kind;
    private boolean isLoadingMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twins);
        ButterKnife.bind(this);

        intent = getIntent();
        if (intent == null || (kind = intent.getStringExtra(TwinsPresenter.KIND)) == null) {
            showError(new Exception("请求数据错误"), false);
        } else {
            if (kind.equals(TwinsPresenter.TWINS_KIND)) {
                toolbar.setTitle("Twins");
            } else {
                toolbar.setTitle("怡人");
            }
        }
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
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new TwinsRVAdapter(this);
        adapter.setListener(new TwinsRVAdapter.OnClickListener() {
            @Override
            public void onImageClick(String imgUrl, View twinsPic) {
                Intent intent = new Intent(TwinsActivity.this, ImageActivity.class);
                intent.putExtra(ImageActivity.img_url, imgUrl);
                if (Build.VERSION.SDK_INT >= 21) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(TwinsActivity.this, twinsPic, "sharedImgView").toBundle());
                } else {
                    startActivity(intent);
                }
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
                        loadData(TwinsPresenter.LOAD_MORE, kind);
                    }
                }
            }
        });
        contentView.setOnRefreshListener(this);
        loadData(TwinsPresenter.LOAD_FROM_DB, kind);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @NonNull
    @Override
    public TwinsPresenter createPresenter() {
        return new TwinsPresenter();
    }

    @Override
    public void setData(List<Feedlist> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
        contentView.setRefreshing(false);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

    private void loadData(int mode, String kind) {
        presenter.loadData(mode, kind);
    }

    @Override
    public void addData(List<Feedlist> list) {
        adapter.addData(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void haveLoadMore(boolean b) {
        isLoadingMore = false;
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        loadData(TwinsPresenter.LOAD_REFRESH, kind);
    }
}
