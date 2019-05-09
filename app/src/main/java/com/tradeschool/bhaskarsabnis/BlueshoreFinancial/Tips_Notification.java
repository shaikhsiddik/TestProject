package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.BlueshoreFinancial.clientapp3.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Tips_Notification extends AppCompatActivity {
    private int[] sn;
    private String[] Notification ;
    private ArrayList<com.tradeschool.bhaskarsabnis.BlueshoreFinancial.DataModelTips> dataModelArrayList;
    private RecyclerView recyclerView;
    com.tradeschool.bhaskarsabnis.BlueshoreFinancial.DataAdapterTips dataAdapter;
    private String URL = "http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/api/getNotifications.php?email=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.screenHeightDp < 616)
        {
            setContentView(R.layout.activity_notification_small);
        }
        else if (config.screenHeightDp >= 616 && config.screenHeightDp <=650)
        {
            setContentView(R.layout.activity_notification_medium);
        }
        else
        {
            setContentView(R.layout.activity_notification);
        }
        //setContentView(R.layout.activity_notification);
        com.tradeschool.bhaskarsabnis.BlueshoreFinancial.HttpHandler http =new com.tradeschool.bhaskarsabnis.BlueshoreFinancial.HttpHandler();
        try {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            String str = http.execute(URL+pref.getString("email",null)).get();
            //String str = http.execute(URL).get();
            JSONObject myResponse = new JSONObject(str);
            JSONArray jArray = myResponse.getJSONArray("data");
            Notification  = new String[jArray.length()];
            sn  = new int[jArray.length()];
            Log.i("Jarray", jArray.toString());
            for(int i=0;i<jArray.length();i++){
                JSONObject jo = jArray.getJSONObject(i);
                Notification[i] = jo.getString("NOTIFICATION_DATA");
                sn[i] =i+1;
            }
            Log.i("Jarrayy", Notification[0]);
        }
        catch (ExecutionException | InterruptedException ei) {
            ei.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_notification);

        //foodModelArrayList = new ArrayList<>();
        dataModelArrayList = populateList();

        dataAdapter = new com.tradeschool.bhaskarsabnis.BlueshoreFinancial.DataAdapterTips(getApplicationContext(),dataModelArrayList);
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }
    private ArrayList<com.tradeschool.bhaskarsabnis.BlueshoreFinancial.DataModelTips> populateList() {
        ArrayList<com.tradeschool.bhaskarsabnis.BlueshoreFinancial.DataModelTips> list = new ArrayList<>();
        com.tradeschool.bhaskarsabnis.BlueshoreFinancial.DataModelTips dataModel=null;
        for(int i = 0; i < sn.length; i++){
            dataModel = new com.tradeschool.bhaskarsabnis.BlueshoreFinancial.DataModelTips();
           // dataModel.setSn(sn[i]);
            dataModel.setTitle(Notification[i]);
            list.add(dataModel);
        }
        return list;
    }

    public void logout_notification(View view) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();

        Intent intent = new Intent(Tips_Notification.this, com.tradeschool.bhaskarsabnis.BlueshoreFinancial.LoginActivity.class);
        finish();
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(Tips_Notification.this, com.tradeschool.bhaskarsabnis.BlueshoreFinancial.MainActivity.class);
        startActivity(intent);
    }
    public void learn(View view) {
        Intent intent = new Intent(Tips_Notification.this, com.tradeschool.bhaskarsabnis.BlueshoreFinancial.Learn.class);
        startActivity(intent);
    }
    public void contact(View view) {
        Intent intent = new Intent(Tips_Notification.this, com.tradeschool.bhaskarsabnis.BlueshoreFinancial.Contact.class);
        startActivity(intent);
    }
    public void feeds(View view) {
        Intent intent = new Intent(Tips_Notification.this, com.tradeschool.bhaskarsabnis.BlueshoreFinancial.Feeds.class);
        startActivity(intent);
    }
    public void showAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Alert Dialog");
        alertDialog.setMessage("This is Alert Dialog");
        alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "OK is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "CANCEL is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }
}
