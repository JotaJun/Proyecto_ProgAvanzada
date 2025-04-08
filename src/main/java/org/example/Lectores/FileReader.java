package org.example.Lectores;

import org.example.Tablas.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract public class FileReader<T extends Table> extends ReaderTemplate<T> {
    private Scanner sc;


    public FileReader(String fileName) {
        super(fileName);
    }

    @Override
    void openSource(String source) {
        try {
            String refFich = getClass().getClassLoader().getResource(source).getFile();
            sc = new Scanner(new File(refFich));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    void closeSource() {
        sc.close();
    }

    @Override
    boolean hasMoreData() {
        if(sc.hasNextLine()) {
            return true;
        }
        return false;
    }

    @Override
    String getNextData() {
        return sc.nextLine();
    }
}
