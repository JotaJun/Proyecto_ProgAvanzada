package org.example;

import java.util.*;

public interface Algorithm<T,U,W> {
    public void train(T data) throws Exception;
    public U estimate(W data);
}
