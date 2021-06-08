package com.example.tp_game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class ScoreActivity extends AppCompatActivity {

    Level level;
    TextView title;
    LevelsManager levelManager;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        level = getIntent().getParcelableExtra("Level");
        title = (TextView) findViewById(R.id.title_level);
        t = (TextView) findViewById(R.id.countdown);
        title.setText(level.getName());
        title.setTextColor(Color.parseColor("#FFFFFF"));
        System.out.println("time record : " + level.getRecordTime());
        t.setText(level.getRecordTime());
    }

    public void goLevels(View view)
    {
        Intent intent = new Intent(ScoreActivity.this, LevelActivity.class);
        startActivity(intent);
    }

    public void reStartLevel(View view)
    {
        Intent intent = new Intent(ScoreActivity.this, PlayActivity.class);
        intent.putExtra("Level", level);
        startActivity(intent);
    }

    public void nextLevel(View view)
    {
        generateLevels();
        Level next_level = levelManager.getLevelById(level.getId()+1);
        Intent intent = new Intent(ScoreActivity.this, PlayActivity.class);
        intent.putExtra("Level", next_level);
        startActivity(intent);
    }

    public void generateLevels() {
        try {
            InputStream inputStream = getAssets().open("level.json");
            levelManager = new LevelsManager(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}