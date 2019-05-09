package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.BlueshoreFinancial.clientapp3.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<DataModel> DataModelArrayList;

    public DataAdapter(Context ctx, ArrayList<DataModel> DataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.DataModelArrayList = DataModelArrayList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(position%2==0){
            holder.ll.setBackgroundColor(Color.parseColor("#F1F5F8"));
        }
        holder.scrip.setText(DataModelArrayList.get(position).getScrip());
        holder.Qty.setText(String.valueOf(DataModelArrayList.get(position).getQty()));
        holder.ltp.setText(String.valueOf(DataModelArrayList.get(position).getLtp()));
        holder.value.setText(String.valueOf(DataModelArrayList.get(position).getValue()));
        holder.stock.setText(String.valueOf(DataModelArrayList.get(position).getStocks()));
        holder.avgPrice.setText("Avg. Purchase Price: "+String.valueOf(DataModelArrayList.get(position).getAvgPrice()));
    }

    @Override
    public int getItemCount() {
        return DataModelArrayList.size();
    }

    public void removeItem(int position) {
        DataModelArrayList.remove(position);
        //notifyItemRemoved(position);
    }

    public ArrayList<DataModel> getData() {
        return DataModelArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView scrip, Qty,ltp,value,stock,avgPrice;
        private LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            ll=(LinearLayout) itemView.findViewById(R.id.l);
            scrip = (TextView) itemView.findViewById(R.id.scrip);
            Qty = (TextView) itemView.findViewById(R.id.qty);
            ltp = (TextView) itemView.findViewById(R.id.ltp);
            value = (TextView) itemView.findViewById(R.id.value);
            stock = (TextView) itemView.findViewById(R.id.scrip1);
            avgPrice = (TextView) itemView.findViewById(R.id.pp);


        }
    }
}
