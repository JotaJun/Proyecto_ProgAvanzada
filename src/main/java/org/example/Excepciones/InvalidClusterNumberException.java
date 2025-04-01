package org.example.Excepciones;

public class InvalidClusterNumberException extends Exception{
    private int clusterNumber;
    public InvalidClusterNumberException(String message) {
        super(message);
    }
    public InvalidClusterNumberException(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    public int getClusterNumber() {
        return clusterNumber;
    }
}
