package com.example.prog_mobile_tp2;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;


public class CountrySelectActivity extends MenuActivity {
    private RecyclerView recyclerView;

    public class Country {
        public String name;
        public String capital;
        public int area;
        public int population;
        public String currency;
        public String language;
        public String flag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_country_select);

        recyclerView = findViewById(R.id.country_list);

        ArrayList<Country> countries = readData();

        String[] countryNames = new String[countries.size()];
        for (int i = 0; i < countries.size(); i++) {
            countryNames[i] = countries.get(i).name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countryNames);

       // recyclerView.setAdapter(adapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private ArrayList<Country> readData() {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            XmlResourceParser parser = getResources().getXml(R.xml.countries);
            int eventType = parser.getEventType();
            Country country = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("country".equals(tagName)) {
                            country = new Country();
                        } else if (country != null) {
                            switch (tagName) {
                                case "name":
                                    country.name = parser.nextText();
                                    break;
                                case "capital":
                                    country.capital = parser.nextText();
                                    break;
                                case "area":
                                    country.area = Integer.parseInt(parser.nextText());
                                    break;
                                case "population":
                                    country.population = Integer.parseInt(parser.nextText());
                                    break;
                                case "currency":
                                    country.currency = parser.nextText();
                                    break;
                                case "language":
                                    country.language = parser.nextText();
                                    break;
                                case "flag":
                                    country.flag = parser.nextText();
                                    break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("country".equals(tagName) && country != null) {
                            countries.add(country);
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        for (Country country : countries) {
            System.out.println(country.name);
        }
        return countries;
    }
}