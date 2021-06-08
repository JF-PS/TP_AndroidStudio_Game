package com.example.tp_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    Level level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        level = getIntent().getParcelableExtra("Level");
    }

    public void goHome(View view)
    {
        Intent intent = new Intent(GameOverActivity.this, StartActivity.class);
        startActivity(intent);
    }

    public void reStartLevel(View view)
    {
        Intent intent = new Intent(GameOverActivity.this, PlayActivity.class);
        intent.putExtra("Level", level);
        startActivity(intent);
    }
}