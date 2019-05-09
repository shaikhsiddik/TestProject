package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.webkit.WebView;

import com.BlueshoreFinancial.clientapp3.R;

public class PortfolioWatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_watch);
        WebView myWebView = (WebView) findViewById(R.id.chart_webview);
        myWebView.loadUrl("http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/graph.php");
        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);

    }
}
