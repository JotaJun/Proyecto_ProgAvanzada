package org.example.Tablas;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<String> headers = new ArrayList<>();
    protected List<Row> rows = new ArrayList<>();

    public Table() {
        this.headers = new ArrayList<>();
        this.rows = new ArrayList<>();
    }


    public List<Row> getRows() {
        return rows;
    }

    public Row getRowAt(int row) {

        if (row < 0 || row >= rows.size()) {
            throw new IndexOutOfBoundsException("El índice está fuera del rango.");
        }
        
        return rows.get(row);
    }
    public int getSize(){
        return rows.size();
    }

    public int getRowCount() {
        return rows.size();
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<Double> getColumnAt(int column) {

        int tamFila = rows.get(0).getData().size();

        if (column >= tamFila || column < 0 ) {
            throw new IndexOutOfBoundsException();
        }

        List<Double> res = new ArrayList<>();
        for(Row row : rows){
            res.add(row.getData().get(column));
        }
        return res;
    }

    public void setHeaders(List<String> headers){
        this.headers = headers;
    }



    public void addRow(Row row){
        this.rows.add(row);
    }

    public boolean isEmpty(){
        return rows.isEmpty();
    }

    public int getSizeRow(){
        if (!isEmpty())
            return rows.get(0).getData().size();
        return -1;
    }


}
