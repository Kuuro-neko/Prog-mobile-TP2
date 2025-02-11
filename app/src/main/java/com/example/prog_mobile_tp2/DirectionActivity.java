package com.example.prog_mobile_tp2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DirectionActivity extends MenuActivity  implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView accelerometer_x;
    private TextView accelerometer_y;
    private TextView accelerometer_z;
    private TextView direction;
    private SeekBar sensitivity;

    private final float EPSILON_MAX = 5.f;
    private final float EPSILON_MIN = 0.1f;
    private float epsilon = EPSILON_MIN + (EPSILON_MAX - EPSILON_MIN) * 20.f / 100.f;

    private float interpolation(float f) {
        return (float) Math.sqrt(f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_direction);

        accelerometer_x = findViewById(R.id.accelerometer_x);
        accelerometer_y = findViewById(R.id.accelerometer_y);
        accelerometer_z = findViewById(R.id.accelerometer_z);
        direction = findViewById(R.id.direction);
        sensitivity = findViewById(R.id.sensitivity);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        sensitivity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                epsilon = EPSILON_MIN + (EPSILON_MAX - EPSILON_MIN) * interpolation((100-progress) / 100.f);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

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

        String directionText = "";

        if (event.values[0] > epsilon)
            directionText += "Droite ";
        else if (event.values[0] < -epsilon)
            directionText += "Gauche ";
        if (event.values[1] > epsilon)
            directionText += "Haut ";
        else if (event.values[1] < -epsilon)
            directionText += "Bas ";
        if (event.values[2] > epsilon)
            directionText += "Avant";
        else if (event.values[2] < -epsilon)
            directionText += "Arrière";

        if (directionText.isEmpty()) {
            directionText += "Immobile";
        }

        direction.setText(directionText);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
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