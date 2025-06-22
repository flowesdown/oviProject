package com.ovidonius.graph;

import static com.ovidonius.models.enums.StationType.*;

public class CityGraph {

    public static void init() {
        BUCHAREST.setNeighbors(PITESTI, CRAIOVA, PLOIESTI, CONSTANTA, TARGUMURES);
        PITESTI.setNeighbors(BUCHAREST, CRAIOVA, SIBIU);
        CRAIOVA.setNeighbors(BUCHAREST, PITESTI, TIMISOARA);
        TIMISOARA.setNeighbors(CRAIOVA, ARAD);
        ARAD.setNeighbors(TIMISOARA, ORADEA);
        ORADEA.setNeighbors(ARAD, CLUJNAPOCA);
        CLUJNAPOCA.setNeighbors(ORADEA, TARGUMURES);
        TARGUMURES.setNeighbors(CLUJNAPOCA, SIBIU, IASI);
        SIBIU.setNeighbors(TARGUMURES, PITESTI, BRASOV);
        BRASOV.setNeighbors(SIBIU, PLOIESTI);
        PLOIESTI.setNeighbors(BUCHAREST, BRASOV);
        CONSTANTA.setNeighbors(BUCHAREST);
        IASI.setNeighbors(TARGUMURES, GALATI);
        GALATI.setNeighbors(IASI);
    }
}
