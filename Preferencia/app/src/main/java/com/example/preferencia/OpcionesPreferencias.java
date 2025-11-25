package com.example.preferencia;

import android.preference.PreferenceActivity;
import android.os.Bundle;


public class OpcionesPreferencias extends PreferenceActivity  {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferencias);
        }
}
