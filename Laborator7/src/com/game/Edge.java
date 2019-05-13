package com.game;

public class Edge {

    int nodeOne;
    int nodeTwo;

    /**
     * Constructor
     * @param nodeOne
     * @param nodeTwo
     */
    public Edge(int nodeOne, int nodeTwo){
        setNodeOne(nodeOne);
        setNodeTwo(nodeTwo);
    }
    /**
     * Setter of the first node
     * @param nodeOne first node
     */
    public void setNodeOne(int nodeOne) {
        this.nodeOne = nodeOne;
    }

    /**
     * Setter of the second node
     * @param nodeTwo
     */
    public void setNodeTwo(int nodeTwo) {
        this.nodeTwo = nodeTwo;
    }

    public int getFirst() {
        return nodeOne;
    }

    public int getSecond() {
        return nodeTwo;
    }
}
