package com.game;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;

    private final List<Player> players = new ArrayList<>();
    private Player winner;
    private boolean hasEnded = true;
    private int playerAtTurnIndex = 0;
    private Object mutex = new Object();

    public void addPlayer(Player player) {
        if (!hasEnded()){
            throw new RuntimeException("Game is running...cannot add players");
        }

        players.add(player);
        player.setGame(this);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public Boolean hasEnded() {
        return hasEnded || board.isEmpty();
    }

    synchronized void endGame() {
        hasEnded = true;
    }

    public void setWinner(Player player) {
        winner = player;
        endGame();

        System.out.println("Winner is: " + player.getName() + "!!!!");
    }

    public void timeout() {
        endGame();
        System.out.println("Time is out, sorry!");
    }

    public void finishTurn(Player player) {
        if (!players.get(playerAtTurnIndex).equals(player)){
            throw new RuntimeException("Not your turn!");
        }

        playerAtTurnIndex = (playerAtTurnIndex + 1) % players.size();
        synchronized (getMutex()) {
            getMutex().notifyAll();
        }
    }

    private void startTimeThread() {
        Timekeeper keeper = new Timekeeper();
        keeper.setGame(this);

        Thread thread = new Thread(keeper);
        thread.setDaemon(true);
        thread.start();
    }

    public void start(){

        startTimeThread();
        hasEnded = false;
        playerAtTurnIndex = -1;

        Thread[] threads = new Thread[players.size()];

        for (int i = 0; i < players.size(); ++i){
            players.get(i).setGraph(new Graph(board.getSize()));

            threads[i] = new Thread(players.get(i));
            threads[i].start();
        }

        playerAtTurnIndex = 0;

        synchronized (getMutex()) {
            getMutex().notifyAll();
        }

        try {
            for (int i = 0; i < players.size(); ++i) {
                threads[i].join();
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        if (winner == null)
            for (Player player : players){
                System.out.println(player + " has a number of " + player.getPoints() + " points.");
        }
        endGame();
    }

    public Object getMutex() {
        return mutex;
    }

    public Player getPlayerAtTurn() {
        if (playerAtTurnIndex < 0){
            return null;
        }

        return players.get(playerAtTurnIndex);
    }

}