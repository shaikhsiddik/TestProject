package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.BlueshoreFinancial.clientapp3.R;
import com.tradeschool.bhaskarsabnis.BlueshoreFinancial.Tips_Notification;

public class Contact extends AppCompatActivity {
    ImageView iv, iv1, iv2,iv3,iv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.screenHeightDp < 616)
        {
            setContentView(R.layout.activity_contact_small);
        }
        else if (config.screenHeightDp >= 616 && config.screenHeightDp <=650)
        {
            setContentView(R.layout.activity_contact_medium);
        }
        else
        {
            setContentView(R.layout.activity_contact);
        }
        //setContentView(R.layout.);

        iv = (ImageView) findViewById(R.id.home_c);
        iv1 = (ImageView) findViewById(R.id.notification_c);
        iv2 = (ImageView) findViewById(R.id.logout_c);
        iv3 = (ImageView) findViewById(R.id.learn_c);
        iv4 = (ImageView) findViewById(R.id.feeds_c);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Contact.this, MainActivity.class);
                startActivity(i);
            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Contact.this, Tips_Notification.class);
                startActivity(i);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();

                Intent i = new Intent(Contact.this, LoginActivity.class);
                finish();
                startActivity(i);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Contact.this, Learn.class);
                startActivity(i);
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Contact.this, Feeds.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Contact.this,MainActivity.class);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
