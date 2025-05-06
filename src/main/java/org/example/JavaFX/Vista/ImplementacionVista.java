package org.example.JavaFX.Vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.JavaFX.Colores;
import org.example.JavaFX.Modelo.Modelo;
import org.example.JavaFX.Controlador.Controlador;

import java.io.FileNotFoundException;
import java.util.List;

public class ImplementacionVista implements  Vista{
    private Controlador controlador;
    private Modelo modelo;
    private Stage stage;
    private DropShadow shadow;
    private Spinner<Integer> num_rec;
    private ListView<String> listaCanciones;
    private ListView<String> listaRecomendada;
    private Button bt_obten_canc;
    private ToggleGroup TG_genero;
    private ComboBox<String> cbOpciones;


    public ImplementacionVista(Stage stage){
        this.stage = stage;
    }

    public void creaGUI() {
        crearSombra();

        VBox VBoxIzq = crearVBoxIzq();

        VBox VBoxGenero = crearVBoxGenero();

        VBox VBoxOpciones = crearVBoxOpciones();

        HBox HBoxUnionGeneroYOpciones = crearUnionGeneroYOpciones(VBoxGenero, VBoxOpciones);

        VBox VBoxNUmRecs = crearVBoxNumRecs();

        VBox VBoxRecomendaciones = crearVBoxRecomendaciones();

        VBox VBoxDer = crearVBoxDerecha(HBoxUnionGeneroYOpciones, VBoxNUmRecs, VBoxRecomendaciones);

        BorderPane esquemaEscena = crearEsquemaEscena(VBoxIzq, VBoxDer);

        stage.setScene(new Scene(esquemaEscena));

        stage.setTitle("Recomendador de canciones"); // Título de ventana

        stage.setResizable(false);

        stage.show();
    }

    private void crearSombra(){
        shadow = new DropShadow();
        shadow.setOffsetX(4);
        shadow.setOffsetY(4);
        shadow.setColor(Colores.GRIS.getColor());
        shadow.setRadius(10);
    }

    private VBox crearVBoxIzq() {
        VBox vboxIzquierda = new VBox(); // Contenedor principal
        vboxIzquierda.setPadding(new Insets(20,20,20,20));
        Label lab_elige_canc = new Label("ELIGE LA CANCIÓN QUE TE GUSTE");
        bt_obten_canc = new Button("OBTENER RECOMENDACIONES");
        bt_obten_canc.setDisable(true);
        bt_obten_canc.setStyle("-fx-background-color: " + Colores.AMARILLO_SUAVE.getCode() + ";");
        bt_obten_canc.setEffect(shadow);
        bt_obten_canc.setOnAction(e -> solicitarNuevasRecomendaciones());

        listaCanciones = new ListView<>();
        vboxIzquierda.setSpacing(5);
        vboxIzquierda.setStyle("-fx-background-color: " + Colores.CELESTE_PASTEL.getCode() + ";");
        vboxIzquierda.getChildren().addAll(lab_elige_canc, listaCanciones, bt_obten_canc); // Añade elementos al contenedor
        listaCanciones.setOnMouseClicked(event -> bt_obten_canc.setDisable(false)); ///CAMBIAR
        mostrarListaCanciones();

        return vboxIzquierda;
    }

    private VBox crearVBoxGenero(){
        VBox vboxGenero = new VBox();
        Label lab_genero = new Label("RECOMENDAR EN BASE A");
        RadioButton rb_genero = new RadioButton("Genero");
        RadioButton rb_simil = new RadioButton("Similitudes");

        TG_genero = new ToggleGroup();
        rb_genero.setToggleGroup(TG_genero);
        rb_simil.setToggleGroup(TG_genero);
        vboxGenero.getChildren().addAll(lab_genero,rb_genero, rb_simil);

        return vboxGenero;
    }

