package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.BlueshoreFinancial.clientapp3.R;

public class AddSymbolWebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symbol_webview);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/addSymbol.php?email="+pref.getString("email",null));
        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new WebInterface(this), "Android");

    }

    public class WebInterface{
        Context mContext;

        WebInterface(Context c){
            mContext = c;
        }
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void moveToAndroidScreen() {
            Intent intent=new Intent(mContext,Watchlist.class);
            intent.putExtra("POS",1);
            startActivity(intent);

        }


    }
}

//http://upstoxadm.in/BlueshoreFinancial/html/api/getSymbols.php