package org.example.Lectores;

import org.example.Tablas.Row;
import org.example.Tablas.RowWithLabel;
import org.example.Tablas.Table;
import org.example.Tablas.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSV {

    public Table readTable(String fileName) { //CABECERA SIN CLASE
        Table t = new Table();

        try{
            String refFich = getClass().getClassLoader().getResource(fileName).getFile();

            Scanner sc = new Scanner(new File(refFich));


            if(sc.hasNextLine()) { //Asignar la cabecera a la tabla
                String line = sc.nextLine();
                List<String> headers = new ArrayList<>(Arrays.asList(line.split(",")));
                t.setHeaders(headers);
            }


            while (sc.hasNextLine()) { //Añadir filas a la tabla
                String line = sc.nextLine();
                String[] row = line.split(",");
                Row r = new Row(row);
                t.addRow(r);
            }
            sc.close();
        }
        catch(NullPointerException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return t;
    }

    public TableWithLabels readTableWithLabels(String fileName) {
        TableWithLabels t = new TableWithLabels();

        try{
            String refFich = getClass().getClassLoader().getResource(fileName).getFile();
            Scanner sc = new Scanner(new File(refFich));
            if(sc.hasNextLine()) { //Asignar la cabecera a la tabla
                String line = sc.nextLine();
                List<String> headers = new ArrayList<>(Arrays.asList(line.split(",")));
                headers.removeLast();
                t.setHeaders(headers);
            }


            while (sc.hasNextLine()) { //Añadir filas a la tabla
                String line = sc.nextLine();
                String[] row = line.split(",");
                String label = row[row.length - 1];

                RowWithLabel r = new RowWithLabel(row, label);
                t.addRow(r);
            }
            sc.close();
        }
        catch(NullPointerException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        return t;
    }


}
