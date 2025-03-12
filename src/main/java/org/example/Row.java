package org.example;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Double> data;

    public Row(List<Double> data) {
        this.data = data;
    }


    public Row(String[] data){
        this.data = conversorADoble(data);
    }

    public List<Double> getData(){
        return data;
    }



    protected List<Double> conversorADoble(String[] data){

        List<Double> d = new ArrayList<>();
        for(int i = 0; i < data.length; i++){
            d.add(Double.parseDouble(data[i]));
        }
        return d;
    }
}
