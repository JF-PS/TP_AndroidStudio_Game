package com.example.tp_game;

import android.os.Parcel;
import android.os.Parcelable;

public class Level implements Parcelable {
    private int id;
    private String name;
    private int rows;
    private int cols;
    private String[] board;
    private boolean complete;
    private String recordTime;
    private String difficulty;
    private boolean achieved;
    private int boardId;

    public Level(int id, String name, int rows, int cols, String[] board, String recordTime, String difficulty, boolean achieved, int boardId) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.cols = cols;
        this.board = board;
        this.complete = false;
        this.recordTime = recordTime;
        this.difficulty = difficulty;
        this.achieved = achieved;
        this.boardId = boardId;
    }

    protected Level(Parcel in) {
        id = in.readInt();
        name = in.readString();
        rows = in.readInt();
        cols = in.readInt();
        board = in.createStringArray();
        recordTime = in.readString();
        difficulty = in.readString();
        boardId = in.readInt();
    }

    public static final Creator<Level> CREATOR = new Creator<Level>() {
        @Override
        public Level createFromParcel(Parcel in) {
            return new Level(in);
        }

        @Override
        public Level[] newArray(int size) {
            return new Level[size];
        }
    };

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public String[] getBoard() {
        return board;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setBoard(String[] board) {
        this.board = board;
    }

    public boolean isComplete() { return complete; }

    public void setComplete(boolean complete) { this.complete = complete; }

    public String toString() { return this.getName() + " - " + this.getRecordTime() + " - " + this.getDifficulty();  }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        System.out.println("paramètre reçue : " + recordTime);
        this.recordTime = recordTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        //dest.WriteInt(boardId);
        dest.writeString(name);
        dest.writeInt(rows);
        dest.writeInt(cols);
        dest.writeStringArray(board);
        dest.writeString(recordTime);
        dest.writeString(difficulty);

    }
}
