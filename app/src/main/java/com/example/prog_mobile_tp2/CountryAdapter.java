package com.example.prog_mobile_tp2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private ArrayList<Country> countries;
    private OnCountryClickListener listener;

    public interface OnCountryClickListener {
        void onCountryClick(Country country);
    }

    public CountryAdapter(ArrayList<Country> countries, OnCountryClickListener listener) {
        this.countries = countries;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.nameTextView.setText(country.getName());
        holder.flagTextView.setText(country.getFlag());
        holder.itemView.setOnClickListener(v -> listener.onCountryClick(country));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView flagTextView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.country_name);
            flagTextView = itemView.findViewById(R.id.country_flag);
        }
    }
}