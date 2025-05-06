package org.example.JavaFX.Modelo;

import org.example.JavaFX.Vista.Vista;

import java.util.List;

public interface Modelo {
    void setVista(Vista v);
    void recomendarCanciones(String cancion, String algoritmo, String distancia, int numeroRecomendaciones);
    List<String> getListaCanciones();
}
