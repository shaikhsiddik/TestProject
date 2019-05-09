package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.BlueshoreFinancial.clientapp3.R;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        //Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        int number = pref.getInt("isLogged", 0);
        if(number == 0) {
            Intent i = new Intent(Launcher.this, LoginActivity.class);
            startActivity(i);
        } else {
            Intent i = new Intent(Launcher.this, MainActivity.class);
            startActivity(i);
        }
    }
}
