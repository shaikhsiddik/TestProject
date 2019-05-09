package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.BlueshoreFinancial.clientapp3.R;
import com.tradeschool.bhaskarsabnis.BlueshoreFinancial.Tips_Notification;

public class Feeds_old extends AppCompatActivity {

    private Toolbar toolbar;
    private CardView cv, cv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.screenHeightDp < 650)
        {
            setContentView(R.layout.activity_rss_feeds_small);
        }
        else
        {
            setContentView(R.layout.activity_rss_feeds);
        }
       // setContentView(R.layout.activity_rss_feeds);
        WebView myWebView = (WebView) findViewById(R.id.feed_webview);
        myWebView.loadUrl("http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/feed.html");
        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);



    }
    public void logout_notification(View view) {
        Intent intent = new Intent(Feeds_old.this, LoginActivity.class);
        finish();
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(Feeds_old.this, MainActivity.class);
        startActivity(intent);
    }
    public void learn(View view) {
        Intent intent = new Intent(Feeds_old.this, Learn.class);
        startActivity(intent);
    }
    public void contact(View view) {
        Intent intent = new Intent(Feeds_old.this, Contact.class);
        startActivity(intent);
    }
    public void notification(View view) {
        Intent intent = new Intent(Feeds_old.this, Tips_Notification.class);
        startActivity(intent);
    }
}
