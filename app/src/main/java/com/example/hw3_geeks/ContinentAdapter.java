// ContinentAdapter.java
package com.example.hw3_geeks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContinentAdapter extends RecyclerView.Adapter<ContinentAdapter.ContinentViewHolder> {

    private List<String> continentList;
    private OnContinentClickListener listener;

    public ContinentAdapter(List<String> continentList, OnContinentClickListener listener) {
        this.continentList = continentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContinentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ContinentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContinentViewHolder holder, int position) {
        holder.bind(continentList.get(position));
    }

    @Override
    public int getItemCount() {
        return continentList.size();
    }

    public void updateData(List<String> newContinentList) {
        continentList = newContinentList;
        notifyDataSetChanged();
    }

    public class ContinentViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ContinentViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(v -> listener.onContinentClick(continentList.get(getAdapterPosition())));
        }

        public void bind(String continent) {
            textView.setText(continent);
        }
    }

    public interface OnContinentClickListener {
        void onContinentClick(String continent);
    }
}
