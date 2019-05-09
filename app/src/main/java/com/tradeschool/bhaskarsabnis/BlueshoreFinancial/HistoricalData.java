package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.BlueshoreFinancial.clientapp3.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HistoricalData extends AppCompatActivity {

    private Toolbar toolbar;
    private ArrayList<DataModelPDF> dataModelArrayList;
    private RecyclerView recyclerView;
    DataAdapterPDF dataAdapter;

    private String URL = "http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/api/getPdf.php?q=";

    private String[] URI;
    private String[] Title;
    private String[] PDF;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i1 = getIntent();
        String data1 = i1.getStringExtra("q");
        HttpHandler http = new HttpHandler();
        try {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            String str = http.execute(URL+data1+"&email="+pref.getString("email",null)).get();
            //String str = http.execute(URL + data1).get();
            JSONObject myResponse = new JSONObject(str);
            JSONArray jArray = myResponse.getJSONArray("data");
            URI = new String[jArray.length()];
            Title = new String[jArray.length()];

            PDF = new String[jArray.length()];
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo = jArray.getJSONObject(i);
                URI[i] = jo.getString("PDF_TYPE");
                Title[i] = jo.getString("PDF_TITLE");
                PDF[i] = jo.getString("PDF_URL");
            }
        } catch (ExecutionException | InterruptedException ei) {
            ei.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (URI[0].equals("Denied")) {
            setContentView(R.layout.fragment_fragment_permission_denied);
        } else {
            setContentView(R.layout.activity_historical_data);
            tv = (TextView) findViewById(R.id.hist_label);
            if (data1.equals("1")) {
                tv.setText("Historical Data");
            } else {
                tv.setText("Research Reports");
            }
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            recyclerView = (RecyclerView) findViewById(R.id.recycler_pdf);

            //foodModelArrayList = new ArrayList<>();
            dataModelArrayList = populateList();

            dataAdapter = new DataAdapterPDF(getApplicationContext(), dataModelArrayList);
            recyclerView.setAdapter(dataAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        }

    }

    private ArrayList<DataModelPDF> populateList() {
        ArrayList<DataModelPDF> list = new ArrayList<>();
        DataModelPDF dataModel = null;
        for (int i = 0; i < URI.length; i++) {
            dataModel = new DataModelPDF();
            dataModel.setUrl(URI[i]);
            dataModel.setTitle(Title[i]);
            dataModel.setPdf(PDF[i]);
            list.add(dataModel);
        }
        return list;
    }
}