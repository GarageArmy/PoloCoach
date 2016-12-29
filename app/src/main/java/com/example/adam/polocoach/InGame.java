package com.example.adam.polocoach;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class InGame extends AppCompatActivity {

    DataBase db;
    public Button clickedPlayer;

    Button[] playerButtons;

    boolean paused = true;
    TextView textViewTime;
    //CounterClass timer;
    long pausedTime;

    String teams[] = {"DVSE", "asd"};

    String whichActivity;
    CounterClass timer;

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        db = new DataBase(this);
        toast = new Toast(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Home").withIcon(R.drawable.ball).withIdentifier(1);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Statisztika");


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_material_red)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(item1).withAccountHeader(headerResult)
                .addDrawerItems(item2)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch((int)drawerItem.getIdentifier()){
                            case 1: finish();
                                break;
                            case 2: {
                                Intent intent = new Intent("com.example.adam.polocoach.Graphicons");
                                startActivity(intent);
                            } break;

                        }
                        return true;
                    }
                })
                .build();



        playerButtons = new Button[28];

        for (int i = 0; i < 28; i++){
            String id = "player" + (i+1);
            int resID = getResources().getIdentifier(id, "id", getPackageName());
            playerButtons[i] = (Button) findViewById(resID);

        }

        /*textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewTime.setText("03:00");
        timer = new CounterClass(180000 * 5, 1000);*/

        /*RelativeLayout layout = (RelativeLayout) findViewById(R.id.rl);
        layout.setVisibility(View.GONE);*/

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
            for (int i = 0; i < 28; i++){
                String id = "player" + (i+1);
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                if (resID == clickedPlayer.getId()){
                    team = (i+1) / 15;
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

    public void onClickKiallit(View v){
        if (clickedPlayer != null){
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.rl);
            layout.setVisibility(View.VISIBLE);
        }
        whichActivity = "kiallit";
    }

    public void onClickKiallitFrom(View v){
        int team = 0;
        for (int i = 0; i < 28; i++){
            String id = "player" + (i+1);
            int resID = getResources().getIdentifier(id, "id", getPackageName());
            if (resID == clickedPlayer.getId()){
                team = (i+1) / 15;
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
            for (int i = 0; i < 28; i++){
                String id = "player" + (i+1);
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                if (resID == clickedPlayer.getId()){
                    team = (i+1) / 15;
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
            for (int i = 0; i < 28; i++){
                String id = "player" + (i+1);
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                if (resID == clickedPlayer.getId()){
                    team = (i+1) / 15;
                    break;
                }
            }
            toast = Toast.makeText(this, "Action stored", Toast.LENGTH_LONG);
            toast.show();
            db.addActivity( new ActivityObject(teams[team], clickedPlayer.getText().toString(), "labdaszerzes ", ""));
        }
    }




    //PLAYER CHOOSER

    public void emptyInputField(View v){
        EditText a = (EditText) v;
        if (!a.getText().toString().contains("player"))
            a.setText("");
    }

    public void finishSelection(View v){
        RelativeLayout a = (RelativeLayout) findViewById(R.id.playerselection);
        a.setVisibility(View.GONE);

        a = (RelativeLayout) findViewById(R.id.secondary);
        a.setVisibility(View.VISIBLE);
        for (int i = 0; i < 28; i++){
            String id = "p" + (i+1);
            int resID = getResources().getIdentifier(id, "id", getPackageName());
            playerButtons[i].setText(((EditText) findViewById(resID)).getText());
            System.out.println(i);

        }
        int resID = getResources().getIdentifier("team1", "id", getPackageName());
        teams[0] = ((EditText)findViewById(resID)).getText().toString();
        resID = getResources().getIdentifier("team2", "id", getPackageName());
        teams[1] = ((EditText)findViewById(resID)).getText().toString();
    }
}
