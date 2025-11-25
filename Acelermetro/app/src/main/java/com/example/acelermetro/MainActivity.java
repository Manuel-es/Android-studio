package com.example.acelermetro;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView tx, ty, tz, tvStatusFlag;
    private View rootLayout;
    private long lastShakeTime = 0;
    private final float SHAKE_THRESHOLD = 12.0f; // Umbral en m/s²
    private final int SHAKE_DEBOUNCE_TIME = 1000; // Retardo en ms
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx = findViewById(R.id.tx);
        ty = findViewById(R.id.ty);
        tz = findViewById(R.id.tz);
        tvStatusFlag = findViewById(R.id.tv_status_flag);
        rootLayout = findViewById(R.id.rootLayout);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        } else {
            Toast.makeText(this, "Servicio de sensores no disponible", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            tvStatusFlag.setText("Estado: Normal (Escuchando)");
        } else {
            tvStatusFlag.setText("Estado: Acelerómetro no disponible");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        tvStatusFlag.setText("Estado: En pausa (No escuchando)");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            tx.setText(String.format("X: %.2f m/s²", x));
            ty.setText(String.format("Y: %.2f m/s²", y));
            tz.setText(String.format("Z: %.2f m/s²", z));

            double magnitude = Math.sqrt(x * x + y * y + z * z);
            long now = System.currentTimeMillis();

            if (magnitude > SHAKE_THRESHOLD && (now - lastShakeTime) > SHAKE_DEBOUNCE_TIME) {
                lastShakeTime = now;

                rootLayout.setBackgroundColor(Color.parseColor("#FFD700")); // Dorado
                tvStatusFlag.setText("Estado: SHAKE DETECTADO!");
                Toast.makeText(this, "👋 ¡Shake detectado!", Toast.LENGTH_SHORT).show();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rootLayout.setBackgroundColor(Color.WHITE);
                        tvStatusFlag.setText("Estado: Normal");
                    }
                }, 500); // 500ms de color de aviso
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}