package org.example.JavaFX.Modelo;

import org.example.JavaFX.Vista.Vista;

public class ImplementacionModelo implements  Modelo{
    private Vista vista;

    @Override
    public void setVista(Vista v) {
        vista = v;
    }
}
