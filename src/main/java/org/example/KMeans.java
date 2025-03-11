package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans implements Algorithm<Table,Integer, List<Double>> {
    private class InvalidClusterNumberException extends Exception {
        public InvalidClusterNumberException(String message) {
            super(message);
        }
    }

    private int numClusters;
    private int numIteraciones;
    private long seed;
    private List<List<Double>> clusters; // x

    public KMeans(int numClusters, int numIteraciones, long seed){
        this.numClusters = numClusters;
        this.numIteraciones = numIteraciones;
        this.seed = seed;
        this.clusters = new ArrayList<List<Double>>();
    }


    public void train(Table data) throws InvalidClusterNumberException {
        Random rand = new Random(seed);
        if(numClusters <= 0 || numClusters >= data.getSize()){
            throw new InvalidClusterNumberException("El número de grupos debe estar entre 1 y "+data.getSize());
        }
        int i = 0;
        while(i < numClusters){ //Clusters seleccionados
            Row aux = data.getRowAt(rand.nextInt(data.getSize()));
            if(!clusters.contains(aux)){
                clusters.add(new ArrayList<>(aux.getData()));
                i++;
            }
        }


        int iteraciones = 0;
        int NOmodificados = 0;
        while(iteraciones < numIteraciones && NOmodificados != numClusters) {
            Double[][] vector = new Double[numClusters][data.getSizeRow()+1]; //La última posición será cuantos puntos(filas) he añadido a la suma
            NOmodificados = 0;
            for (Row row : data.rows) {
                int grupo = estimate(row.getData());
                for (int j = 0; j < vector[0].length - 1; j++) {
                    vector[grupo][j] += row.getData().get(j);
                }
                vector[grupo][vector[0].length - 1]++; //Incrementar el contador de puntos añadidos al grupo
            }
            for(int k = 0; k < vector.length; k++){
                if (!recalcularCentroide(vector[k], k))
                    NOmodificados++;

            }
            iteraciones++;
        }

    }


    public Integer estimate(List<Double> sample) {
        int cerca = 0;
        Double distanciaMinima = CalcularDistancia(sample, clusters.get(0));
        for(int i = 1; i < clusters.size(); i++){
            Double nuevaDistancia = CalcularDistancia(sample, clusters.get(i));
            if (distanciaMinima > nuevaDistancia){
                distanciaMinima = nuevaDistancia;
                cerca = i;
            }
        }

        return cerca;
    }

    private boolean recalcularCentroide(Double[] sample, int grupo){
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

    private Double CalcularDistancia(List<Double> sample, List<Double> cluster){
        double res = 0.0;

        for (int i = 0; i < sample.size(); i++){
            res += Math.pow(cluster.get(i) - sample.get(i), 2);
        }
        return Math.sqrt(res);
    }




}
