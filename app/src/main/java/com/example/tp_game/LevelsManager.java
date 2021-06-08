package com.example.tp_game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class LevelsManager  {
    List<Level> levels;
    InputStream inputStream;


    public LevelsManager(InputStream inputStream) {
        this.inputStream = inputStream;
        this.levels = new ArrayList<Level>();
        get_json();
    }

    public List<Level> getLevels() {
        return levels;
    }

    public Level getLevelById(int idLevel) {

        for(Level l: this.getLevels()) {
            if(l.getId() == idLevel) {
                return l;
            }
        }
        return this.getLevels().get(this.getLevels().size()-1);
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void get_json()
    {
        try
        {
            InputStream inputStream = getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;

            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONArray array = new JSONArray(responseStrBuilder.toString());

            for(int i=0; i < array.length(); i++) {

                JSONObject level = array.getJSONObject(i);
                JSONObject board = level.getJSONObject("board");
                JSONObject boardRows = board.getJSONObject("row");
                String LevelId = level.getString("id");
                String LevelName = level.getString("name");
                String LevelRows = level.getString("rows");
                String LevelCols = level.getString("cols");
                String BoardId = board.getString("id");
                String recordTime = level.getString("recordTime");
                String difficulty = level.getString("difficulty");
                boolean Achieved = level.getBoolean("achieved");
                String[] boardLevel = new String[parseInt(LevelRows)*parseInt(LevelCols)];

                int position = 0;
                for(int y=0; y < parseInt(LevelRows); y++) {
                    String row = boardRows.getString(Integer.toString(y));
                    String[] bordRow = row.split("-");
                    for(int z=0; z < parseInt(LevelCols); z++) {
                        String caseRow = bordRow[z];
                        boardLevel[position] = caseRow;
                        position++;
                    }
                }

                Level myLevel = new Level(parseInt(LevelId), LevelName, parseInt(LevelRows), parseInt(LevelCols), boardLevel, recordTime, difficulty, Achieved, parseInt(BoardId));
                levels.add(myLevel);
            }

        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }
    }
}
