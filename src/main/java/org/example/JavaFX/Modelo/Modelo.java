package org.example.JavaFX.Modelo;

import org.example.JavaFX.Vista.Vista;

import java.util.ArrayList;

public interface Modelo {
    void setVista(Vista v);
    void obtenerCanciones(ArrayList<String> lista, String algoritmo, String distancia, int numeroRecomendaciones);
}
