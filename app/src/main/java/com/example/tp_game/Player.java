package com.example.tp_game;

public class Player {
    private int picture;
    private int position;
    private String playerMoove;

    public Player(int picture, String playerMoove, int position) {
        this.picture = picture;
        this.playerMoove = playerMoove;
        this.position = position;
    }

    public int getPicture() {
        return picture;
    }

    public String getPlayerMoove() {
        return playerMoove;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void setPlayerMoove(String playerMoove) {
        this.playerMoove = playerMoove;
        ActualisePicturePlayerPosition();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position += position;
    }

    public int getPlayerPositionMove(String move){
        int positionMove = 0;
        switch(move) {
            case "left":
                positionMove = -1;
                break;
            case "top":
                positionMove = -7;
                break;
            case "bottom":
                positionMove = +7;
                break;
            default:
                positionMove = +1;
        }
        return positionMove;
    }

    public void ActualisePicturePlayerPosition(){
        int positionPicture;
        switch(this.getPlayerMoove()) {
            case "left":
                this.setPosition(getPlayerPositionMove(this.getPlayerMoove()));
                this.setPicture(R.drawable.player_left);
                break;
            case "top":
                this.setPosition(getPlayerPositionMove(this.getPlayerMoove()));
                this.setPicture(R.drawable.player_top);
                break;
            case "bottom":
                this.setPosition(getPlayerPositionMove(this.getPlayerMoove()));
                this.setPicture(R.drawable.player_botomme);
                break;
            default:
                this.setPosition(getPlayerPositionMove(this.getPlayerMoove()));
                this.setPicture(R.drawable.player_right);
        }
    }
}
