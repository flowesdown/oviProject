package com.ovidonius.graph;

import com.ovidonius.models.enums.StationType;

import java.util.List;

import static com.ovidonius.models.enums.StationType.*;

public class CityGraph {

    // Метод для инициализации соседей
    public static void init() {
        // Устанавливаем соседей для каждой станции
        StationType.BUCHAREST.setNeighbors(PITESTI, CRAIOVA, PLOIESTI, CONSTANTA);
        StationType.PITESTI.setNeighbors(BUCHAREST, CRAIOVA, SIBIU);
        StationType.CRAIOVA.setNeighbors(BUCHAREST, PITESTI, TIMISOARA);
        StationType.TIMISOARA.setNeighbors(CRAIOVA, ARAD);
        StationType.ARAD.setNeighbors(TIMISOARA, ORADEA);
        StationType.ORADEA.setNeighbors(ARAD, CLUJ_NAPOCA);
        StationType.CLUJ_NAPOCA.setNeighbors(ORADEA, TARGU_MURES);
        StationType.TARGU_MURES.setNeighbors(CLUJ_NAPOCA, SIBIU, IASI);
        StationType.SIBIU.setNeighbors(TARGU_MURES, PITESTI, BRASOV);
        StationType.BRASOV.setNeighbors(SIBIU, PLOIESTI);
        StationType.PLOIESTI.setNeighbors(BUCHAREST, BRASOV);
        StationType.CONSTANTA.setNeighbors(BUCHAREST);
        StationType.IASI.setNeighbors(TARGU_MURES, GALATI);
        StationType.GALATI.setNeighbors(IASI);
    }
}
