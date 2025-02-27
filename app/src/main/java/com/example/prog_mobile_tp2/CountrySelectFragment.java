package com.example.prog_mobile_tp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class CountrySelectFragment extends Fragment {

    OnCountrySelectedListener callback;

    public interface OnCountrySelectedListener {
        void onCountrySelected(String country);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnCountrySelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnCountrySelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_select, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.country_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Country> countries = Country.readData(getResources().getXml(R.xml.countries));

        CountryAdapter adapter = new CountryAdapter(countries, country -> {
            // Go to / Update the country details fragment
            callback.onCountrySelected(country.getName());
        });

        recyclerView.setAdapter(adapter);

        return view;
    }
}
