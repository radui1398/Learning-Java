package com.game;

import com.utils.RandomGenerator;

public class Player implements Runnable {
    protected static final int THINKING_TIME = 2;

    protected String name;
    protected Game game;
    protected Graph graph;
    protected int points;

    public Player(String name) {
        this.name = name;
        this.game = null;
        this.graph = new Graph(10);
    }

    public void setGraph(Graph newGraph) {
        this.graph = newGraph;
    }

    public String getName() {
        return this.name;
    }

    protected boolean play() throws InterruptedException {
        Board board = game.getBoard();

        if (board.isEmpty()) {
            return false;
        }

        graph.add(board.extract());
        Thread.sleep(THINKING_TIME);

        points = graph.isSpanningTree();
        if (points == board.getSize()) {
            game.setWinner(this);
        }

        return true;
    }

    @Override
    public void run() {
        while (!game.hasEnded()) {

            while (!this.equals(game.getPlayerAtTurn()) && !game.hasEnded()) {
                synchronized (game.getMutex()) {
                    try {
                       // System.out.println(this.name + " waiting");
                        game.getMutex().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

           System.out.println(this.name + " turn:");

            if (!game.hasEnded()) {
                try {
                    play();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.finishTurn(this);
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Player)) {
            return false;
        }

        Player player = (Player) other;
        return player.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getGraphSize() {
        return this.graph.getNodesSize();
    }

    public int getPoints() {
        return RandomGenerator.getRandomInt(getGraphSize());
    }
}

