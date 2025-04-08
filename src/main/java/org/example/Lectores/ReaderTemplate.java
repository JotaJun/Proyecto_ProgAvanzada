package org.example.Lectores;

import org.example.Tablas.Table;

public abstract class ReaderTemplate<T extends Table> {
    protected T t;
    private String fileName;

    public ReaderTemplate(String fileName) {
        this.fileName = fileName;
    }

    abstract void openSource(String source);
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData();
    abstract String getNextData();

    public final T readTableFromSource(){

        openSource(fileName);
        if (hasMoreData())
            processHeaders(getNextData());

        while(hasMoreData()){
            processData(getNextData());
        }
        closeSource();
        return t;
    }

}
