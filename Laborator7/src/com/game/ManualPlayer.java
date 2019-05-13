package com.game;

import java.util.Scanner;

public class ManualPlayer extends Player {

    public ManualPlayer(String name) {
        super(name);
    }

    protected boolean play() throws InterruptedException {
        Board board = game.getBoard();

        if (board.isEmpty()) {
            return false;
        }

        System.out.println("Available edges are:");
        for(Edge edge : board.getGraph().getEdges()) {
            System.out.print("[" + edge.nodeOne + "," + edge.nodeTwo + "] ");
        }

        System.out.println();
        System.out.println("Select an edge:");

        int nodeOne, nodeTwo;
        Scanner in = new Scanner(System.in);
        nodeOne = in.nextInt();
        nodeTwo = in.nextInt();

        board.extractEdge(nodeOne, nodeTwo);
        graph.add(new Edge(nodeOne, nodeTwo));
        Thread.sleep(THINKING_TIME);

        points = graph.isSpanningTree();
        if (points == board.getSize()) {
            game.setWinner(this);
        }

        return true;
    }

}
