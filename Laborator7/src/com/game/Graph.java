package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Graph {
    private List<Edge> edges;
    private int nodesNumber;

    public Graph(int graphSize) {
        nodesNumber = graphSize;
        edges = new ArrayList<>();
    }

    public void addEdge(Edge edge)
    {
        edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void swap(int firstEdge, int secondEdge) {
        Collections.swap(edges, firstEdge, secondEdge);
    }

    public void add(Edge extract) {
        edges.add(extract);
    }

    public Edge removeEdge(int edgeNumber) {
        Edge edge = edges.get(edgeNumber);
        edges.remove(edge);
        
        return edge;
    }

    private Boolean isCyclic(int current, Boolean[] visited, int parent)
    {
        Boolean status = false;
        visited[current] = true;

        for (Edge edge : edges) {
            int adjacent = -1;

            if (edge.getFirst() == current)
                adjacent = edge.getSecond();

            if (edge.getSecond() == current)
                adjacent = edge.getFirst();

            if (adjacent >= 0) {
                if (!visited[adjacent]) {
                    status = status || isCyclic(adjacent, visited, current);

                } else if (adjacent != parent) {
                    status = true;
                }
            }
        }
        return status;
    }

    public int isSpanningTree() {
        Boolean[] visited = new Boolean[nodesNumber+1];
        for (int i = 1; i <= nodesNumber; i++)
            visited[i] = false;
        
        if (isCyclic(1, visited, -1))
            return 0;

        int count = nodesNumber;
        for (int node = 1; node <= nodesNumber; node++)
            if (!visited[node])
            {
                count--;
            }

        return count;
    }

    public int getNodesSize() {
        return nodesNumber;
    }
}