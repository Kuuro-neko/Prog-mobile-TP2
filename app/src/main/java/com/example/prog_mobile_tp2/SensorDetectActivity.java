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

public class SensorDetectActivity extends MenuActivity {
    SensorManager sensorManager;

    public String getSensorTypeString(int sensorType) {
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                return Sensor.STRING_TYPE_ACCELEROMETER;
            case Sensor.TYPE_MAGNETIC_FIELD:
                return Sensor.STRING_TYPE_MAGNETIC_FIELD;
            case Sensor.TYPE_ORIENTATION:
                return Sensor.STRING_TYPE_ORIENTATION;
            case Sensor.TYPE_GYROSCOPE:
                return Sensor.STRING_TYPE_GYROSCOPE;
            case Sensor.TYPE_LIGHT:
                return Sensor.STRING_TYPE_LIGHT;
            case Sensor.TYPE_PRESSURE:
                return Sensor.STRING_TYPE_PRESSURE;
            case Sensor.TYPE_TEMPERATURE:
                return Sensor.STRING_TYPE_TEMPERATURE;
            case Sensor.TYPE_PROXIMITY:
                return Sensor.STRING_TYPE_PROXIMITY;
            case Sensor.TYPE_GRAVITY:
                return Sensor.STRING_TYPE_GRAVITY;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return Sensor.STRING_TYPE_LINEAR_ACCELERATION;
            case Sensor.TYPE_ROTATION_VECTOR:
                return Sensor.STRING_TYPE_ROTATION_VECTOR;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return Sensor.STRING_TYPE_RELATIVE_HUMIDITY;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return Sensor.STRING_TYPE_AMBIENT_TEMPERATURE;
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                return Sensor.STRING_TYPE_MAGNETIC_FIELD_UNCALIBRATED;
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                return Sensor.STRING_TYPE_GAME_ROTATION_VECTOR;
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                return Sensor.STRING_TYPE_GYROSCOPE_UNCALIBRATED;
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                return Sensor.STRING_TYPE_SIGNIFICANT_MOTION;
            case Sensor.TYPE_STEP_DETECTOR:
                return Sensor.STRING_TYPE_STEP_DETECTOR;
            case Sensor.TYPE_STEP_COUNTER:
                return Sensor.STRING_TYPE_STEP_COUNTER;
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                return Sensor.STRING_TYPE_GEOMAGNETIC_ROTATION_VECTOR;
            case Sensor.TYPE_HEART_RATE:
                return Sensor.STRING_TYPE_HEART_RATE;
            case Sensor.TYPE_POSE_6DOF:
                return Sensor.STRING_TYPE_POSE_6DOF;
            case Sensor.TYPE_STATIONARY_DETECT:
                return Sensor.STRING_TYPE_STATIONARY_DETECT;
            case Sensor.TYPE_MOTION_DETECT:
                return Sensor.STRING_TYPE_MOTION_DETECT;
            case Sensor.TYPE_HEART_BEAT:
                return Sensor.STRING_TYPE_HEART_BEAT;
            case Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT:
                return Sensor.STRING_TYPE_LOW_LATENCY_OFFBODY_DETECT;
            case Sensor.TYPE_ACCELEROMETER_UNCALIBRATED:
                return Sensor.STRING_TYPE_ACCELEROMETER_UNCALIBRATED;
            case Sensor.TYPE_HINGE_ANGLE:
                return Sensor.STRING_TYPE_HINGE_ANGLE;
            case Sensor.TYPE_HEAD_TRACKER:
                return Sensor.STRING_TYPE_HEAD_TRACKER;
            case Sensor.TYPE_ACCELEROMETER_LIMITED_AXES:
                return Sensor.STRING_TYPE_ACCELEROMETER_LIMITED_AXES;
            case Sensor.TYPE_GYROSCOPE_LIMITED_AXES:
                return Sensor.STRING_TYPE_GYROSCOPE_LIMITED_AXES;
            case Sensor.TYPE_ACCELEROMETER_LIMITED_AXES_UNCALIBRATED:
                return Sensor.STRING_TYPE_ACCELEROMETER_LIMITED_AXES_UNCALIBRATED;
            case Sensor.TYPE_GYROSCOPE_LIMITED_AXES_UNCALIBRATED:
                return Sensor.STRING_TYPE_GYROSCOPE_LIMITED_AXES_UNCALIBRATED;
            case Sensor.TYPE_HEADING:
                return Sensor.STRING_TYPE_HEADING;
            default:
                return "Unknown sensor type";
        }
    }
    private String listSensor() {
        StringBuilder sensorDesc = new StringBuilder();
        sensorDesc.append("List of sensors types not available : \r\n\n");
        for (int i = 1; i <= 15; i++) {
            List<Sensor> sensors = sensorManager.getSensorList(i);
            if (sensors.size() == 0) {
                sensorDesc.append("No sensor of type ").append(getSensorTypeString(i)).append("\r\n");
            }
        }

        return sensorDesc.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_capteur_liste);

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