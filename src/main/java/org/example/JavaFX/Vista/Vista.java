package org.example.JavaFX.Vista;

import javafx.scene.control.ListView;
import org.example.JavaFX.Controlador.Controlador;
import org.example.JavaFX.Modelo.Modelo;

import java.util.ArrayList;


public interface Vista {
    void setControlador(Controlador c);

    void setModelo(Modelo m);

    void mostrarRecomendacion();

    void borrarRecomendaciones();

    ArrayList<String> getListaCanciones();

    String getAlgoritmo();

    String getDistancia();

    int getCantidadRecomendaciones();

}

