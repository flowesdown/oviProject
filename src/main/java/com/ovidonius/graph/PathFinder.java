package com.ovidonius.graph;

import com.ovidonius.models.enums.StationType;

import java.util.*;

public class PathFinder {

    public static List<StationType> findPath(StationType start, StationType end) {
        if (start == end) return Collections.singletonList(start);

        Queue<StationType> queue = new LinkedList<>();
        Map<StationType, StationType> previous = new HashMap<>();
        Set<StationType> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            StationType current = queue.poll();

            for (StationType neighbor : current.getNeighbors()) {
                if (visited.contains(neighbor)) continue;

                previous.put(neighbor, current);

                if (neighbor == end) {
                    return buildPath(previous, end);
                }

                visited.add(neighbor);
                queue.add(neighbor);
            }
        }

        return Collections.emptyList();
    }

    private static List<StationType> buildPath(Map<StationType, StationType> previous, StationType end) {
        List<StationType> path = new ArrayList<>();
        for (StationType at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
