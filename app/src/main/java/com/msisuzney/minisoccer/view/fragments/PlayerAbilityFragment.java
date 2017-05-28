package com.msisuzney.minisoccer.view.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.msisuzney.minisoccer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 只有一个WebView的Fragment，没有用mvp
 */
public class PlayerAbilityFragment extends Fragment {


    public static final String ID = "id";
    @BindView(R.id.webView)
    WebView wv;
    @BindView(R.id.errorView)
    TextView errorView;
    @BindView(R.id.loadingView)
    ProgressBar loading;
    private String id;

    public PlayerAbilityFragment() {
        // Required empty public constructor
    }

    public static PlayerAbilityFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(ID, id);
        PlayerAbilityFragment fragment = new PlayerAbilityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player_ability, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        WebSettings settings = wv.getSettings();
        settings.setBlockNetworkImage(false);
        settings.setJavaScriptEnabled(true);
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress > 70) {
                    errorView.setVisibility(View.GONE);
                    loading.setVisibility(View.GONE);
                    wv.setVisibility(View.VISIBLE);
                }
            }

        });
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse
                    errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                errorView.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                wv.setVisibility(View.GONE);
            }
        });
        if (Build.VERSION.SDK_INT >=21) {
            wv.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (getArguments() == null || (id = getArguments().getString(ID)) == null || id.equals("")) {
            errorView.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
            wv.setVisibility(View.GONE);
        } else {
            String url = "https://api.dongqiudi.com/data/person_ability/" + id;
            Log.d("PlayerAbilityFragment", url);
            errorView.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
            wv.setVisibility(View.VISIBLE);
            wv.loadUrl(url);
        }
    }
}
