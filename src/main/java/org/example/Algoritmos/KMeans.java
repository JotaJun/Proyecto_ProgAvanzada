package org.example.Algoritmos;

import org.example.Excepciones.InvalidClusterNumberException;
import org.example.Interfaces.Algorithm;
import org.example.Interfaces.Distance;
import org.example.Tablas.Row;
import org.example.Tablas.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans implements Algorithm<Table, List<Double>> {

    private Distance distance;
    private int numClusters;
    private int numIteraciones;
    private long seed;
    private List<List<Double>> clusters; // x


    public KMeans(int numClusters, int numIteraciones, long seed, Distance distance) {
        this.numClusters = numClusters;
        this.numIteraciones = numIteraciones;
        this.seed = seed;
        this.clusters = new ArrayList<>();
        this.distance = distance;
    }


    public void train(Table data) throws InvalidClusterNumberException {
        if(numClusters <= 0 || numClusters >= data.getSize()){
            throw new InvalidClusterNumberException(numClusters);
        }

        seleccionarClusters(data); //asignamos clusters iniciales

        int iteraciones = 0;
        double[][] vector;

        do{
            vector = new double[numClusters][data.getSizeRow()+1]; //La última posición será cuantos puntos(filas) he añadido a la suma
            for (Row row : data.getRows()) {
                int grupo = estimate(row.getData());
                for (int j = 0; j < vector[0].length - 1; j++)
                    vector[grupo][j] += row.getData().get(j);

                vector[grupo][vector[0].length - 1]++; //Incrementar el numero de integrantes para el grupo al que pertenece
            }
            iteraciones++;
        }while(iteraciones < numIteraciones && modificarCentroides(vector));

    }

    private boolean modificarCentroides(double[][] v){ //modificar centroides y comprobar si no se han modificado
        int noModificados = 0;
        for(int k = 0; k < v.length; k++){
            if (!recalcularCentroide(v[k], k))
                noModificados++;

        }
        return noModificados != numClusters; //si es igual, significa que no se han modificado los centroides
    }

    public Integer estimate(List<Double> sample) {
        int cerca = 0;
        double distanciaMinima = distance.calculateDistance(sample, clusters.getFirst());
        for(int i = 1; i < clusters.size(); i++){
            double nuevaDistancia = distance.calculateDistance(sample, clusters.get(i));
            if (distanciaMinima > nuevaDistancia){
                distanciaMinima = nuevaDistancia;
                cerca = i;
            }
        }

        return cerca;
    }

    private boolean recalcularCentroide(double[] sample, int grupo){
        List<Double> centroid = new ArrayList<>();
        for(int i = 0; i < sample.length - 1; i++){
            centroid.add(sample[i]/sample[sample.length-1]);
        }
        if (centroid.equals(clusters.get(grupo))){
            return false;
        }
        clusters.set(grupo, centroid);
        return true;
    }

    private void seleccionarClusters(Table data){
        Random rand = new Random(seed);
        int i = 0;
        while(i < numClusters){
            Row aux = data.getRowAt(rand.nextInt(data.getSize()));
            if(!clusters.contains(aux)){
                clusters.add(new ArrayList<>(aux.getData()));
                i++;
            }
        }
    }

}
