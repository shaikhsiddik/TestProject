package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.BlueshoreFinancial.clientapp3.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Tips extends AppCompatActivity {
    private String URL = "http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/api/geted_screens.php?type=Free-Tips&email=";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String[] SYMBOL ;
    private String[] STOP_LOSS ;
    private String[] QTY ;
    private String[] CMP ;
    private String[] TARGET1 ;
    private String[] TARGET2 ;
    private String[] PL_ARR ;
    private String[] TIMESTAMP ;

    private ArrayList<DataModel_ED> dataModelArrayList;
    private RecyclerView recyclerView;
    DataAdapter_ED dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        HttpHandler http =new HttpHandler();
        try{
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            String str = http.execute(URL+pref.getString("email",null)).get();
            JSONObject myResponse = new JSONObject(str);
            JSONArray jArray = myResponse.getJSONArray("data");
            SYMBOL  = new String[jArray.length()];
            STOP_LOSS = new String[jArray.length()];
            QTY = new String[jArray.length()];
            CMP = new String[jArray.length()];
            TARGET1 = new String[jArray.length()];
            TARGET2 = new String[jArray.length()];
            PL_ARR = new String[jArray.length()];
            TIMESTAMP = new String[jArray.length()];

            for(int i=0;i<jArray.length();i++){
                JSONObject jo = jArray.getJSONObject(i);
                SYMBOL[i] = jo.getString("ED_COMPANY").toUpperCase();
                STOP_LOSS[i] = "STOP LOSS:"+jo.getString("ED_STOP_LOSS");
                QTY[i] = jo.getString("ED_Q_L_LABEL")+":"+jo.getString("ED_Q_L");
                CMP[i] = "CMP: " + jo.getString("ED_CMP");
                String t1 = jo.getInt("ED_TARGET1_ACHIEVED_STATUS")==1?"Achieved":"Pending";
                String t2 = jo.getInt("ED_TARGET2_ACHIEVED_STATUS")==1?"Achieved":"Pending";
                TARGET1[i] = "T1: "+jo.getString("ED_BUY_SELL_LABEL")+"@"+jo.getString("ED_TARGET1")+" ("+t1+")";
                TARGET2[i] = "T1: "+jo.getString("ED_BUY_SELL_LABEL")+"@"+jo.getString("ED_TARGET1")+" ("+t2+")";
                PL_ARR[i] =  jo.getString("ED_P_L_LABEL")+"@: "+jo.getString("ED_P_L");
                TIMESTAMP[i] =  "Created At: "+jo.getString("ED_CREATED_AT");
            }
        }
        catch (ExecutionException | InterruptedException ei) {
            ei.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_tips);

        //foodModelArrayList = new ArrayList<>();
        dataModelArrayList = populateList();

        dataAdapter = new DataAdapter_ED(getApplicationContext(),dataModelArrayList);
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }
    private ArrayList<DataModel_ED> populateList() {
        ArrayList<DataModel_ED> list = new ArrayList<>();
        DataModel_ED dataModel=null;
        for(int i = 0; i < SYMBOL.length; i++){
            dataModel = new DataModel_ED();
            dataModel.setUname(SYMBOL[i]);
            dataModel.setStop_loss(STOP_LOSS[i]);
            dataModel.setBuy(QTY[i]);
            dataModel.setEp(TARGET1[i]);
            dataModel.setTp(TARGET2[i]);
            dataModel.setLtp(CMP[i]);
            dataModel.setPl(PL_ARR[i]);
            dataModel.setSource(TIMESTAMP[i]);
            list.add(dataModel);
        }
        return list;
    }
}
