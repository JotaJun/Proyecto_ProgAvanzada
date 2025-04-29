package org.example.JavaFX.Controlador;

import javafx.scene.control.Control;
import org.example.JavaFX.Modelo.Modelo;
import org.example.JavaFX.Vista.Vista;

public class ImplementacionControlador implements  Controlador{
    private Vista vista;
    private Modelo modelo;

    @Override
    public void setModelo(Modelo m) {
        modelo = m;
    }

    @Override
    public void setVista(Vista v) {
        vista = v;
    }
}
