package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import com.BlueshoreFinancial.clientapp3.R;

public class WidgetsWebView extends AppCompatActivity {
    private String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets_webview);
        Intent i = getIntent();
        String intent = i.getStringExtra("webView");
        Log.i("Intent",intent);
        switch(intent){
            case "1":
                URL ="http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/technical_analysis_widget.php";
                break;
            case "2":
                URL ="http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/stock_market_widget.php";
                break;
            case "3":
                URL ="http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/ticker_widget.php";
                break;
            case "4":
                URL ="http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/symbol_overview_widget.php";
                break;
        }
        WebView myWebView = (WebView) findViewById(R.id.widgets_webview);
        myWebView.loadUrl(URL);
        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);

    }
}
