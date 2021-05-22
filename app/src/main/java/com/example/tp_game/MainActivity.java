package com.example.tp_game;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    GridView gridView;
    GameManager gameManager;
    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.layoutContainer);
        gridView = findViewById(R.id.gridView1);
        gridView.setNumColumns(7);
        init();
    }

    public void moveTop(View view) {
        this.refrechGame("top");
    }

    public void moveLeft(View view) {
        this.refrechGame("left");
    }

    public void moveRight(View view) {
        this.refrechGame("right");
    }

    public void moveBottom(View view) {
        this.refrechGame("bottom");
    }

    public void refrechGame(String playerMove){
        gameManager.manageGame(playerMove);
        loadInterface();
    }

    public void reStart(View view) {
        init();
    }

    public void start()
    {
        board = new Board(7,6);
        gameManager = new GameManager(board.getGameArray(), board.getPlayer(), board.getCaisseBlocks(), board.getGoals());
    }

    public void loadInterface()
    {
        CustomAdapter customAdapter = new CustomAdapter(gameManager.MakeInterfaceGraphique(), this);
        gridView.setAdapter(customAdapter);
    }

    public void init() {
        start();
        loadInterface();
    }


}



