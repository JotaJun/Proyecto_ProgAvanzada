package org.example.JavaFX;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AplicacionRecomendador extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox vboxIzquierda = new VBox(); // Contenedor principal
        Label lab_elige_canc = new Label("ELIGE LA CANCIÓN QUE TE GUSTE"); // Texto no editable
        Button bt_obten_canc = new Button("OBTENER RECOMENDACIONES"); // Un botón, no hace nada
        ListView<String> listaCanciones = new ListView();
        listaCanciones.getItems().add("Canciones del recomendador");
        vboxIzquierda.getChildren().addAll(lab_elige_canc, listaCanciones, bt_obten_canc); // Añade elementos al contenedor
        vboxIzquierda.setAlignment(Pos.CENTER_LEFT); // Centra los elementos
// Añade el contenedor a la escena, y la escena al escenario

        VBox vboxGenero = new VBox();
        Label lab_genero = new Label("RECOMENDAR EN BASE A");
        RadioButton rb_genero = new RadioButton("Genero");
        RadioButton rb_simil = new RadioButton("Similitudes");
        vboxGenero.getChildren().addAll(lab_genero,rb_genero, rb_simil);


        VBox vboxOpciones = new VBox();
        Label lab_opciones = new Label("OPCIÓN EXTRA");
        Label lab_elige_metrica = new Label("Elige la métrica");
        ComboBox cbOpciones = new ComboBox();
        cbOpciones.getItems().add("Manhattan");
        cbOpciones.getItems().add("Euclidean");
        vboxOpciones.getChildren().addAll(lab_opciones, lab_elige_metrica,cbOpciones);

        HBox hboxUnionRecYOpcion = new HBox();
        hboxUnionRecYOpcion.getChildren().addAll(vboxGenero, vboxOpciones);

        HBox hboxNumRecs = new HBox();
        Spinner<Integer> num_rec = new Spinner<>();
        Button bt_mas5 = new Button("+5");
        Button bt_menos5 = new Button("-5");
        Button bt_borrar_recs = new Button("Borrar las recomendaciones");
        hboxNumRecs.getChildren().addAll(num_rec, bt_menos5, bt_mas5, bt_borrar_recs);

        VBox vboxRecomendaciones = new VBox();
        Label lab_recomendado = new Label("TE RECOMENDAMOS ESTAS CANCIONES");
        ListView<String> listaRecomendada =  new ListView<>();
        vboxRecomendaciones.getChildren().addAll(lab_recomendado, listaRecomendada);

        VBox vboxDer = new VBox();
        vboxDer.getChildren().addAll(hboxUnionRecYOpcion, hboxNumRecs, vboxRecomendaciones);
        vboxDer.setAlignment(Pos.CENTER_RIGHT);

        HBox hboxEscena = new HBox();
        hboxEscena.getChildren().addAll(vboxIzquierda, vboxDer);
        hboxEscena.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(hboxEscena, 1000, 700));

        stage.setTitle("JavaFXApp"); // Título de ventana
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
