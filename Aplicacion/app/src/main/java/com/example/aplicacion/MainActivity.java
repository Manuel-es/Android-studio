package com.example.aplicacion;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewJugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setContentView(R.layout.activity_main);

        recyclerViewJugadores = findViewById(R.id.recyclerViewJugadores);
        recyclerViewJugadores.setLayoutManager(new LinearLayoutManager(this));

        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Lionel Messi", "Inter Miami", "Delantero", 95, R.drawable.messi));
        jugadores.add(new Jugador("Kylian Mbappé", "Real Madrid", "Delantero", 93, R.drawable.mbappe));
        jugadores.add(new Jugador("Luka Modrić", "AC Milan", "Centrocampista", 89, R.drawable.modric));
        jugadores.add(new Jugador("Cristiano Ronaldo", "Al-Nassr", "Delantero", 90, R.drawable.ronaldo));

        JugadorAdapter adapter = new JugadorAdapter(jugadores);
        recyclerViewJugadores.setAdapter(adapter);
    }
}