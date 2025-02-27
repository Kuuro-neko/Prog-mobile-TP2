package com.example.prog_mobile_tp2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class CountryActivity extends MenuActivity implements CountrySelectFragment.OnCountrySelectedListener{
    private boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_country);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        updateLandscape();
    }


    @Override
    public void onCountrySelected(String country) {
        if (isLandscape) {
            CountryDetailFragment countryDetailFragment = (CountryDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_detail);
            assert countryDetailFragment != null;
            countryDetailFragment.updateCountry(country);
        } else {
            CountryDetailFragment newFragment = new CountryDetailFragment();
            Bundle args = new Bundle();
            args.putString("country", country);
            newFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_select, newFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLandscape = true;
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            isLandscape = false;
        }
        setContentView(R.layout.activity_country);
        updateLandscape();
    }

    private void updateLandscape() {
        if (isLandscape) {
            showTwoPanels();
        } else {
            showOnePanel();
        }
    }

    private void showOnePanel() {
        // Clear both containers first to prevent duplicates
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_select, new CountrySelectFragment())
                .commit();

        // Remove details container if it exists
        Fragment detailsFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_detail);
        if (detailsFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .remove(detailsFragment)
                    .commit();
        }
    }

    private void showTwoPanels() {
        // Remove all fragments first to avoid overlapping
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_select, new CountrySelectFragment())
                .commit();

        // Only add details fragment if the container is present
        if (findViewById(R.id.fragment_container_detail) != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_detail, new CountryDetailFragment())
                    .commit();
        }
    }
}