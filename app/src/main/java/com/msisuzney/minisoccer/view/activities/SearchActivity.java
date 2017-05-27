package com.msisuzney.minisoccer.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.msisuzney.minisoccer.DQDApi.model.search.News;
import com.msisuzney.minisoccer.DQDApi.model.search.Search;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.adapter.SearchResultRVAdapter;
import com.msisuzney.minisoccer.presenter.SearchPresenter;
import com.msisuzney.minisoccer.view.SearchView;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends MvpLceActivity<RecyclerView, Search, SearchView, SearchPresenter> implements
        SearchView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.searchEditView)
    MaterialEditText editText;
    @BindView(R.id.contentView)
    RecyclerView rv;

    SearchResultRVAdapter adapter;
    private boolean isLoadingMore;
    private String keywords;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    keywords = getSearchString();
                    loadData();
                    imm.hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    return true;
                }
                return false;
            }
        });
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPos = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPos + 1 == adapter.getItemCount()) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        presenter.loadMoreNews(keywords);
                    }
                }
            }
        });
        adapter = new SearchResultRVAdapter(this);

        adapter.setOnClickListener(new SearchResultRVAdapter.OnClickListener() {
            @Override
            public void onPlayerClick(String player_id) {
                Intent intent = new Intent(SearchActivity.this, PlayerDetailActivity.class);
                intent.putExtra(PlayerDetailActivity.PLAYER_ID, player_id);
                startActivity(intent);
            }

            @Override
            public void onTeamClick(String team_id, String team_logo, String team_name) {
                Intent intent = new Intent(SearchActivity.this, TeamDetailActivity.class);
                intent.putExtra(TeamDetailActivity.TEAM_NAME, team_name);
                intent.putExtra(TeamDetailActivity.TEAM_Id, team_id);
                intent.putExtra(TeamDetailActivity.TEAM_LOGO_URL, team_logo);
                startActivity(intent);
            }

            @Override
            public void onNewsClick(News news) {
                Intent intent = new Intent(SearchActivity.this, NewsDetailActivity.class);
                String id = news.getUrl().replaceAll("\\D", "");
                String url = "https://api.dongqiudi.com/article/" + id + ".html?from=tab_1";
                intent.putExtra(NewsDetailActivity.URL, url);
                startActivity(intent);
            }
        });

        rv.setAdapter(adapter);
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
    }

    private void loadData() {
        if (keywords == null || keywords.length() == 0) {
            Toast.makeText(this, "没有输入内容", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("SearchActivity", "loadData " + keywords);
            loadData(keywords);
        }
    }

    @NonNull
    @Override
    public SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search) {
            loadData();
            return true;
        }
        return false;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public String getSearchString() {
        return editText.getText().toString();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void setData(Search data) {
        Log.d("SearchActivity", data.getWord().get(0));
        adapter.setData(data);
        adapter.notifyDataSetChanged();
        rv.scrollToPosition(0);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
    }

    @Override
    public void loadData(String keyword) {
        showLoading(false);
        presenter.loadData(keyword, false);
    }

    @Override
    public void addNews(List<News> news) {
        adapter.addNews(news);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void haveLoadMore(boolean b) {
        isLoadingMore = false;
    }
}
