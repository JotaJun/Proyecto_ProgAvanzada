package org.example.Interfaces;

import org.example.Excepciones.InvalidClusterNumberException;

public interface Algorithm<T,W> {
    void train(T data) throws InvalidClusterNumberException;
    Integer estimate(W data);
}
