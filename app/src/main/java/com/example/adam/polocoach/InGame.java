package com.example.adam.polocoach;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class InGame extends Activity {

    TextView historyText;
    public Button clickedPlayer;

    Button[] playerButtons;

    boolean paused = true;
    TextView textViewTime;
    CounterClass timer;
    long pausedTime;


    int score1, score2 = 0;
    TextView score1Text, score2Text;

    String teams[] = {"DVSE", "asd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        historyText  = (TextView) findViewById(R.id.historyText);

        score1Text = (TextView) findViewById(R.id.score1);
        score2Text = (TextView) findViewById(R.id.score2);

        playerButtons = new Button[12];

        for (int i = 0; i < 12; i++){
            String id = "player" + (i+1);
            int resID = getResources().getIdentifier(id, "id", getPackageName());
            playerButtons[i] = (Button) findViewById(resID);

        }

        historyText.setMovementMethod(new ScrollingMovementMethod());

        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewTime.setText("03:00");
        timer = new CounterClass(180000, 1000);

    }

    public void onClickPlayer(View v){
        Button button = (Button) v;
        if (clickedPlayer != null) {
            for (int i = 0; i < 12; i++){
                if (clickedPlayer == playerButtons[i]){
                    Button prev = (Button) findViewById(playerButtons[i].getId());
                    prev.setBackgroundColor(Color.LTGRAY);
                }
            }
        }

        clickedPlayer = button;
        v.setBackgroundColor(Color.GREEN);
    }

    public void onClickGoal(View v){
        if (clickedPlayer != null){
            Intent intent = new Intent("com.example.adam.polocoach.MainActivity");
            int team = 0;
            for (int i = 0; i < 12; i++){
                String id = "player" + (i+1);
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                if (resID == clickedPlayer.getId()){
                    team = (i+1) / 12;
                    break;
                }
            }
            intent.putExtra("player", clickedPlayer.getText());
            intent.putExtra("team", teams[team]);
            startActivity(intent);
        }
    }

    public void timerOnOff(View v){
        if (paused) {
            timer.start();
            paused = false;
        } else {
            timer.cancel();
            timer = new CounterClass(pausedTime, 1000);
            paused = true;
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            textViewTime.setText(hms);
            pausedTime = 60000 * (TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))) + 1000 * (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            textViewTime.setText("End");
        }
    }
}
