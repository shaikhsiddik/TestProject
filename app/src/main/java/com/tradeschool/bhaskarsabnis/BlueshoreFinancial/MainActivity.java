package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.BlueshoreFinancial.clientapp3.R;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tradeschool.bhaskarsabnis.BlueshoreFinancial.Tips_Notification;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11, iv12;
    TextView tv, tv1;
    String s="";
    boolean doubleBackToExitPressedOnce = false;
    static Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();

        if (config.screenHeightDp < 616)
        {
            s="As";
            setContentView(R.layout.activity_main_small);
        }
        else if (config.screenHeightDp >= 616 && config.screenHeightDp <=650)
        {
            s="Bs";
            setContentView(R.layout.activity_main_medium);
        }
        else if (config.screenHeightDp >=725)
        {
            s="Cs";
            setContentView(R.layout.activity_main_large);
        }
        else
        {
            s="Ds";
            setContentView(R.layout.activity_main);
        }
        //Toast.makeText(getApplicationContext(),s+" : "+config.screenHeightDp,Toast.LENGTH_LONG).show();
        iv1 = (ImageView) findViewById(R.id.watchlist);
        iv2 = (ImageView) findViewById(R.id.equity_derivative);
        iv3 = (ImageView) findViewById(R.id.openAccount);
        iv4 = (ImageView) findViewById(R.id.historicData);
        iv5 = (ImageView) findViewById(R.id.portfolioWatch);
        iv6 = (ImageView) findViewById(R.id.tips);
        tv = (TextView) findViewById(R.id.sensex);
        tv1 = (TextView) findViewById(R.id.nifty);
        iv7 = (ImageView ) findViewById(R.id.notification);
        iv8 = (ImageView ) findViewById(R.id.research_reports);
        iv9 = (ImageView ) findViewById(R.id.widgets);
        iv10 = (ImageView ) findViewById(R.id.learn);
        iv11 = (ImageView ) findViewById(R.id.contact);
        iv12 = (ImageView ) findViewById(R.id.feeds);


        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, Watchlist.class);
                startActivity(i);
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EquityDerivative.class);
                startActivity(i);
            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, OpenAccount.class);
                startActivity(i);
            }
        });

        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, HistoricalData.class);
                i.putExtra("q","1");
                startActivity(i);
            }
        });

        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PortfolioWatch.class);
                startActivity(i);
            }
        });

        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Tips.class);
                startActivity(i);
            }
        });
        iv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Tips_Notification.class);
                startActivity(i);
            }
        });
        iv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, HistoricalData.class);
                i.putExtra("q","2");
                startActivity(i);
            }
        });
        iv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Widgets.class);
                startActivity(i);
            }
        });
        iv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Learn.class);
                startActivity(i);
            }
        });
        iv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Contact.class);
                startActivity(i);
            }
        });
        iv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Feeds.class);
                startActivity(i);
            }
        });
        setData();
        //this.mHandler = new Handler();
        //this.mHandler.postDelayed(m_Runnable,60000);
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()
        {
            //Toast.makeText(Watchlist.this,"in runnable",Toast.LENGTH_SHORT).show();
            //setData();
            MainActivity.mHandler.postDelayed(m_Runnable, 60000);
        }

    };//runnable

    public void logout(View view) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(i);
    }

    private void setData(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        HttpHandler http =new HttpHandler();
        String data="";
        String URL_SENSEX = "http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/api/get_sensex.php?email="+pref.getString("email",null);
       // String URL_NIFTY = "http://appfeeds.moneycontrol.com/jsonapi/market/indices&ind_id=9";
        try{
            String str = http.execute(URL_SENSEX).get();

            Log.i("DATAT",str);
            JSONObject myResponse = new JSONObject(str);
            JSONArray jArray = myResponse.getJSONArray("data");
            Log.i("Jarray", jArray.toString());
            for(int i=0;i<jArray.length()-1;i++){
                JSONObject jo = jArray.getJSONObject(i);
                JSONObject myResponse1 = jo.getJSONObject("indices");
                data = myResponse1.getString("lastprice");
                Log.i("Jarray_data", data);
                if(i==0){
                    tv.setText(data);
                }
                else{
                    tv1.setText(data);
                }
            }
            JSONObject jo = jArray.getJSONObject(2);
           // JSONObject myResponse1 = jo.getJSONObject("indices");
            String permission = jo.getString("permission");
            Log.i("Permission :" , permission);
            String[] e = {"Long-Term","Short-Term","Intraday","F-O","COMMODITY"};
            for(int i=0; i<permission.length()-1;i++) {
                if (permission.charAt(i) == 'T') {
                    FirebaseMessaging.getInstance().subscribeToTopic(e[i]);
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(e[i]);
                }
            }
            FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
        }
        catch (ExecutionException | InterruptedException ei) {
            ei.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
     //   return data;
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            //super.onBackPressed();
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}