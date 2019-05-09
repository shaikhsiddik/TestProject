package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.BlueshoreFinancial.clientapp3.R;

import java.util.ArrayList;

public class DataAdapter_ED extends RecyclerView.Adapter<DataAdapter_ED.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<DataModel_ED> DataModelArrayList;

    public DataAdapter_ED(Context ctx, ArrayList<DataModel_ED> DataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.DataModelArrayList = DataModelArrayList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_ed, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(position%2==0){
            holder.ll.setBackgroundColor(Color.parseColor("#eff0f1"));
        }
        if(DataModelArrayList.get(position).getUname().equals("")){
            holder.UNAME.setText("No Suggestion yet!!");
        }
        else{
            holder.UNAME.setText(DataModelArrayList.get(position).getUname());
            holder.STOP_LOSS.setText(DataModelArrayList.get(position).getStop_loss());
            holder.BUY.setText(String.valueOf(DataModelArrayList.get(position).getBuy()));
            holder.EP.setText(String.valueOf(DataModelArrayList.get(position).getEp()));
            holder.TP.setText(String.valueOf(DataModelArrayList.get(position).getTp()));
            holder.LTP.setText(String.valueOf(DataModelArrayList.get(position).getLtp()));
            holder.PL.setText(String.valueOf(DataModelArrayList.get(position).getPl()));
            holder.SOURCE.setText(String.valueOf(DataModelArrayList.get(position).getSource()));
            holder.iv.setImageResource(R.drawable.ic_money);

        /*if(Integer.valueOf(DataModelArrayList.get(position).getPl())<0){
            holder.PL.setTextColor(Color.parseColor("#EC252C"));
            holder.cv.setBackgroundResource(R.drawable.button_bg_red);
            holder.cv.setTextColor(Color.parseColor("#EC252C"));
        }*/
            //else{
            holder.PL.setTextColor(Color.parseColor("#32A73E"));
            //holder.cv.setBackgroundResource(R.drawable.button_bg_green);
            //holder.cv.setTextColor(Color.parseColor("#32A73E"));
            //    }
        }
    }

    @Override
    public int getItemCount() {
        return DataModelArrayList.size();
    }

    public void removeItem(int position) {
        DataModelArrayList.remove(position);
        //notifyItemRemoved(position);
    }
    public ArrayList<DataModel_ED> getData() {
        return DataModelArrayList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView UNAME,STOP_LOSS, BUY,EP,TP,LTP,PL,SOURCE,cv;
        private ImageView iv;
        private LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            ll=(LinearLayout) itemView.findViewById(R.id.l_ed);
            UNAME = (TextView) itemView.findViewById(R.id.uname);
            STOP_LOSS = (TextView) itemView.findViewById(R.id.stop_loss);
            BUY = (TextView) itemView.findViewById(R.id.c1r2);
            EP = (TextView) itemView.findViewById(R.id.c2r2);
            TP = (TextView) itemView.findViewById(R.id.c3r2);
            LTP = (TextView) itemView.findViewById(R.id.c1r3);
            PL = (TextView) itemView.findViewById(R.id.c2r3);
            SOURCE =(TextView) itemView.findViewById(R.id.c1r4);
            iv = (ImageView) itemView.findViewById(R.id.thumbnail_img);
            //cv = (TextView) itemView.findViewById(R.id.c2r4);

        }
    }
}
