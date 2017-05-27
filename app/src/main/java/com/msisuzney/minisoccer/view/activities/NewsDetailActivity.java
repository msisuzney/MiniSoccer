package com.msisuzney.minisoccer.view.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.presenter.NewsDetailPresenter;
import com.msisuzney.minisoccer.view.NewsDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends MvpLceActivity<WebView, Object, NewsDetailView, NewsDetailPresenter>
        implements NewsDetailView {

    public static final String URL = "URL";
    @BindView(R.id.contentView)
    WebView contentView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        toolbar.setTitle("新闻");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        if (intent == null || (url = intent.getStringExtra(URL)) == null) {
            showError(new Exception("没有文章链接"), false);
        } else {
            presenter.initWebView();
            loadData(url);
        }

    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @NonNull
    @Override
    public NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    public void setData(Object data) {
    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

    private void loadData(String url) {
        showLoading(false);
        presenter.loadData(url);
    }

    @Override
    public WebView getWebView() {
        return contentView;
    }

    @Override
    public void onBackPressed() {
        if (!presenter.canGoBack()) {
            super.onBackPressed();
        }
    }
}
