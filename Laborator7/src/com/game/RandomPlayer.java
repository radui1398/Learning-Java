package com.game;

public class RandomPlayer extends Player {

    public RandomPlayer(String name) {
        super(name);
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

}

