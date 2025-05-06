package org.example.JavaFX.Modelo;

import org.example.JavaFX.Vista.Vista;

import java.util.ArrayList;

public class ImplementacionModelo implements  Modelo{
    private Vista vista;

    @Override
    public void setVista(Vista v) {
        vista = v;
    }

    @Override
    public void obtenerCanciones(ArrayList<String> lista, String algoritmo, String distancia, int numeroRecomendaciones) {

    }


}
