package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.BlueshoreFinancial.clientapp3.R;

public class Widgets extends AppCompatActivity {

    private Toolbar toolbar;
    private CardView cv, cv1,cv2,cv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets);
        //toolbar = (Toolbar) findViewById(R.id.toolbar_openAccount);
//        /setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        cv = (CardView) findViewById(R.id.card_view1);
        cv1 = (CardView) findViewById(R.id.card_view2);
        cv2 = (CardView) findViewById(R.id.card_view3);
        cv3 = (CardView) findViewById(R.id.card_view4);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Widgets.this, WidgetsWebView.class);
                intent.putExtra("webView","1");
                startActivity(intent);
            }
        });
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Widgets.this, WidgetsWebView.class);
                intent.putExtra("webView","2");
                startActivity(intent);
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Widgets.this, WidgetsWebView.class);
                intent.putExtra("webView","3");
                startActivity(intent);
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Widgets.this, WidgetsWebView.class);
                intent.putExtra("webView","4");
                startActivity(intent);
            }
        });
    }
}
