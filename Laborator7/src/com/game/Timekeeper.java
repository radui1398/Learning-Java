package com.game;

public class Timekeeper implements Runnable {
    static private int TIME_SECONDS = 50;

    private Game game;
    private int timeLeft;

    public void setGame(Game target_game) {
        game = target_game;
    }

    @Override
    public void run() {
        timeLeft = TIME_SECONDS;

        while (timeLeft > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            timeLeft--;
        }

        game.timeout();
    }
}
