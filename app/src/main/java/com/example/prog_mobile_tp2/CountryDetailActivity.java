package com.example.prog_mobile_tp2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CountryDetailActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_country_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Get the country from the intent
        String countryName = getIntent().getStringExtra("country");
        Country country = Country.getCountryByName(getResources().getXml(R.xml.countries), countryName);

        assert country != null;

        TextView nameTextView = findViewById(R.id.country_name);
        TextView capitalTextView = findViewById(R.id.country_capital);
        TextView areaTextView = findViewById(R.id.country_area);
        TextView populationTextView = findViewById(R.id.country_population);
        TextView currencyTextView = findViewById(R.id.country_currency);
        TextView languageTextView = findViewById(R.id.country_language);
        TextView flagTextView = findViewById(R.id.country_flag);

        nameTextView.setText(country.getName());
        capitalTextView.setText(country.getCapital());
        areaTextView.setText(String.valueOf(country.getArea()));
        populationTextView.setText(String.valueOf(country.getPopulation()));
        currencyTextView.setText(country.getCurrency());
        languageTextView.setText(country.getLanguage());
        flagTextView.setText(country.getFlag());

    }
}