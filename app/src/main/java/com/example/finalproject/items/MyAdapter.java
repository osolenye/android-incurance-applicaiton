package com.example.finalproject.items;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Map<String, Object>> dataSet;

    public MyAdapter(List<Map<String, Object>> dataSet) {
        this.dataSet = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;

        public ViewHolder(View view) {
            super(view);
            textView1 = view.findViewById(R.id.textView3);
            textView2 = view.findViewById(R.id.textView5);
            textView3 = view.findViewById(R.id.textView4);
            textView4 = view.findViewById(R.id.textView6);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_request_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Предположим, что dataSet содержит данные для каждого TextView
        holder.textView1.setText(dataSet.get(position).get("id").toString());
        holder.textView2.setText(dataSet.get(position).get("industry").toString());
        holder.textView3.setText(dataSet.get(position).get("start_date").toString());
        holder.textView4.setText(dataSet.get(position).get("end_date").toString());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}


