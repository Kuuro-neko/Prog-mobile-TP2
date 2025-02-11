package com.example.prog_mobile_tp2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AccelerometerActivity extends MenuActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    TextView accelerometer_x;
    TextView accelerometer_y;
    TextView accelerometer_z;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accelerometer);

        layout = findViewById(R.id.accelerometer_layout);
        accelerometer_x = findViewById(R.id.accelerometer_x);
        accelerometer_y = findViewById(R.id.accelerometer_y);
        accelerometer_z = findViewById(R.id.accelerometer_z);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void onAcceleroMeterChanged(SensorEvent event) {
        accelerometer_x.setText("X: " + event.values[0]);
        accelerometer_y.setText("Y: " + event.values[1]);
        accelerometer_z.setText("Z: " + event.values[2]);

        int red = Math.round(((event.values[0] + 9.81f) / 19.62f) * 255);
        int green = Math.round(((event.values[1] + 9.81f) / 19.62f) * 255);
        int blue = Math.round(((event.values[2] + 9.81f) / 19.62f) * 255);

        layout.setBackgroundColor(0xff000000 + (red << 16) + (green << 8) + blue);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            onAcceleroMeterChanged(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}