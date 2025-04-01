package org.example.CalculoDistancias;

import org.example.Interfaces.Distance;

import java.util.List;

public class EuclideanDistance implements Distance {

    public double calculateDistance(List<Double> p, List<Double> q) {
        double res = 0.0;

        for (int i = 0; i < p.size(); i++){
            res += Math.pow(q.get(i) - p.get(i), 2);
        }
        return Math.sqrt(res);
    }
}
