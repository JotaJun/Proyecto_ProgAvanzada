package org.example.Algoritmos;

import org.example.Interfaces.Algorithm;
import org.example.Interfaces.Distance;
import org.example.Tablas.TableWithLabels;

import java.util.List;

public class KNN implements Algorithm<TableWithLabels,List<Double>> {
    private TableWithLabels table;
    private Distance distance;
    // lo haremos con --> k = 1;


    public KNN(Distance distance) {
        this.distance = distance;
    }
    public void train(TableWithLabels data){
        this.table = data;
    }

    public Integer estimate(List<Double> sample){

        if (sample.size() != table.getSizeRow())
            throw new IllegalArgumentException("El tamaño de la muestra no corresponde al de la tabla!");
        String labelMasCercana = table.getRowAt(0).getLabel();

        double distanciaMinima = distance.calculateDistance(sample, table.getRowAt(0).getData());

        for( int i = 1; i < table.getSize(); i++){
            double comparar = distance.calculateDistance(sample, table.getRowAt(i).getData());
            if (comparar < distanciaMinima){
                distanciaMinima = comparar;
                labelMasCercana = table.getRowAt(i).getLabel();
            }
        }

        return table.getLabelAsInteger(labelMasCercana);
    }

}
