package com.example.flamenco;

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

    private void irAPalo(String palo) {
        Intent intent = new Intent(this, FlamencoActivity.class);
        intent.putExtra("palo", palo);

        startActivity(intent);
    }

    public void sevillanas(View view) {
        irAPalo("sevillanas");
    }

    public void bulerias(View view) {
        irAPalo("bulerias");
    }

    public void fandangos(View view) {
        irAPalo("fandangos");
    }
}