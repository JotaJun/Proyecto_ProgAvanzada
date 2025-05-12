package org.example.JavaFX.Modelo;

import org.example.Algoritmos.*;
import org.example.CalculoDistancias.EuclideanDistance;
import org.example.CalculoDistancias.ManhattanDistance;
import org.example.Excepciones.LikedItemNotFoundException;
import org.example.Interfaces.Algorithm;
import org.example.Interfaces.Distance;
import org.example.JavaFX.Vista.Vista;
import org.example.Lectores.CSV;
import org.example.Tablas.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ImplementacionModelo implements  Modelo{
    private Vista vista;

    private RecSys recSys;
    private String sep = "/";
    private String ruta = "recommender";
    private Map<String,String> filenames;
    Map<String, Algorithm> algorithms = new HashMap<>();
    Map<String, Table> tables;
    Map<String, Distance> distances;

    public ImplementacionModelo() {
        inicializarFileNames();
        inicializarTables();
        inicializarDistancias();
    }

    private void inicializarFileNames(){
        filenames = new HashMap<>();
        filenames.put("knn"+"train",ruta+sep+"songs_train.csv");
        filenames.put("knn"+"test",ruta+sep+"songs_test.csv");
        filenames.put("kmeans"+"train",ruta+sep+"songs_train_withoutnames.csv");
        filenames.put("kmeans"+"test",ruta+sep+"songs_test_withoutnames.csv");

    }

    private void inicializarDistancias(){
        distances = new HashMap<>();
        distances.put("Manhattan", new ManhattanDistance());
        distances.put("Euclidean", new EuclideanDistance());
    }

    private void asignarDistanciaAlgoritmos(String distancia){
        algorithms.put("knn",new KNN(distances.get(distancia)));
        algorithms.put("kmeans",new KMeans(15, 200, 4321, distances.get(distancia)));
    }

    private void inicializarTables(){
        tables = new HashMap<>();
        String [] stages = {"train", "test"};
        CSV csv = new CSV();
        for (String stage : stages) {
            tables.put("knn" + stage, csv.readTableWithLabels(filenames.get("knn" + stage)));
            tables.put("kmeans" + stage, csv.readTable(filenames.get("kmeans" + stage)));
        }
    }


    @Override
    public void setVista(Vista v) {
        vista = v;
    }

    @Override
    public void recomendarCanciones(String cancionSeleccionada, String algoritmo, String distancia, int numeroRecomendaciones) {
        List<String> names = leerFicheroNombres();

        algoritmo = traducirAlgoritmo(algoritmo);

        asignarDistanciaAlgoritmos(distancia);

        utilizarRecSys(algoritmo, names);

        try {
            List<String> cancionesRecomendadas = recSys.recommend(cancionSeleccionada, numeroRecomendaciones);
            vista.mostrarNuevasRecomendaciones(cancionesRecomendadas);
        }
        catch (LikedItemNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String traducirAlgoritmo(String alg){
        return switch (alg) {
            case "Genero" -> "knn";
            case "Similitudes" -> "kmeans";
            default -> null;
        };
    }

    private void utilizarRecSys(String alg, List<String> names){
        try{
            recSys = new RecSys(algorithms.get(alg));
            recSys.train(tables.get(alg + "train"));
            recSys.initialise(tables.get(alg+"test"), names);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public List<String> getListaCanciones() {
        return leerFicheroNombres();
    }

    private List<String> leerFicheroNombres() {

        String path = getClass().getClassLoader().getResource(ruta + sep + "songs_test_names.csv").getPath();
        List<String> names = new ArrayList<>();

        try{
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                names.add(scanner.nextLine());
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("No se encontro el fichero");
        }
        return names;
    }

}
