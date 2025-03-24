package org.example.Interfaces;

import org.example.Excepciones.InvalidClusterNumberException;

public interface Algorithm<T,W> {
    public void train(T data) throws InvalidClusterNumberException;
    public Integer estimate(W data);
}
