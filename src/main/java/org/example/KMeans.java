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
    private List<Row> clusters; // x

    public KMeans(int numClusters, int numIteraciones, long seed){
        this.numClusters = numClusters;
        this.numIteraciones = numIteraciones;
        this.seed = seed;
        this.clusters = new ArrayList<Row>();
    }

    @Override
    public void train(Table data) throws InvalidClusterNumberException {
        Random rand = new Random(seed);
        if(numClusters <= 0 || numClusters >= data.getSize()){
            throw new InvalidClusterNumberException("El número de grupos debe estar entre 1 y "+data.getSize());
        }
        int i = 0;
        while(i < numClusters){ //Clusters seleccionados
            Row aux = data.getRowAt(rand.nextInt(data.getSize()));
            if(!clusters.contains(aux)){
                clusters.add(aux);
                i++;
            }
        }

    }


    @Override
    public Integer estimate(List<Double> sample) {
        return null;
    }


}
