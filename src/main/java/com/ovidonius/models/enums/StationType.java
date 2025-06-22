package com.ovidonius.models.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public enum StationType {
    BUCHAREST("Bucharest"),
    CLUJNAPOCA("ClujNapoca"),
    TIMISOARA("Timisoara"),
    IASI("Iasi"),
    CONSTANTA("Constanta"),
    BRASOV("Brasov"),
    CRAIOVA("Craiova"),
    GALATI("Galati"),
    PLOIESTI("Ploiesti"),
    ARAD("Arad"),
    TARGUMURES("TarguMures"),
    SIBIU("Sibiu"),
    ORADEA("Oradea"),
    PITESTI("Pitesti");

    private final String cityName;
    private List<StationType> neighbors;

    StationType(String cityName) {
        this.cityName = cityName;
    }

    public List<StationType> getNeighbors() {
        return neighbors != null ? neighbors : Collections.emptyList();
    }

    public void setNeighbors(StationType... neighbors) {
        this.neighbors = Arrays.asList(neighbors);
    }

    public static StationType getStationTypeByName(String name) {
        for (StationType station : StationType.values()) {
            if (station.cityName.equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null;
    }
}
