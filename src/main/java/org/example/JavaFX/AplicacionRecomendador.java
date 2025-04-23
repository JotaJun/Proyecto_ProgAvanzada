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




public class AplicacionRecomendador extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VBox vboxIzquierda = new VBox(); // Contenedor principal
        vboxIzquierda.setPadding(new Insets(20,20,20,20));
        Label lab_elige_canc = new Label("ELIGE LA CANCIÓN QUE TE GUSTE");
        Button bt_obten_canc = new Button("OBTENER RECOMENDACIONES");
        bt_obten_canc.setDisable(true);
        bt_obten_canc.setStyle("-fx-background-color: " + Colores.AMARILLO_SUAVE.getCode() + ";"
        + "-fx-border-color: " + Colores.NEGRO.getCode() + ";"
        + "-fx-border-width: 0.5px;");
        bt_obten_canc.setDisable(true);

        //Shadow
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(4);
        shadow.setOffsetY(4);
        shadow.setColor(Colores.GRIS.getColor());
        shadow.setRadius(10);
        bt_obten_canc.setEffect(shadow);
        //

        ListView<String> listaCanciones = new ListView();
        listaCanciones.getItems().add("Canciones del recomendador");
        vboxIzquierda.setSpacing(5);
        vboxIzquierda.setStyle("-fx-background-color: " + Colores.CELESTE_PASTEL.getCode() + ";");
        vboxIzquierda.getChildren().addAll(lab_elige_canc, listaCanciones, bt_obten_canc); // Añade elementos al contenedor
        listaCanciones.setOnMouseClicked(event -> bt_obten_canc.setDisable(false));
// Añade el contenedor a la escena, y la escena al escenario

        VBox vboxGenero = new VBox();
        Label lab_genero = new Label("RECOMENDAR EN BASE A");
        RadioButton rb_genero = new RadioButton("Genero");
        RadioButton rb_simil = new RadioButton("Similitudes");

        ToggleGroup TG_genero = new ToggleGroup();
        rb_genero.setToggleGroup(TG_genero);
        rb_simil.setToggleGroup(TG_genero);
        vboxGenero.getChildren().addAll(lab_genero,rb_genero, rb_simil);


        VBox vboxOpciones = new VBox();
        Label lab_opciones = new Label("OPCIÓN EXTRA");
        Label lab_elige_metrica = new Label("Elige la métrica");
        ComboBox cbOpciones = new ComboBox();
        cbOpciones.getItems().add("Manhattan");
        cbOpciones.getItems().add("Euclidean");
        vboxOpciones.getChildren().addAll(lab_opciones, lab_elige_metrica,cbOpciones);

        HBox hboxUnionRecYOpcion = new HBox();
        hboxUnionRecYOpcion.setSpacing(70);
        hboxUnionRecYOpcion.getChildren().addAll(vboxGenero, vboxOpciones);

        HBox hboxNumRecs = new HBox();
        Spinner<Integer> num_rec = new Spinner<>();
        num_rec.setEditable(true);
        Button bt_mas5 = new Button("+5");
        Button bt_menos5 = new Button("-5");
        Button bt_borrar_recs = new Button("Borrar las recomendaciones");
        bt_borrar_recs.setEffect(shadow);


        bt_borrar_recs.setStyle("-fx-background-color: " + Colores.AMARILLO_SUAVE.getCode() + ";");
        hboxNumRecs.setSpacing(5);
        hboxNumRecs.getChildren().addAll(num_rec, bt_menos5, bt_mas5, bt_borrar_recs);

        VBox vboxRecomendaciones = new VBox();
        Label lab_recomendado = new Label("TE RECOMENDAMOS ESTAS CANCIONES");

        ListView<String> listaRecomendada =  new ListView<>();
        vboxRecomendaciones.setSpacing(5);
        vboxRecomendaciones.getChildren().addAll(lab_recomendado, listaRecomendada);
        bt_obten_canc.setOnAction(event -> listaRecomendada.getItems().addAll("Cancion1","Cancion2","Cancion3","Cancion4","Cancion5"));
        bt_borrar_recs.setOnAction(event -> listaRecomendada.getItems().removeAll(listaRecomendada.getItems()));



        VBox vboxDer = new VBox();
        vboxDer.setPadding(new Insets(20,20,20,20));
        vboxDer.setSpacing(20);
        vboxDer.getChildren().addAll(hboxUnionRecYOpcion, hboxNumRecs, vboxRecomendaciones);
        vboxDer.setStyle("-fx-background-color: " + Colores.ROSA_PASTEL.getCode() + ";");
        BorderPane escenaBorderPane = new BorderPane();
        escenaBorderPane.setLeft(vboxIzquierda);
        escenaBorderPane.setRight(vboxDer);


        stage.setScene(new Scene(escenaBorderPane));

        stage.setTitle("Recomendador de canciones"); // Título de ventana
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
