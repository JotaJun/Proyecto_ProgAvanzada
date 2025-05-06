package org.example.JavaFX.Controlador;

import org.example.JavaFX.Modelo.Modelo;
import org.example.JavaFX.Vista.Vista;

import java.util.ArrayList;

public interface Controlador {
    void setModelo(Modelo m);
    void setVista(Vista v);
    void solicitarDatosRecomendar();
}
