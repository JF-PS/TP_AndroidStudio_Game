package com.example.tp_game;

public class Goal {
    private int position;
    private boolean achieved;

    public Goal(int position, boolean achieved) {
        this.position = position;
        this.achieved = achieved;
    }

    public int getPosition() {
        return position;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }
}
