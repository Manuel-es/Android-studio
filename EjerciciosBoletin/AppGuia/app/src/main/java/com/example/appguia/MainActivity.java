package com.example.appguia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sevilla(View view) {
        startActivity(new Intent(this, SevillaActivity.class));
    }

    public void granada(View view) {
        startActivity(new Intent(this, GranadaActivity.class));
    }

    public void cordoba(View view) {
        startActivity(new Intent(this, CordobaActivity.class));
    }

}