package com.example.tp_game;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int column;
    private int line;
    List<CaisseBlock> caisseBlocks;
    List<Goal> goals;
    Player player;
    String[] board;

    public Board(int column, int line, String[] board) {
        this.column = column;
        this.line = line;
        this.board = board;
        init();
    }

    public String[] getBoard() {
        return board;
    }

    public void setBoard(String[] board) {
        this.board = board;
    }

    public String[] getGameArray() {

        String[] plateau = {
                "#", "#", "#", "#", "#", "#", "#",
                "#", "P", ".", ".", ".", ".", "#",
                "#", ".", "C", ".", ".", ".", "#",
                "#", ".", ".", ".", ".", ".", "#",
                "#", ".", ".", ".", ".", "X", "#",
                "#", "#", "#", "#", "#", "#", "#",
        };

        String[] gameArray = this.getBoard();

        return gameArray;
    }

    public void init(){
        goals = new ArrayList<Goal>();
        caisseBlocks = new ArrayList<CaisseBlock>();

        String[] board = this.getGameArray();
        for (int i=0; i< board.length; i++)
        {
            switch(board[i]) {
                case "P":
                    player = new Player(R.drawable.right,"", i, this.getColumn());
                    break;
                case "X":
                    goals.add(new Goal(i, false));
                    break;
                case "C":
                    caisseBlocks.add(new CaisseBlock(i));
                    break;
            }
        }
    }

    public int getColumn() {
        return column;
    }

    public List<CaisseBlock> getCaisseBlocks() {
        return caisseBlocks;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public Player getPlayer() {
        return player;
    }
}
