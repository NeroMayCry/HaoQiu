package com.yiluxiangbei.joey.haoqiu.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.yiluxiangbei.joey.haoqiu.R;


public class PlayerInfoActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings webSettings;
    private Toolbar toolbar;
    private SwipyRefreshLayout swipyRefreshLayout;
    private int progressColor=0xffec407a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);
        toolbar= (Toolbar) findViewById(R.id.toolbar_playerinfo);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        swipyRefreshLayout= (SwipyRefreshLayout) findViewById(R.id.info_swipyrefreshlayout);
        swipyRefreshLayout.setColorSchemeColors(progressColor);


        //imageBack= (ImageView) findViewById(R.id.imageBack);
        Intent intent=getIntent();
        final String url=intent.getStringExtra("playerInfo");
        webView= (WebView) findViewById(R.id.infoWebView);
        webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new webViewClient());
        SwipyRefreshLayout.OnRefreshListener listener=new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                webView.loadUrl(url);
            }
        };
        swipyRefreshLayout.setOnRefreshListener(listener);

        swipyRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipyRefreshLayout.setRefreshing(true);
            }
        });
        listener.onRefresh(SwipyRefreshLayoutDirection.TOP);





    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode== KeyEvent.KEYCODE_BACK)&&webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class webViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            swipyRefreshLayout.setRefreshing(true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            swipyRefreshLayout.setRefreshing(false);
            webView.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
