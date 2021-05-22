package com.example.tp_game;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int column;
    private int line;
    List<CaisseBlock> caisseBlocks;
    List<Goal> goals;
    Player player;

    public Board(int column, int line) {
        this.column = column;
        this.line = line;
        init();
    }

    public String[] getGameArray() {

        String[] boardGame = {
                "#", "#", "#", "#", "#", "#", "#",
                "#", "P", ".", ".", ".", ".", "#",
                "#", ".", "C", ".", "C", ".", "#",
                "#", ".", "X", ".", "X", ".", "#",
                "#", ".", ".", ".", ".", ".", "#",
                "#", "#", "#", "#", "#", "#", "#",
        };
        return boardGame;
    }

    public void init(){
        goals = new ArrayList<Goal>();
        caisseBlocks = new ArrayList<CaisseBlock>();

        String[] board = this.getGameArray();
        for (int i=0; i< board.length; i++)
        {
            switch(board[i]) {
                case "P":
                    player = new Player(R.drawable.player_right,"", i);
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

    public int getLine() {
        return line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setLine(int line) {
        this.line = line;
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
