package com.example.prog_mobile_tp2;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CountryDetailFragment extends Fragment {
    private TextView nameTextView;
    private TextView capitalTextView;
    private TextView areaTextView;
    private TextView populationTextView;
    private TextView currencyTextView;
    private TextView languageTextView;
    private TextView flagTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_country_detail, container, false);

        Button returnButton = view.findViewById(R.id.return_button);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Show button in portrait mode
            returnButton.setVisibility(View.VISIBLE);
            returnButton.setOnClickListener(v -> {
                requireActivity().getSupportFragmentManager().popBackStack();
            });
        } else {
            // Hide button in landscape mode
            returnButton.setVisibility(View.GONE);
        }

        nameTextView = view.findViewById(R.id.country_name);
        capitalTextView = view.findViewById(R.id.country_capital);
        areaTextView = view.findViewById(R.id.country_area);
        populationTextView = view.findViewById(R.id.country_population);
        currencyTextView = view.findViewById(R.id.country_currency);
        languageTextView = view.findViewById(R.id.country_language);
        flagTextView = view.findViewById(R.id.country_flag);

        if (getArguments() == null) {
            return view;
        }
        String countryName = getArguments().getString("country");
        if (countryName != null) {
            updateCountry(countryName);
        }
        return view;
    }

    public void updateCountry(String countryName) {

        Country country = Country.getCountryByName(getResources().getXml(R.xml.countries), countryName);

        if (country == null) {
            return;
        }

        nameTextView.setText(country.getName());
        capitalTextView.setText(country.getCapital());
        areaTextView.setText(String.valueOf(country.getArea()));
        populationTextView.setText(String.valueOf(country.getPopulation()));
        currencyTextView.setText(country.getCurrency());
        languageTextView.setText(country.getLanguage());
        flagTextView.setText(country.getFlag());
    }
}
