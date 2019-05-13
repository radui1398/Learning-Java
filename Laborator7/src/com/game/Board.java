package com.game;

import com.utils.RandomGenerator;

public class Board {
    private final Graph graph;
    private int size;

    public Board(int graphSize) {
        size = graphSize;
        graph = new Graph(graphSize);

        initCompleteGraph();
        shuffleEdges();
    }

    public Graph getGraph() {
        return graph;
    }

    public int getSize() {
        return size;
    }

    private void initCompleteGraph() {
        for (int nodeOne = 1; nodeOne <= size; nodeOne++)
            for (int nodeTwo = nodeOne + 1; nodeTwo <= size; nodeTwo++) {
                Edge e = new Edge(nodeOne, nodeTwo);
                graph.addEdge(e);
            }
    }

    private void shuffleEdges() {
        int shuffleNumber = graph.getEdges().size()/2;

        for (int i = 0; i < shuffleNumber; i++) {
            int firstRandomEdge = RandomGenerator.getRandomInt(size);
            int secondRandomEdge = RandomGenerator.getRandomInt(size);

            graph.swap(firstRandomEdge, secondRandomEdge);
        }
    }

    public synchronized void extractEdge(int nodeOne, int nodeTwo) {
        int number = -1;
        for (int i = 0; i < graph.getEdges().size(); i++) {
            Edge ed = graph.getEdges().get(i);
            if (ed.nodeOne == nodeOne && ed.nodeTwo == nodeTwo) {
                number = i;
                break;
            }
        }

        if (number >= 0)
            graph.removeEdge(number);

        System.out.println("Extracted edge with nodes (" + nodeOne + ", " + nodeTwo + ").");
    }

    public synchronized Edge extract() {
        int edgeNumber = RandomGenerator.getRandomInt(graph.getEdges().size());

        Edge edge = graph.removeEdge(edgeNumber);
        System.out.println("Extracted edge with nodes (" + edge.getFirst() + ", " + edge.getSecond() + ").");
        return edge;
    }

    public boolean isEmpty() {
        return graph.getEdges().isEmpty();
    }
}
