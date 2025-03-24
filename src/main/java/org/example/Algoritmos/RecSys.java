package org.example.Algoritmos;

import org.example.Excepciones.InvalidClusterNumberException;
import org.example.Excepciones.LikedItemNotFoundException;
import org.example.Interfaces.Algorithm;
import org.example.Tablas.Table;

import java.util.*;

public class RecSys {
    private Algorithm algorithm;
    private Map<String, Integer> cancionesAGrupo;
    private Map<Integer, List<String>> grupoACanciones;
    public RecSys(Algorithm algorithm) {
        this.algorithm = algorithm;
        this.cancionesAGrupo = new HashMap<>();
        this.grupoACanciones = new HashMap<>();
    }

    public void train(Table trainData) throws InvalidClusterNumberException {
        algorithm.train(trainData);
    }

    public void initialise(Table testData, List<String> testItems) throws Exception {

        if (testItems.size() != testData.getSize()) throw new Exception("No coinciden los lengths");

        for(int i = 0; i < testItems.size(); i++){
            int grupo = algorithm.estimate(testData.getRowAt(i).getData());
            String cancion = testItems.get(i);

            cancionesAGrupo.put(cancion, grupo);

            if (!grupoACanciones.containsKey(grupo))
                grupoACanciones.put(grupo, new ArrayList<>());

            grupoACanciones.get(grupo).add(cancion);
        }

    }

    public List<String> recommend(String nameLikedItem, int numRecommendations) throws LikedItemNotFoundException {
        if(!cancionesAGrupo.containsKey(nameLikedItem)) throw new LikedItemNotFoundException(nameLikedItem + " not found");

        int grupo = cancionesAGrupo.get(nameLikedItem);

        List<String> recommendedSongs = new ArrayList<>();
        List<String> recommendedItems = grupoACanciones.get(grupo);

        int i = 0;
        while(recommendedSongs.size() < numRecommendations && i < recommendedItems.size()){
            String recommendedSong = recommendedItems.get(i);
            if (!recommendedSong.equals(nameLikedItem))
                recommendedSongs.add(recommendedSong);
            i++;

        }

        return recommendedSongs;
    }
}
