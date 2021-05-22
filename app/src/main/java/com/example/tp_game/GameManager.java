package com.example.tp_game;
import android.widget.LinearLayout;

import java.util.List;

public class GameManager {
    private String[] boardGame;
    private Player player;
    private List<CaisseBlock> caisseBlocks;
    private List<Goal> goals;


    public GameManager(String[] boardGame, Player player, List<CaisseBlock> caisseBlocks, List<Goal> goals) {
        this.boardGame = boardGame;
        this.player = player;
        this.caisseBlocks = caisseBlocks;
        this.goals = goals;
    }

    public String[] getBoardGame() {
        return boardGame;
    }

    public void setBoardGame(String[] boardGame) {
        this.boardGame = boardGame;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Goal> getGoals() { return goals; }

    public void manageGame(String playerMove) {
        if(!isWal(playerMove)){
            Player player = this.getPlayer();
            String[] board = this.getBoardGame();

            int position1 = player.getPosition();
            String case1 = board[position1];

            int position2 = position1 + player.getPlayerPositionMove(playerMove);
            String case2 = board[position2];

            gameManagement(case1, case2, position1, position2, playerMove);

            this.setBoardGame(board);
        }
    }

    public boolean isWal(String objectMove){
        int objectPosition = this.getPlayer().getPosition()+player.getPlayerPositionMove(objectMove);
        return isWal(objectPosition);
    }

    public boolean isWal(int objectPosition){
        return (this.getBoardGame()[objectPosition] == "#");
    }

    public int[] MakeInterfaceGraphique() {

        String[] boardGame = this.getBoardGame();
        int[] graphiqueBoard = new int[boardGame.length];

        for (int i=0; i< boardGame.length; i++)
        {
            switch(this.getBoardGame()[i]) {
                case "P":
                    Player player = this.getPlayer();
                    graphiqueBoard[i] = player.getPicture();
                    break;
                case "X":
                    graphiqueBoard[i] = R.drawable.point;
                    break;
                case "C":
                    graphiqueBoard[i] = R.drawable.block;
                    break;
                case ".":
                    graphiqueBoard[i] = R.drawable.terrain;
                    break;
                case "V":
                    graphiqueBoard[i] = R.drawable.victory;
                    break;
                default:
                    graphiqueBoard[i] = R.drawable.mur;
            }
        }
        return graphiqueBoard;
    }

    public void gameManagement(String case1, String case2, int position1, int position2, String playerMove) {
        String[] board = this.getBoardGame();
        switch(case2){
            case "C":
                for (CaisseBlock caisseBlock: caisseBlocks) {
                    if(caisseBlock.getPosition() == position2) {
                        int caissePosition = caisseBlock.getPosition() + player.getPlayerPositionMove(playerMove);
                        if(!isWal(caissePosition) && board[caissePosition] != "C"){
                            caisseBlock.setPosition(caissePosition);
                            player.setPlayerMoove(playerMove);

                            boolean isGoalPresent = false;
                            for(Goal g : this.getGoals()) {
                                if(g.getPosition() == caissePosition) {
                                    isGoalPresent = true;
                                    break;
                                }
                            }

                            if(!isGoalPresent) {
                                board[position1] = board[caissePosition];
                                board[position2] = case1;
                                board[caissePosition] = case2;
                                gallAchived(caissePosition);
                            }
                            else{
                                board[position1] = ".";
                                board[position2] = case1;
                                board[caissePosition] = case2;
                                gallAchived(caissePosition);
                            }
                        }
                    }
                }
                break;
            case "X":
                player.setPlayerMoove(playerMove);
                board[position2] = case1;
                board[position1] = ".";
                break;
            default:
                player.setPlayerMoove(playerMove);
                board[position2] = case1;
                board[position1] = case2;
        }
        gallPosition();
    }

    public void gallPosition() {
        for (CaisseBlock caisseBlock: caisseBlocks) {
            for(Goal goal : goals) {
                if((goal.getPosition() != caisseBlock.getPosition()) && (goal.getPosition() != player.getPosition()) && (!goal.isAchieved())) {
                    this.getBoardGame()[goal.getPosition()] = "X";
                }
            }
        }
    }

    public void gallAchived(int caissePosition) {
        int allGoalAchieved = 0;

        for(Goal goal : goals) {
            if(goal.getPosition() == caissePosition){
                goal.setAchieved(true);
            }
            if(goal.isAchieved()) {
                boolean achiveOk = false;
                for(CaisseBlock caisseBlock: caisseBlocks) {
                    if(caisseBlock.getPosition() == goal.getPosition()){
                        achiveOk = true;
                    }
                }
                goal.setAchieved(achiveOk);
            }
            if(goal.isAchieved()){
                allGoalAchieved++;
            }
        }
        if(allGoalAchieved == this.getGoals().size()) {
            victory();
        }
    }

    public void victory() {
        int i = 0;
        while (i < this.getBoardGame().length) {
            this.getBoardGame()[i] = "V";
            i++;
        }
    }


}
