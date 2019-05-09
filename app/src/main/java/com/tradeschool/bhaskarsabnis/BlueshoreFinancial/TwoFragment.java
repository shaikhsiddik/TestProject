package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.BlueshoreFinancial.clientapp3.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TwoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwoFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView tv,tv1,tv2;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SharedPreferences sp;
    public static final String mypreference = "mypref";
    public static final String Stock = "stockKey";
    public static final String Symbol = "symbolKey";
    public static final String QTY = "qtyKey";
    public static final String AVG = "avgKey";
    private OnFragmentInteractionListener mListener;
    private String URL = "http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/api/getSymbols.php?email=";
    private String[] scrip;
    private double[] ltp;
    private double[] value;
    private int[] Qty;
    private double[] avgPrice;
    private String[] stocks;
    private Double iv = 0.0,cv=0.0,pl=0.0;
    private static DecimalFormat df2 = new DecimalFormat(".##");
    private ArrayList<DataModel> dataModelArrayList;
    private RecyclerView recyclerView;
    CoordinatorLayout coordinatorLayout;
    DataAdapter dataAdapter;
    JSONArray jsArray;
    public TwoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TwoFragment newInstance(String param1, String param2) {
        TwoFragment fragment = new TwoFragment();
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
        View view = reloadMethod(inflater,container,savedInstanceState);

        return view;

    }

    private View reloadMethod(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        HttpHandler http =new HttpHandler();
        try{
            SharedPreferences pref = getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            String str = http.execute(URL+pref.getString("email",null)).get();
            JSONObject myResponse = new JSONObject(str);
            JSONArray jArray = myResponse.getJSONArray("data");
            scrip = new String[jArray.length()];
            ltp = new double[jArray.length()];
            value = new double[jArray.length()];
            Qty = new int[jArray.length()];
            avgPrice = new double[jArray.length()];
            stocks = new String[jArray.length()];
            for(int i=0;i<jArray.length();i++){
                JSONObject jo = jArray.getJSONObject(i);
                scrip[i] = jo.getString("WL_SCRIP").toUpperCase();
                ltp[i] = jo.getDouble("WL_LTP");
                Qty[i] = jo.getInt("WL_QTY");
                value[i] = Math.round(ltp[i]*Qty[i]);
                avgPrice[i] = jo.getDouble("WL_AVG_PRICE");
                stocks[i] = jo.getString("WL_MARKET").toUpperCase();
                iv +=  Math.round(Qty[i]*avgPrice[i]);
                cv +=  value[i];
                pl +=  Math.round(cv - iv);
            }
        }
        catch (ExecutionException | InterruptedException ei) {
            ei.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //http.execute(URL);
        View view =inflater.inflate(R.layout.fragment_two, container, false);
        tv = (TextView) view.findViewById(R.id.textView_data);
        tv1 = (TextView) view.findViewById(R.id.textView1_data);
        tv2 = (TextView) view.findViewById(R.id.textView2_data);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        //foodModelArrayList = new ArrayList<>();
        dataModelArrayList = populateList();

        dataAdapter = new DataAdapter(getContext(),dataModelArrayList);
        recyclerView.setAdapter(dataAdapter);
        tv.setText(iv.toString());
        tv1.setText(cv.toString());
        tv2.setText(pl.toString());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        enableSwipeToDeleteAndUndo();
        return view;
    }
    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
                final String item = dataAdapter.getData().get(position).toString();
                dataAdapter.removeItem(position);
                recyclerView.scrollToPosition(position);
                Toast.makeText(getContext(),"Item Removed",Toast.LENGTH_LONG).show();
                recyclerView.setAdapter(dataAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }


    private ArrayList<DataModel> populateList() {
        ArrayList<DataModel> list = new ArrayList<>();
        DataModel dataModel=null;
        for(int i = 0; i < scrip.length; i++){
            dataModel = new DataModel();
            dataModel.setScrip(scrip[i]);
            dataModel.setQty(Qty[i]);
            dataModel.setLtp(ltp[i]);
            dataModel.setValue(value[i]);
            dataModel.setAvgPrice(avgPrice[i]);
            dataModel.setStocks(stocks[i]);
            list.add(dataModel);
        }

        sp = getActivity().getSharedPreferences(mypreference,getContext().MODE_PRIVATE);
        if(sp.contains(Stock)){
            dataModel.setScrip(sp.getString(Symbol,""));
            dataModel.setQty(sp.getInt(QTY,0));
            dataModel.setLtp(0);
            dataModel.setValue(0);
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
