package com.msisuzney.minisoccer.presenter;

import android.os.Build;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.msisuzney.minisoccer.view.NewsDetailView;

/**
 * Created by chenxin.
 * Date: 2017/5/10.
 * Time: 12:59.
 */

public class NewsDetailPresenter extends MvpBasePresenter<NewsDetailView> {
    public void initWebView() {
        if (isViewAttached()) {
            WebSettings settings = getView().getWebView().getSettings();
            settings.setBlockNetworkImage(false);
            settings.setJavaScriptEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            getView().getWebView().setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.startsWith("dongqiudi:")) {
                        String articleId = url.replaceAll("\\D", "");
                        StringBuilder sb = new StringBuilder("https://api.dongqiudi.com/article/");
                        sb.append(articleId);
                        sb.append(".html?from=tab_56");
                        String newsUrl = sb.toString();
                        getView().getWebView().loadUrl(newsUrl);
                }
                    return false;
                }
            });
            getView().getWebView().setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    if (newProgress > 40) {
                        if (isViewAttached())
                            getView().showContent();
                    }
                }
            });
        }
    }

    public void loadData(String url) {
        if (isViewAttached()) {
            getView().getWebView().loadUrl(url);
        }
    }

    public boolean canGoBack() {
        if (isViewAttached() && getView().getWebView().canGoBack()) {
            getView().getWebView().goBack();
            return true;
        } else {
            return false;
        }
    }
}
