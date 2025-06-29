package com.ovidonius.models;

import com.ovidonius.graph.PathFinder;
import com.ovidonius.models.enums.StationType;
import lombok.Getter;

import java.util.ArrayList;
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
            path = PathFinder.findPath(start, end);

            if (path != null && !path.isEmpty()) {
                distance = path.size() - 1;
                time = (int) (distance * 1.75);
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
    public Direction(Direction other) {
        this.path = other.path != null ? new ArrayList<>(other.path) : null;
        this.time = other.time;
        this.distance = other.distance;
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
