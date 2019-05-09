package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.BlueshoreFinancial.clientapp3.R;

public class OpenAccount extends AppCompatActivity {

    private Toolbar toolbar;
    private CardView cv, cv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_account);
        //toolbar = (Toolbar) findViewById(R.id.toolbar_openAccount);
//        /setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        cv = (CardView) findViewById(R.id.card_view1);
        cv1 = (CardView) findViewById(R.id.card_view);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://recommended-spools.000webhostapp.com/open-account/?f=6LN5"));
                startActivity(intent);
            }
        });
    }
}
