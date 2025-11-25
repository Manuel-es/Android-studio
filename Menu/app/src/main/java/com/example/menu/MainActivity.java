package com.example.menu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String mensaje="";
            switch (item.getItemId()) {
               // case R.id.opcion1_1:mensaje = "Has elegido Pisos en Madrid";
                //    break;
               // case R.id.opcion1_2:mensaje = "Has elegido Casas en Madrid";
               //     break;
               // case R.id.opcion1_3:mensaje = "Has elegido Locales en Madrid";
                //    break;
              //  case R.id.opcion2_1:mensaje = "Has elegido Pisos en Barcelona";
                //    break;
              //  case R.id.opcion2_2:mensaje = "Has elegido Casas en Barcelona";
               //     break;
               // case R.id.opcion2_3:mensaje = "Has elegido Locales en Barcelona";
               //     break;
               // default:
               //     return super.onOptionsItemSelected(item);
            }

            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            return true;
        }
}