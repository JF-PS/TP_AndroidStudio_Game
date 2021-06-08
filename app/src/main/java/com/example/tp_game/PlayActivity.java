package com.example.tp_game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import static java.lang.Integer.parseInt;

public class PlayActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 10000;
    private TextView t;
    private CountDownTimer cdt;
    private boolean timeRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    LinearLayout linearLayout;
    GridView gridView;
    Level level;
    GameManager gameManager;
    Board board;
    String timeLeftFormatted;
    ImageButton top;
    ImageButton bottom;
    ImageButton left;
    ImageButton right;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        linearLayout = findViewById(R.id.layoutContainer);
        gridView = findViewById(R.id.gridView1);
        level = getIntent().getParcelableExtra("Level");
        setInterface();
        gridView.setNumColumns(level.getCols());
        t = findViewById(R.id.countdown);
        init();
    }

    public void moveTop(View view) {
        this.refreshGame("top");
    }

    public void moveLeft(View view) {
        this.refreshGame("left");
    }

    public void moveRight(View view) {
        this.refreshGame("right");
    }

    public void moveBottom(View view) {
        this.refreshGame("bottom");
    }

    public void refreshGame(String playerMove){
        gameManager.manageGame(playerMove);
        loadInterface();
        if(level.isComplete()){
            this.viewScore();
        }
    }

    public void start()
    {
        startTimer();
        updateCountDownText();
        board = new Board(level.getCols(), level.getRows(), level.getBoard().clone());
        gameManager = new GameManager(board.getGameArray(), board.getPlayer(), board.getCaisseBlocks(), board.getGoals(), level);
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

    public void  setInterface() {
        top = (ImageButton) findViewById(R.id.button1);
        bottom = (ImageButton) findViewById(R.id.button4);
        left = (ImageButton) findViewById(R.id.button2);
        right = (ImageButton) findViewById(R.id.button3);
        title = (TextView) findViewById(R.id.level_title);

        top.setImageResource(R.drawable.button_top);
        bottom.setImageResource(R.drawable.button_bottom);
        left.setImageResource(R.drawable.button_left);
        right.setImageResource(R.drawable.button_right);
        title.setText(level.getName());
        title.setTextColor(Color.parseColor("#FFFFFF"));
    }
    public void reStart(View view) {
        resetTimer();
        init();
    }

    public void goLevels(View view)
    {
        cdt.cancel();
        Intent intent = new Intent(PlayActivity.this, LevelActivity.class);
        startActivity(intent);
    }


    public void startTimer(){
        cdt = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                cdt.cancel();
                timeRunning = false;
                Intent intent = new Intent(PlayActivity.this, GameOverActivity.class);
                intent.putExtra("Level", level);
                startActivity(intent);
            }
        }.start();

        timeRunning = true;
    }

    public void pauseTimer() {
        cdt.cancel();
        timeRunning = false;
    }

    public void resetTimer() {
        cdt.cancel();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }

    public void updateCountDownText() {
        int minutes = (int) mTimeLeftInMillis / 1000 / 60;
        int seconds = (int) mTimeLeftInMillis / 1000 % 60;

        timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        t.setText(timeLeftFormatted);
    }

    public String getRecordTime() {
        String recordTime = t.getText().toString();
        String seconde = recordTime.split(":")[1];
        String theRecord;
        int secondeR = 20 - parseInt(seconde);
        if(secondeR < 10)
            theRecord = "0" + secondeR;
        else
            theRecord = "" + secondeR;

        recordTime = "00:" + theRecord;

        return recordTime;
    }

    public void viewScore() {
        pauseTimer();
        cdt.cancel();

        Intent intent = new Intent(PlayActivity.this, ScoreActivity.class);

        level.setRecordTime(this.getRecordTime());
        intent.putExtra("Level", level);

        startActivity(intent);
    }


}



