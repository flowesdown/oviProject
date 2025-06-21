package com.ovidonius.models.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public enum StationType {
    BUCHAREST("Bucharest"),
    CLUJ_NAPOCA("Cluj-Napoca"),
    TIMISOARA("Timisoara"),
    IASI("Iasi"),
    CONSTANTA("Constanta"),
    BRASOV("Brasov"),
    CRAIOVA("Craiova"),
    GALATI("Galați"),
    PLOIESTI("Ploiești"),
    ARAD("Arad"),
    TARGU_MURES("Targu Mureș"),
    SIBIU("Sibiu"),
    ORADEA("Oradea"),
    PITESTI("Pitești");

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

    // Метод для получения StationType по названию города (без учета регистра)
    public static StationType getStationTypeByName(String name) {
        for (StationType station : StationType.values()) {
            if (station.cityName.equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null; // Возвращаем null, если не нашли соответствующий город
    }
}
