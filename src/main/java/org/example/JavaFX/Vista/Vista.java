package org.example.JavaFX.Vista;

import org.example.JavaFX.Controlador.Controlador;
import org.example.JavaFX.Modelo.Modelo;


public interface Vista {
    public void setControlador(Controlador c);
    public void setModelo(Modelo m);

    public void mostrarRecomendacion();
    public void borrarRecomendacion();

}
