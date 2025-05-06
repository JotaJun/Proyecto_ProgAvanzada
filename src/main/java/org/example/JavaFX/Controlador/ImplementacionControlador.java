package org.example.JavaFX.Controlador;

import javafx.scene.control.ListView;
import org.example.JavaFX.Modelo.Modelo;
import org.example.JavaFX.Vista.Vista;

import java.util.ArrayList;

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

    @Override
    public void solicitarDatosRecomendar(){

        ArrayList<String> listaRecomendaciones = vista.getListaCanciones();


        if(listaRecomendaciones.isEmpty())
            return;


        String algoritmo = vista.getAlgoritmo();

        if (algoritmo == null)
            return;

        String distancia = vista.getDistancia();

        if(distancia == null)
            return;


        int numeroRecomendaciones = vista.getCantidadRecomendaciones();


        modelo.obtenerCanciones(listaRecomendaciones, algoritmo, distancia, numeroRecomendaciones);
    }
}
