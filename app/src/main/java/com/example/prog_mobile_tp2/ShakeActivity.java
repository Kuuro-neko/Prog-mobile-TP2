package com.example.prog_mobile_tp2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShakeActivity extends MenuActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private CameraManager cameraManager;
    private boolean torch = false;
    private boolean shaking = false;
    private final long COOLDOWN = 1000; // 1 second cooldown
    private Handler handler = new Handler();
    private Sensor sensor;
    private TextView accelerometer_x;
    private TextView accelerometer_y;
    private TextView accelerometer_z;
    private TextView message;
    private final float EPSILON_MAX = 30.f;
    private final float EPSILON_MIN = 5.f;
    private float epsilon = EPSILON_MIN + (EPSILON_MAX - EPSILON_MIN) * 20.f / 100.f;

    private float interpolation(float f) {
        return (float) Math.sqrt(f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secouer);

        accelerometer_x = findViewById(R.id.accelerometer_x);
        accelerometer_y = findViewById(R.id.accelerometer_y);
        accelerometer_z = findViewById(R.id.accelerometer_z);
        SeekBar sensitivity = findViewById(R.id.sensitivity);
        message = findViewById(R.id.message);
        message.setText("Flashlight is off");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraManager.setTorchMode(cameraManager.getCameraIdList()[0], false);
        } catch (CameraAccessException e) {
            message.setText("Error: " + e.getMessage());
        }

        cameraManager.registerTorchCallback(new CameraManager.TorchCallback() {
            @Override
            public void onTorchModeChanged(String cameraId, boolean enabled) {
                super.onTorchModeChanged(cameraId, enabled);
                onTorchChanged(enabled);
            }
        }, null);

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

    private void onTorchChanged(boolean enabled) {
        torch = enabled;
        if (enabled) {
            message.setText("Flashlight is on");
        } else {
            message.setText("Flashlight is off");
        }
    }

    private void onAcceleroMeterChanged(SensorEvent event) {
        accelerometer_x.setText("X: " + event.values[0]);
        accelerometer_y.setText("Y: " + event.values[1]);
        accelerometer_z.setText("Z: " + event.values[2]);

        if (!shaking && (Math.abs(event.values[0]) + Math.abs(event.values[1]) + Math.abs(event.values[2]) > epsilon)) {
            shaking = true;
            try {
                String cameraId = cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(cameraId, !torch);
            } catch (CameraAccessException e) {
                message.setText("Error: " + e.getMessage());
            }
            handler.postDelayed(() -> shaking = false, COOLDOWN);
        }
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