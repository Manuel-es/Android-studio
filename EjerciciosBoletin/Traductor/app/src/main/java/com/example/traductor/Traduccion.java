package com.example.traductor;

import java.util.HashMap;
import java.util.Map;

public class Traduccion {
    private static final Map<String, String> Traduccion = new HashMap<>();

    public Traduccion() {
        Traduccion.put("Hola", "Hello");
        Traduccion.put("Casa", "House");
        Traduccion.put("Fútbol", "Football");
        Traduccion.put("Sevilla", "Seville");
        Traduccion.put("Programador", "Developer");
        Traduccion.put("Ordenador", "Computer");
        Traduccion.put("Italia", "Italy");
    }

    public static Map<String, String> getTraduccion() {
        return Traduccion;
    }
}
