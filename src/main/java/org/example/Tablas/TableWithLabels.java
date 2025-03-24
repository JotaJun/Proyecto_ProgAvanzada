package org.example.Tablas;

import java.util.HashMap;

public class TableWithLabels extends Table {
    private HashMap<String, Integer> labelsToIndex = new HashMap<>();

    private int tam = 0;

    public Integer getLabelAsInteger(String label) {
        if (labelsToIndex.containsKey(label))
            return labelsToIndex.get(label);
        return -1;

    }
    public void addIndex(String label){
        if (!labelsToIndex.containsKey(label)){
            labelsToIndex.put(label, tam);
            tam++;
        }
    }

    //sobrecarga
    public void addRow(RowWithLabel row){
        super.addRow(row);
        addIndex(row.getLabel());
    }


    @Override
    public RowWithLabel getRowAt(int row){
        if (row < 0 || row >= rows.size()) {
            throw new IndexOutOfBoundsException("El índice está fuera del rango.");
        }

        if (!(rows.get(row) instanceof RowWithLabel)) {
            throw new ClassCastException("La fila en la posición " + row + " no es de tipo RowWithLabel.");
        }

        return (RowWithLabel) rows.get(row);
    }


}
