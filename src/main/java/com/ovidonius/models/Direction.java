package com.ovidonius.models;

import com.ovidonius.graph.PathFinder;
import com.ovidonius.models.enums.StationType;
import lombok.Getter;

import java.util.List;

@Getter
public class Direction {
    private List<StationType> path;
    private int time;
    private int distance;

    public Direction(String startName, String endName) {
        StationType start = StationType.getStationTypeByName(startName);
        StationType end = StationType.getStationTypeByName(endName);

        if (start != null && end != null) {
            System.out.println("Start: " + start + " End: " + end);
            path = PathFinder.findPath(start, end);
            System.out.println("Path: " + path);
            if (path != null && !path.isEmpty()) {
                distance = path.size() - 1;
                time = (int) (distance * 1.75); // Ожидаемое время в пути
            } else {
                distance = 0;
                time = 0;
            }
        } else {
            path = null;
            distance = 0;
            time = 0;
        }
    }

    // Возвращаем путь как список станций
    public List<StationType> getPath() {
        return path;
    }

    // Возвращаем время в пути
    public int getTime() {
        return time;
    }

    // Возвращаем дистанцию
    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "path=" + path +
                ", time=" + time +
                ", distance=" + distance +
                '}';
    }
}
