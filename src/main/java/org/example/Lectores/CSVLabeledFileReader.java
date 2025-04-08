package org.example.Lectores;


import org.example.Tablas.RowWithLabel;
import org.example.Tablas.TableWithLabels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVLabeledFileReader extends FileReader<TableWithLabels> {


    public CSVLabeledFileReader(String fileName) {
        super(fileName);
        this.t = new TableWithLabels();
    }

    @Override
    void processHeaders(String headers) {
        List<String> listaHeaders = new ArrayList<>(Arrays.asList(headers.split(",")));
        listaHeaders.removeLast();
        t.setHeaders(listaHeaders);
    }

    @Override
    void processData(String data) {
        String[] row = data.split(",");
        String label = row[row.length - 1];

        RowWithLabel r = new RowWithLabel(row, label);
        t.addRow(r);
    }
}
