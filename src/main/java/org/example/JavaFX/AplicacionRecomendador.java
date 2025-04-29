package org.example.JavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.JavaFX.Controlador.ImplementacionControlador;
import org.example.JavaFX.Modelo.ImplementacionModelo;
import org.example.JavaFX.Vista.ImplementacionVista;


public class AplicacionRecomendador extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ImplementacionControlador controlador = new ImplementacionControlador();
        ImplementacionModelo modelo = new ImplementacionModelo();
        ImplementacionVista vista = new ImplementacionVista(stage);

        modelo.setVista(vista);
        controlador.setVista(vista);
        controlador.setModelo(modelo);
        vista.setModelo(modelo);

        vista.creaGUI();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
