package org.example.Lectores;

import org.example.Tablas.Row;
import org.example.Tablas.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUnlabeledFileReader extends FileReader<Table> {

    public CSVUnlabeledFileReader(String fileName) {
        super(fileName);
        t = new Table();
    }

    @Override
    void processHeaders(String headers) {
        //Asignar la cabecera a la tabla
        List<String> listaHeaders = new ArrayList<>(Arrays.asList(headers.split(",")));
        t.setHeaders(listaHeaders);
    }

    @Override
    void processData(String data) {
        String[] row = data.split(",");
        Row r = new Row(row);
        t.addRow(r);
    }


}
