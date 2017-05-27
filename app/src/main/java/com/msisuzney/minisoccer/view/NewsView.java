package com.msisuzney.minisoccer.view;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.msisuzney.minisoccer.DQDApi.model.news.Article;

import java.util.List;

/**
 * Created by chenxin.
 * Date: 2017/5/8.
 * Time: 12:45.
 */

public interface NewsView extends MvpLceView<List<Article>> {
    void addData(List<Article> data);
    void haveLoadMore(boolean b);
}
