package com.example.tp_game;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class LevelActivity extends AppCompatActivity {

    List<Level> levels = new ArrayList<Level>();
    ListView listView;
    TextView title;
    LevelsManager levelManager;
    Context ctx;
    FeedReaderDbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        ctx = getApplicationContext();
        dbHelper = new FeedReaderDbHelper(ctx);
        db = dbHelper.getWritableDatabase();
        title = (TextView) findViewById(R.id.level_title);
        title.setTextColor(Color.parseColor("#FFFFFF"));
        generateLevels();
        this.LoadListeLevel();
        this.insertListLevels(levels);
    }

    public void LoadListeLevel() {
        listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter<Level> arrayAdapter = new ArrayAdapter<Level>(this, android.R.layout.simple_list_item_1 , levels);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Level levelClick = arrayAdapter.getItem(position);

                Intent intent = new Intent(LevelActivity.this, PlayActivity.class);
                intent.putExtra("Level", levelClick);
                startActivity(intent);
            }
        });
    }

    public void goHome(View view) {
        Intent intent = new Intent(LevelActivity.this, StartActivity.class);
        startActivity(intent);
    }

    public void generateLevels() {
        try {
            InputStream inputStream = getAssets().open("level.json");
            levelManager = new LevelsManager(inputStream);
            levels = levelManager.getLevels();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertLevel(int id, String name, int rows, int cols, String recordTime, String difficulty, boolean achieved) {
        ContentValues values = new ContentValues();

        values.put(FeedReaderLevel.FeedEntry.COLUMN_ID, id);
        values.put(FeedReaderLevel.FeedEntry.COLUMN_NAME, name);
        values.put(FeedReaderLevel.FeedEntry.COLUMN_ROWS, rows);
        values.put(FeedReaderLevel.FeedEntry.COLUMN_COLS, cols);
        values.put(FeedReaderLevel.FeedEntry.COLUMN_RECORD_TIME, recordTime);
        values.put(FeedReaderLevel.FeedEntry.COLUMN_DIFFICULTY, difficulty);
        values.put(FeedReaderLevel.FeedEntry.COLUMN_ACHIEVED, achieved);

        long newRowId = db.insert(FeedReaderLevel.FeedEntry.TABLE_NAME, null, values);
    }

    public void insertBoardLevel(String[] boardLevel, int boardId, int cols) {
        //FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(ctx);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values2 = new ContentValues();
        System.out.println("boardId : " + boardId);
        int i = 0;
        String row = "";
        for(int y = 0; y < boardLevel.length; y++) {
            row = row + boardLevel[y];
            i++;
            if(i == cols) {
                values2.put(FeedReaderBoard.FeedEntry.COLUMN_ID, boardId);
                values2.put(FeedReaderBoard.FeedEntry.COLUMN_ROWS, row);
                long newRowId = db.insert(FeedReaderBoard.FeedEntry.TABLE_NAME, null, values2);
                i = 0;
                row = "";
            }
        }

    }

    public void insertListLevels(List<Level> levels) {
        //FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(ctx);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.reset(db);
        for(Level level : levels) {
            this.insertLevel(level.getId(), level.getName(), level.getRows(), level.getCols(), level.getRecordTime(), level.getDifficulty(), level.isAchieved());
            this.insertBoardLevel(level.getBoard(), level.getBoardId(), level.getCols());
        }
    }
}