package com.example.adam.polocoach;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class InGame extends Activity {

    DataBase db;

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

    String whichActivity;

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Tab 1"));
        tabs.addTab(tabs.newTab().setText("Tab 2"));
        tabs.addTab(tabs.newTab().setText("Tab 3"));


        db = new DataBase(this);
        toast = new Toast(this);


        score1Text = (TextView) findViewById(R.id.score1);
        score2Text = (TextView) findViewById(R.id.score2);

        playerButtons = new Button[12];

        for (int i = 0; i < 12; i++){
            String id = "player" + (i+1);
            int resID = getResources().getIdentifier(id, "id", getPackageName());
            playerButtons[i] = (Button) findViewById(resID);

        }

        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewTime.setText("03:00");
        timer = new CounterClass(180000, 1000);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.rl);
        layout.setVisibility(View.GONE);

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
            Intent intent = new Intent("com.example.adam.polocoach.GoalActivity");
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
            if (team == 0){
                score1++;
                score1Text.setText(Integer.toString(score1));
            }
            else {
                score2++;
                score2Text.setText(Integer.toString(score2));
            }

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

    public void onClickKiallit(View v){
        if (clickedPlayer != null){
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.rl);
            layout.setVisibility(View.VISIBLE);
        }
        whichActivity = "kiallit";
    }

    public void onClickKiallitFrom(View v){
        int team = 0;
        for (int i = 0; i < 12; i++){
            String id = "player" + (i+1);
            int resID = getResources().getIdentifier(id, "id", getPackageName());
            if (resID == clickedPlayer.getId()){
                team = (i+1) / 12;
                break;
            }
        }
        if (whichActivity == "wrongPass"){
            Button b = (Button) v;
            db.addActivity(new ActivityObject(teams[team], clickedPlayer.getText().toString(), "wrongPass ", playerButtons[Integer.parseInt(b.getText().toString()) - 1].getText().toString()));
        }

        if (whichActivity == "kiallit") {
            Button b = (Button) v;
            db.addActivity(new ActivityObject(teams[team], clickedPlayer.getText().toString(), "kiallitva ", playerButtons[Integer.parseInt(b.getText().toString()) - 1].getText().toString()));
        }
        toast = Toast.makeText(this, "Action stored", Toast.LENGTH_LONG);
        toast.show();
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.rl);
        layout.setVisibility(View.GONE);
    }

    public void reachDataBase(View v){
        List<ActivityObject> objects = db.getAllActivity();
        for (ActivityObject activity : objects){
            System.out.println(activity.getId() + " " + activity.getTeam() + " " + activity.getPlayer() + " " + activity.getActivityName() + " " + activity.getActivityText());
        }
    }

    public void wrongPass(View v){
        whichActivity = "wrongPass";

        if (clickedPlayer != null){
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.rl);
            layout.setVisibility(View.VISIBLE);
        }
    }

    public void act(View v){
        Intent intent = new Intent("com.example.adam.polocoach.Graphicons");
        startActivity(intent);
    }

    public void onClickRauszas(View v){
        if (clickedPlayer != null){
            int team = 0;
            for (int i = 0; i < 12; i++){
                String id = "player" + (i+1);
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                if (resID == clickedPlayer.getId()){
                    team = (i+1) / 12;
                    break;
                }
            }
            toast = Toast.makeText(this, "Action stored", Toast.LENGTH_LONG);
            toast.show();
            db.addActivity( new ActivityObject(teams[team], clickedPlayer.getText().toString(), "rauszas ", ""));
        }
    }

    public void onClickLabdaszerzes(View v){
        if (clickedPlayer != null){
            int team = 0;
            for (int i = 0; i < 12; i++){
                String id = "player" + (i+1);
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                if (resID == clickedPlayer.getId()){
                    team = (i+1) / 12;
                    break;
                }
            }
            toast = Toast.makeText(this, "Action stored", Toast.LENGTH_LONG);
            toast.show();
            db.addActivity( new ActivityObject(teams[team], clickedPlayer.getText().toString(), "labdaszerzes ", ""));
        }
    }
}
