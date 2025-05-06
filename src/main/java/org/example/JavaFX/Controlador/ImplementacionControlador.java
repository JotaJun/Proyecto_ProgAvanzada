package org.example.JavaFX.Controlador;

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

    @Override
    public void solicitarDatosRecomendar() {

        String cancionSeleccionada = vista.getCancionSeleccionada();


        String algoritmo = vista.getAlgoritmo();

        if (algoritmo == null)
            return;

        String distancia = vista.getDistancia();

        if(distancia == null)
            return;


        int numeroRecomendaciones = vista.getCantidadRecomendaciones();


        modelo.recomendarCanciones(cancionSeleccionada, algoritmo, distancia, numeroRecomendaciones);

    }
}
