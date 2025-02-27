package com.example.prog_mobile_tp2;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;


public class CountrySelectActivity extends MenuActivity {
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_country_select);

        recyclerView = findViewById(R.id.country_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Country> countries = Country.readData(getResources().getXml(R.xml.countries));

//        String[] countryNames = new String[countries.size()];
//        for (int i = 0; i < countries.size(); i++) {
//            countryNames[i] = countries.get(i).name;
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countryNames);

       // recyclerView.setAdapter(adapter);
        CountryAdapter adapter = new CountryAdapter(countries, country -> {
            // Go to the country details activity
            Intent intent = new Intent(this, CountryDetailActivity.class);
            intent.putExtra("country",country.getName());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}