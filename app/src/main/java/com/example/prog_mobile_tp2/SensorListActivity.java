package com.example.prog_mobile_tp2;

import static java.lang.Character.getType;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class SensorListActivity extends MenuActivity {
    SensorManager sensorManager;
    private String listSensor() {
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorDesc = new StringBuilder();
        sensorDesc.append("Number of sensors: ").append(sensors.size()).append("\r\n\n");
        sensorDesc.append("List of sensors that are available on this device: \r\n\n");
        for (Sensor sensor : sensors) {
            sensorDesc.append("New sensor detected : \r\n");
            sensorDesc.append("\tName: ").append(sensor.getName()).append("\r\n");
            sensorDesc.append("\tType: ").append(sensor.getType()).append("\r\n");
            sensorDesc.append("Version: ").append(sensor.getVersion()).append("\r\n");
            sensorDesc.append("Resolution (in the sensor unit): ").append(sensor.getResolution()).append("\r\n");
            sensorDesc.append("Power in mA used by this sensor while in use").append(sensor.getPower()).append("\r\n");
            sensorDesc.append("Vendor: ").append(sensor.getVendor()).append("\r\n");
            sensorDesc.append("Maximum range of the sensor in the sensor's unit.").append(sensor.getMaximumRange()).append("\r\n\n");
        }
        return sensorDesc.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sensor_list);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        TextView textView = findViewById(R.id.capteur_liste);
        textView.setText(listSensor().toString());




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}