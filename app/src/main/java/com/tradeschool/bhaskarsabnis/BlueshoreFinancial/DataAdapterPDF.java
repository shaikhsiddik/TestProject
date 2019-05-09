package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.BlueshoreFinancial.clientapp3.R;

import java.util.ArrayList;

public class DataAdapterPDF extends RecyclerView.Adapter<DataAdapterPDF.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<DataModelPDF> DataModelPDFArrayList;
    View view;
    int pos;
    public DataAdapterPDF(Context ctx, ArrayList<DataModelPDF> DataModelPDFArrayList){

        inflater = LayoutInflater.from(ctx);
        this.DataModelPDFArrayList = DataModelPDFArrayList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.recycler_view_pdf, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        pos=position;
      //Picasso.get().load(DataModelPDFArrayList.get(position).getUrl()).into(holder.URL);
        holder.Title.setText(String.valueOf(DataModelPDFArrayList.get(position).getTitle()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Dtata",DataModelPDFArrayList.get(pos).getPdf());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(DataModelPDFArrayList.get(pos).getPdf()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return DataModelPDFArrayList.size();
    }

    public void removeItem(int position) {
        DataModelPDFArrayList.remove(position);
        //notifyItemRemoved(position);
    }
    public ArrayList<DataModelPDF> getData() {
        return DataModelPDFArrayList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView URL;
        private TextView Title;

        public MyViewHolder(View itemView) {
            super(itemView);
            //URL = (ImageView) itemView.findViewById(R.id.thumbnail);
            Title = (TextView) itemView.findViewById(R.id.name1);
        }
    }
}
