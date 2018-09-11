package utils;

import enums.FieldConnections;
import enums.FieldID;
import enums.PathType;
import lombok.Getter;
import model.Field;
import model.GameBoard;

import java.util.*;
import java.util.stream.Collectors;

public class DijkstraUtil {
    @Getter
    public class Edge {
        private final FieldID id1;
        private final FieldID id2;
        private final int weight;

        public Edge(FieldID id1, FieldID id2, PathType pathType) {
            this.id1 = id1;
            this.id2 = id2;
           weight = pathType.equals(PathType.UNCHARTED)?101:100;


        }
    }

    private final List<FieldID> nodes;
    private final List<Edge> edges;
    private Set<FieldID> settledNodes;
    private Set<FieldID> unSettledNodes;
    private Map<FieldID, FieldID> predecessors;
    private Map<FieldID, Integer> distance;

    public DijkstraUtil(GameBoard gameBoard) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<>(gameBoard.getFields().stream().map(Field::getFieldID).collect(Collectors.toList()));
        this.edges = new ArrayList<>();
        for (FieldConnections fieldConnection : FieldConnections.values()) {
            edges.add(new Edge(fieldConnection.getId1(), fieldConnection.getId2(),fieldConnection.getPathType()));
            edges.add(new Edge(fieldConnection.getId2(), fieldConnection.getId1(),fieldConnection.getPathType()));
        }

    }

    public void execute(FieldID source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            FieldID node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(FieldID node) {
        List<FieldID> adjacentNodes = getNeighbors(node);
        for (FieldID target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(FieldID node, FieldID target) {
        for (Edge edge : edges) {
            if (edge.getId1().equals(node)
                    && edge.getId2().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<FieldID> getNeighbors(FieldID node) {
        List<FieldID> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getId1().equals(node)
                    && !isSettled(edge.getId2())) {
                neighbors.add(edge.getId2());
            }
        }
        return neighbors;
    }

    private FieldID getMinimum(Set<FieldID> vertexes) {
        FieldID minimum = null;
        for (FieldID vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(FieldID vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(FieldID destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<FieldID> getPath(FieldID target) {
        LinkedList<FieldID> path = new LinkedList<>();
        FieldID step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}
