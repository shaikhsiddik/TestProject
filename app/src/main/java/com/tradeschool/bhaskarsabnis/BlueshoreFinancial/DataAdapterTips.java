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

public class DataAdapterTips extends RecyclerView.Adapter<DataAdapterTips.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<DataModelTips> DataModelArrayList;

    public DataAdapterTips(Context ctx, ArrayList<DataModelTips> DataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.DataModelArrayList = DataModelArrayList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_tips, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(position%2==0){
            holder.ll.setBackgroundColor(Color.parseColor("#F1F5F8"));
        }
        //holder.scrip.setText(String.valueOf(DataModelArrayList.get(position).getSn()));
        holder.Qty.setText(String.valueOf(DataModelArrayList.get(position).getTitle()));
    }

    @Override
    public int getItemCount() {
        return DataModelArrayList.size();
    }

    public void removeItem(int position) {
        DataModelArrayList.remove(position);
        //notifyItemRemoved(position);
    }
    public ArrayList<DataModelTips> getData() {
        return DataModelArrayList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView scrip, Qty;
        private LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            ll=(LinearLayout) itemView.findViewById(R.id.l_tips);
           // scrip = (TextView) itemView.findViewById(R.id.sn_tips);
            Qty = (TextView) itemView.findViewById(R.id.nty);


        }
    }
}
