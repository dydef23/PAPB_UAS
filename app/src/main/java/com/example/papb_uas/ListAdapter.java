package com.example.papb_uas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter {
    Context ctx;
    ArrayList<ToDoList> listData;

    public ListAdapter(Context context, ArrayList<ToDoList> list){
        this.ctx = context;
        this.listData = list;
    }

    public class VHList extends RecyclerView.ViewHolder{
        TextView itemDo, itemTime;

        public VHList(@NonNull View itemView) {
            super(itemView);
            itemDo = itemView.findViewById(R.id.tvListDo);
            itemTime = itemView.findViewById(R.id.tvListTime);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.ctx).inflate(R.layout.list_row, parent, false);
        return new VHList(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VHList vh = (VHList) holder;
        ToDoList i = this.listData.get(position);

        vh.itemDo.setText(i.getWhat());
        vh.itemTime.setText(i.getTime());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
