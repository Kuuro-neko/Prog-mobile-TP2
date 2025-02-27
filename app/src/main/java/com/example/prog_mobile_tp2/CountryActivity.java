package com.example.prog_mobile_tp2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CountryActivity extends MenuActivity implements CountrySelectFragment.OnCountrySelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_country);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_select, new CountrySelectFragment())
                    .replace(R.id.fragment_container_detail, new CountryDetailFragment())
                    .commit();
        }
    }

    @Override
    public void onCountrySelected(String country) {
        CountryDetailFragment countryDetailFragment = (CountryDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_detail);
        if (countryDetailFragment != null) {
            countryDetailFragment.updateCountry(country);
        } else {
            CountryDetailFragment newFragment = new CountryDetailFragment();
            Bundle args = new Bundle();
            args.putString("country", country);
            newFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, newFragment).addToBackStack(null).commit();
        }
    }
}