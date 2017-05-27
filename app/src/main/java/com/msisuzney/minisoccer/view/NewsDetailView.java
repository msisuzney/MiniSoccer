package com.msisuzney.minisoccer.view;

import android.webkit.WebView;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

/**
 * Created by chenxin.
 * Date: 2017/5/10.
 * Time: 12:46.
 */

public interface NewsDetailView extends MvpLceView<Object> {
    WebView getWebView();
}