    private VBox crearVBoxOpciones(){
        VBox vboxOpciones = new VBox();
        Label lab_opciones = new Label("OPCIÓN EXTRA");
        Label lab_elige_metrica = new Label("Elige la métrica");
        cbOpciones = new ComboBox<>();
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

    private VBox crearVBoxNumRecs(){
        HBox hboxNumRecs = new HBox();
        num_rec = new Spinner<>(1, 50, 0); //límites núm rec
        configurarSpinner(num_rec);

        Button bt_mas5 = new Button("+5");
        bt_mas5.setOnAction(actionEvent -> pulsarBoton5(1));

        Button bt_menos5 = new Button("-5");
        bt_menos5.setOnAction(actionEvent -> pulsarBoton5(-1));

        Button bt_borrar_recs = new Button("BORRAR LAS RECOMENDACIONES");
        bt_borrar_recs.setEffect(shadow);
        bt_borrar_recs.setStyle("-fx-background-color: " + Colores.AMARILLO_SUAVE.getCode() + ";");
        bt_borrar_recs.setEffect(shadow);
        bt_borrar_recs.setOnAction(actionEvenet -> borrarRecomendaciones());

        hboxNumRecs.setSpacing(5);
        hboxNumRecs.getChildren().addAll(num_rec, bt_menos5, bt_mas5, bt_borrar_recs);

        Label lb_numrec = new Label("NÚMERO DE RECOMENDACIONES");
        VBox VBoxNumRecs = new VBox();
        VBoxNumRecs.setSpacing(5);
        VBoxNumRecs.getChildren().addAll(lb_numrec, hboxNumRecs);


        return VBoxNumRecs;

    }

    private void configurarSpinner(Spinner<Integer> spinner){
        spinner.setEditable(true);

        spinner.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
                spinner.getEditor().setText(oldValue);

        });

    }

    private VBox crearVBoxRecomendaciones(){
        VBox vboxRecomendaciones = new VBox();
        Label lab_recomendado = new Label("TE RECOMENDAMOS ESTAS CANCIONES");

        listaRecomendada =  new ListView<>();
        listaRecomendada.setSelectionModel(null);
        vboxRecomendaciones.setSpacing(5);
        vboxRecomendaciones.getChildren().addAll(lab_recomendado, listaRecomendada);

        return vboxRecomendaciones;
    }

    private VBox crearVBoxDerecha(HBox hboxUnionRecYOpcion, VBox vboxNumRecs, VBox vboxRecomendaciones){
        VBox vboxDer = new VBox();
        vboxDer.setPadding(new Insets(20,20,20,20));
        vboxDer.setSpacing(20);

        vboxDer.getChildren().addAll(hboxUnionRecYOpcion, vboxNumRecs, vboxRecomendaciones);
        vboxDer.setStyle("-fx-background-color: " + Colores.ROSA_PASTEL.getCode() + ";");

        return vboxDer;
    }

    private BorderPane crearEsquemaEscena(VBox VBoxIzq, VBox VBoxDer){
        BorderPane escenaBorderPane = new BorderPane();

        escenaBorderPane.setLeft(VBoxIzq);
        escenaBorderPane.setRight(VBoxDer);

        return escenaBorderPane;
    }

    @Override
    public void setModelo(Modelo modelo){
        this.modelo = modelo;
    }

    @Override
    public void setControlador(Controlador controlador){
        this.controlador = controlador;
    }

    @Override
    public void solicitarNuevasRecomendaciones() {
        controlador.solicitarDatosRecomendar();
    }

    @Override
    public void mostrarNuevasRecomendaciones(List<String> cancionesRecomendadas){
        listaRecomendada.getItems().addAll(cancionesRecomendadas);
    }



    private void pulsarBoton5(int i){
        num_rec.increment(5*i);
    }
    @Override
    public void borrarRecomendaciones(){ //Se hace aquí dentro de la vista también, no hará falta llamar al controlador
        listaRecomendada.getItems().clear();
        bt_obten_canc.setDisable(true);
    }

    @Override
    public String getCancionSeleccionada(){
        return listaCanciones.getSelectionModel().getSelectedItem();
    }

    @Override
    public String getAlgoritmo(){
        Toggle temp = TG_genero.getSelectedToggle();
        if (temp == null) {
            crearPopUpError("Seleccione un género");
            return null;
        }
        RadioButton selectedRadioButton = (RadioButton) temp;
        String selectedText = selectedRadioButton.getText();
        return selectedText;
    }

    @Override
    public String getDistancia(){
        String distancia = cbOpciones.getValue();
        if(distancia == null){
            crearPopUpError("Seleccione un distancia");
        }
        return distancia;
    }

    @Override
    public int getCantidadRecomendaciones(){
        return num_rec.getValue();
    }

    private void crearPopUpError(String mensaje){
        Stage ventanaError = new Stage();
        ventanaError.setTitle("Error");
        ventanaError.setResizable(false);

        VBox VBoxError = new VBox();
        Label labelError = new Label(mensaje);
        Button botonError = new Button("Cerrar ventana");
        botonError.setOnAction(actionEvent -> ventanaError.close());
        VBoxError.getChildren().addAll(labelError, botonError);

        VBoxError.setAlignment(Pos.CENTER);
        VBoxError.setSpacing(5);
        ventanaError.setScene(new Scene(VBoxError, 200, 100));
        ventanaError.show();
    }

    @Override
    public void mostrarListaCanciones() {
        listaCanciones.getItems().addAll(modelo.getListaCanciones());
    }

}
