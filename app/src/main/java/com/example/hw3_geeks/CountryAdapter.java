// CountryAdapter.java
package com.example.hw3_geeks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<String> countryList;
    private OnCountryClickListener listener;

    public CountryAdapter(List<String> countryList, OnCountryClickListener listener) {
        this.countryList = countryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(countryList.get(position));
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void updateData(List<String> newCountryList) {
        countryList = newCountryList;
        notifyDataSetChanged();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(v -> listener.onCountryClick(countryList.get(getAdapterPosition())));
        }

        public void bind(String country) {
            textView.setText(country);
        }
    }

    public interface OnCountryClickListener {
        void onCountryClick(String country);
    }
}
