package com.example.proyectoo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.Context;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "FootballQuizUserPrefs";
    private static final String KEY_USERNAME = "last_username";
    private TextView textViewInstruction;
    private EditText editTextName;
    private Button buttonStart;
    private ImageView imageViewLogo;
    private TextView textViewWelcome;
    private EditText editTextGuess;
    private Button buttonCheck;
    private TextView textViewResult;
    private String userName;
    private SharedPreferences sharedPreferences;
    private MediaPlayer mediaPlayer;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        enlazarVistas();
        cargarNombrePersistente();
        mostrarFase(1);
        configurarListeners();

        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.sonido);
        } catch (Exception e) {
            String errorMsg = getString(R.string.error_sound); // Usa string localizado
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    @Override

    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    private void playSound() {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
    }
    private void cargarNombrePersistente() {
        String defaultName = getString(R.string.default_username);
        userName = sharedPreferences.getString(KEY_USERNAME, defaultName);
        editTextName.setText(userName);
    }
    private void guardarNombre(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, name);
        editor.apply();
    }
    private void enlazarVistas() {
        textViewInstruction = findViewById(R.id.textViewInstruction);
        editTextName = findViewById(R.id.editTextName);
        buttonStart = findViewById(R.id.buttonStart);
        imageViewLogo = findViewById(R.id.imageViewLogo);
        textViewWelcome = findViewById(R.id.textViewWelcome);
        editTextGuess = findViewById(R.id.editTextGuess);
        buttonCheck = findViewById(R.id.buttonCheck);
        textViewResult = findViewById(R.id.textViewResult);
    }
    private void mostrarFase(int fase) {
        if (fase == 1) {
            textViewInstruction.setVisibility(View.VISIBLE);
            editTextName.setVisibility(View.VISIBLE);
            buttonStart.setVisibility(View.VISIBLE);
            imageViewLogo.setVisibility(View.GONE);
            textViewWelcome.setVisibility(View.GONE);
            editTextGuess.setVisibility(View.GONE);
            buttonCheck.setVisibility(View.GONE);
            textViewResult.setVisibility(View.GONE);
            textViewInstruction.setText(getString(R.string.instruction_text));
            buttonStart.setText(getString(R.string.button_start));
        } else if (fase == 2) {
            textViewInstruction.setVisibility(View.GONE);
            editTextName.setVisibility(View.GONE);
            buttonStart.setVisibility(View.GONE);
            imageViewLogo.setVisibility(View.VISIBLE);
            textViewWelcome.setVisibility(View.VISIBLE);
            editTextGuess.setVisibility(View.VISIBLE);
            buttonCheck.setVisibility(View.VISIBLE);
            textViewResult.setVisibility(View.VISIBLE);
            imageViewLogo.setImageResource(R.drawable.madrid);
            String welcomeText = getString(R.string.welcome_question, userName);
            textViewWelcome.setText(welcomeText);
            buttonCheck.setText(getString(R.string.button_check));
        }
    }
    private void configurarListeners() {
        buttonStart.setOnClickListener(v -> {
            String enteredName = editTextName.getText().toString().trim();
            if (!enteredName.isEmpty()) {
                userName = enteredName;
            } else {
                userName = getString(R.string.default_username);
            }
            guardarNombre(userName);
            mostrarFase(2);
        });
        buttonCheck.setOnClickListener(v -> {
            checkGuess();
        });
    }
    private void checkGuess() {
        String userGuess = editTextGuess.getText().toString().trim().toUpperCase();
        String correct = getString(R.string.correct_answer);
        if (userGuess.equals(correct)) {
            playSound();
            String successMsg = getString(R.string.result_correct, userName);
            textViewResult.setText(successMsg);
            String winToast = getString(R.string.toast_win);
            Toast.makeText(this, winToast, Toast.LENGTH_SHORT).show();
        } else {
            String errorMsg = getString(R.string.result_incorrect);
            textViewResult.setText(errorMsg);
        }
    }
}