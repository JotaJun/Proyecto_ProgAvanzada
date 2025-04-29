package org.example.JavaFX.Vista;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.JavaFX.Colores;
import org.example.JavaFX.Modelo.Modelo;
import org.example.JavaFX.Controlador.Controlador;

public class ImplementacionVista {
    private Controlador controlador;
    private Modelo modelo;
    private Stage stage;
    private DropShadow shadow;

    public ImplementacionVista(Stage stage){
        this.stage = stage;
    }

    public void creaGUI(){
        crearSombra();

        VBox VBoxIzq = crearVBoxIzq();

        VBox VBoxGenero = crearVBoxGenero();

        VBox VBoxOpciones = crearVBoxOpciones();

        HBox HBoxUnionGeneroYOpciones = crearUnionGeneroYOpciones(VBoxGenero, VBoxOpciones);

        HBox HBoxNUmRecs = crearHBoxNumRecs();

        VBox VBoxRecomendaciones = crearVBoxRecomendaciones();

        VBox VBoxDer = crearVBoxDerecha(HBoxUnionGeneroYOpciones, HBoxNUmRecs, VBoxRecomendaciones);

        BorderPane esquemaEscena = crearEsquemaEscena(VBoxIzq, VBoxDer);

        stage.setScene(new Scene(esquemaEscena));

        stage.setTitle("Recomendador de canciones"); // Título de ventana

        stage.setResizable(false);

        stage.show();
    }

    private void crearSombra(){
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(4);
        shadow.setOffsetY(4);
        shadow.setColor(Colores.GRIS.getColor());
        shadow.setRadius(10);
    }

    private VBox crearVBoxIzq(){
        VBox vboxIzquierda = new VBox(); // Contenedor principal
        vboxIzquierda.setPadding(new Insets(20,20,20,20));
        Label lab_elige_canc = new Label("ELIGE LA CANCIÓN QUE TE GUSTE"); // Texto no editable
        Button bt_obten_canc = new Button("OBTENER RECOMENDACIONES"); // Un botón, no hace nada
        bt_obten_canc.setDisable(true);
        bt_obten_canc.setStyle("-fx-background-color: " + Colores.AMARILLO_SUAVE.getCode() + ";"
                + "-fx-border-color: " + Colores.NEGRO.getCode() + ";"
                + "-fx-border-width: 0.5px;");
        bt_obten_canc.setEffect(shadow);

        ListView<String> listaCanciones = new ListView<>();
        listaCanciones.getItems().add("Canciones del recomendador");
        vboxIzquierda.setSpacing(5);
        vboxIzquierda.setStyle("-fx-background-color: " + Colores.CELESTE_PASTEL.getCode() + ";");
        vboxIzquierda.getChildren().addAll(lab_elige_canc, listaCanciones, bt_obten_canc); // Añade elementos al contenedor
        listaCanciones.setOnMouseClicked(event -> bt_obten_canc.setDisable(false));

        return vboxIzquierda;
    }

    private VBox crearVBoxGenero(){
        VBox vboxGenero = new VBox();
        Label lab_genero = new Label("RECOMENDAR EN BASE A");
        RadioButton rb_genero = new RadioButton("Genero");
        RadioButton rb_simil = new RadioButton("Similitudes");

        ToggleGroup TG_genero = new ToggleGroup();
        rb_genero.setToggleGroup(TG_genero);
        rb_simil.setToggleGroup(TG_genero);
        vboxGenero.getChildren().addAll(lab_genero,rb_genero, rb_simil);
        return vboxGenero;
    }

    private VBox crearVBoxOpciones(){
        VBox vboxOpciones = new VBox();
        Label lab_opciones = new Label("OPCIÓN EXTRA");
        Label lab_elige_metrica = new Label("Elige la métrica");
        ComboBox<String> cbOpciones = new ComboBox<>();
        cbOpciones.getItems().add("Manhattan");
        cbOpciones.getItems().add("Euclidean");
        vboxOpciones.getChildren().addAll(lab_opciones, lab_elige_metrica,cbOpciones);

        return vboxOpciones;
    }

    private HBox crearUnionGeneroYOpciones(VBox VBoxGenero, VBox VBoxOpciones){
        HBox hboxUnionGeneroYOpciones = new HBox();
        hboxUnionGeneroYOpciones.setSpacing(70);
        hboxUnionGeneroYOpciones.getChildren().addAll(VBoxGenero, VBoxOpciones);

        return hboxUnionGeneroYOpciones;
    }

    private HBox crearHBoxNumRecs(){
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

        return hboxNumRecs;

    }

    private VBox crearVBoxRecomendaciones(){
        VBox vboxRecomendaciones = new VBox();
        Label lab_recomendado = new Label("TE RECOMENDAMOS ESTAS CANCIONES");

        ListView<String> listaRecomendada =  new ListView<>();
        vboxRecomendaciones.setSpacing(5);
        vboxRecomendaciones.getChildren().addAll(lab_recomendado, listaRecomendada);

        return vboxRecomendaciones;
    }

    private VBox crearVBoxDerecha(HBox hboxUnionRecYOpcion, HBox hboxNumRecs, VBox vboxRecomendaciones){
        VBox vboxDer = new VBox();
        vboxDer.setPadding(new Insets(20,20,20,20));
        vboxDer.setSpacing(20);
        vboxDer.getChildren().addAll(hboxUnionRecYOpcion, hboxNumRecs, vboxRecomendaciones);
        vboxDer.setStyle("-fx-background-color: " + Colores.ROSA_PASTEL.getCode() + ";");

        return vboxDer;


    }

    private BorderPane crearEsquemaEscena(VBox VBoxIzq, VBox VBoxDer){
        BorderPane escenaBorderPane = new BorderPane();

        escenaBorderPane.setLeft(VBoxIzq);
        escenaBorderPane.setRight(VBoxDer);

        return escenaBorderPane;
    }


}
