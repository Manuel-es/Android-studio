package com.example.aplicacion;

public class Jugador {

    private String nombre;
    private String equipo;
    private String posicion;
    private int valoracion;
    private int imagenResId;

    public Jugador(String nombre, String equipo, String posicion, int valoracion, int imagenResId) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.posicion = posicion;
        this.valoracion = valoracion;
        this.imagenResId = imagenResId;
    }

    public String getNombre() { return nombre; }
    public String getEquipo() { return equipo; }
    public String getPosicion() { return posicion; }
    public int getValoracion() { return valoracion; }
    public int getImagenResId() { return imagenResId; }
}
