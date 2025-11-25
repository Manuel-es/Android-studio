package com.example.aplicacion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JugadorAdapter extends RecyclerView.Adapter<JugadorAdapter.JugadorViewHolder> {

    private List<Jugador> listaJugadores;

    public JugadorAdapter(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    @NonNull
    @Override
    public JugadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jugador, parent, false);
        return new JugadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JugadorViewHolder holder, int position) {
        Jugador jugador = listaJugadores.get(position);
        holder.textNombre.setText(jugador.getNombre());
        holder.textEquipo.setText(jugador.getEquipo());
        holder.textPosicion.setText(jugador.getPosicion());
        holder.textValoracion.setText("Valoración: " + jugador.getValoracion());
        holder.imageJugador.setImageResource(jugador.getImagenResId());
    }

    @Override
    public int getItemCount() {
        return listaJugadores.size();
    }

    public static class JugadorViewHolder extends RecyclerView.ViewHolder {
        TextView textNombre, textEquipo, textPosicion, textValoracion;
        ImageView imageJugador;

        public JugadorViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.textNombre);
            textEquipo = itemView.findViewById(R.id.textEquipo);
            textPosicion = itemView.findViewById(R.id.textPosicion);
            textValoracion = itemView.findViewById(R.id.textValoracion);
            imageJugador = itemView.findViewById(R.id.imageJugador);
        }
    }
}
