package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.BlueshoreFinancial.clientapp3.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentLT.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentLT#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLT extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String URL = "http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/api/geted_screens.php?type=Long-Term&email=";
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
    private OnFragmentInteractionListener mListener;

    public FragmentLT() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLT.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLT newInstance(String param1, String param2) {
        FragmentLT fragment = new FragmentLT();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HttpHandler http =new HttpHandler();
        try{
            SharedPreferences pref = getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
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
                String t1 = jo.getInt("ED_TARGET1_ACHIEVED_STATUS")==1?"Done":"";
                String t2 = jo.getInt("ED_TARGET2_ACHIEVED_STATUS")==1?"Done":"";
                TARGET1[i] = "Target 1: "+jo.getString("ED_BUY_SELL_LABEL")+"@"+jo.getString("ED_TARGET1")+" ("+t1+")";
                TARGET2[i] = "Target 2: "+jo.getString("ED_BUY_SELL_LABEL")+"@"+jo.getString("ED_TARGET1")+" ("+t2+")";
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

        View view=null;
        if(SYMBOL[0].equals("DENIED")){
            view =inflater.inflate(R.layout.fragment_fragment_permission_denied, container, false);
        }
        else {
            view = inflater.inflate(R.layout.fragment_fragment_lt, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.recycler_ed);

            //foodModelArrayList = new ArrayList<>();
            dataModelArrayList = populateList();

            dataAdapter = new DataAdapter_ED(getContext(), dataModelArrayList);
            recyclerView.setAdapter(dataAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }
        return view;
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
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
