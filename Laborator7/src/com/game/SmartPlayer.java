package com.game;

public class SmartPlayer extends Player {

    public SmartPlayer(String name) {
        super(name);
    }

    protected boolean play() throws InterruptedException {
        Board board = game.getBoard();

        if (board.isEmpty()) {
            return false;
        }

        if (graph.getEdges().size() == 0) {
            graph.add(board.extract());
        }
        else {
            Boolean visited[] = new Boolean[graph.getNodesSize()+1];
            for (Edge edge: graph.getEdges()) {
                visited[edge.nodeTwo] = true;
                visited[edge.nodeOne] = true;
            }

            for (Edge edge: board.getGraph().getEdges()) {
                if (visited[edge.nodeOne] != visited[edge.nodeTwo])
                {
                    graph.add(edge);
                    board.extractEdge(edge.nodeOne, edge.nodeTwo);
                    break;
                }
            }
        }
        Thread.sleep(THINKING_TIME);

        points = graph.isSpanningTree();
        if (points == board.getSize()) {
            game.setWinner(this);
        }

        return true;
    }

}

