package org.example.Interfaces;

import org.example.Excepciones.InvalidClusterNumberException;
import org.example.Tablas.Table;

public interface Algorithm<T extends Table,W> {
    void train(T data) throws InvalidClusterNumberException;
    Integer estimate(W data);
}
