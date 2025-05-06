package org.example.JavaFX.Vista;

import org.example.JavaFX.Controlador.Controlador;
import org.example.JavaFX.Modelo.Modelo;

import java.util.List;


public interface Vista {
    void setControlador(Controlador c);

    void setModelo(Modelo m);

    void solicitarNuevasRecomendaciones();

    void mostrarNuevasRecomendaciones(List<String> canciones);

    void mostrarListaCanciones();

    void borrarRecomendaciones();

    String getCancionSeleccionada();

    String getAlgoritmo();

    String getDistancia();

    int getCantidadRecomendaciones();

}

