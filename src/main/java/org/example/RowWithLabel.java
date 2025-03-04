package org.example;

import java.util.ArrayList;
import java.util.List;

public class RowWithLabel extends Row {
    private String label;


    public RowWithLabel(List<Double> data, String label) {
        super(data);
        this.label = label;
    }

    public RowWithLabel(String[] data,String label) {
        super(data);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    @Override
    public List<Double> conversorADoble(String[] data){

        List<Double> d = new ArrayList<>();
        for(int i = 0; i < data.length - 1; i++){
            d.add(Double.parseDouble(data[i]));
        }

        return d;
    }


}
