package com.example.prog_mobile_tp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("MenuActivity", "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_liste_capteurs) {
            Intent intent = new Intent(this, SensorListActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_detection_capteurs) {
            Intent intent = new Intent(this, SensorDetectActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_accelerometre) {
            Intent intent = new Intent(this, AccelerometerActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_direction) {
            Intent intent = new Intent(this, DirectionActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_secouer) {
            Intent intent = new Intent(this, ShakeActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_proximite) {
            Intent intent = new Intent(this, ProximityActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_geolocalisation) {
            Intent intent = new Intent(this, GeolocalizationActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_country_select) {
            Intent intent = new Intent(this, CountrySelectActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}