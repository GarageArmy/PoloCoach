package com.example.adam.polocoach;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class GoalActivity extends Activity implements View.OnTouchListener{

    ImageView ball;
    ImageView goal;

    boolean moving = false;
    float x, y = 0.0f;

    DataBase db;
    String player, team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBase(this);


        ball = (ImageView) findViewById(R.id.ball);
        goal = (ImageView) findViewById(R.id.goal);

        ball.setOnTouchListener(this);

        player = getIntent().getStringExtra("player");
        team = getIntent().getStringExtra("team");

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moving = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (moving) {
                    x = motionEvent.getRawX() - ball.getWidth() / 2;
                    y = motionEvent.getRawY() - ball.getHeight() * 3 / 2;

                    ball.setX(x);
                    if (y < goal.getHeight() + ball.getHeight() + 20)
                        ball.setY(y);
                }
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                break;
        }
        return true;
    }

    public void onClickGoal(View v){
        db.addActivity( new ActivityObject(team, player, "goal ", x + " " + y));

       /* List<ActivityObject> objects = db.getAllActivity();
        for (ActivityObject activity : objects){
            System.out.println(activity.getId());
        }*/
        //finishActivity(0);
        finish();
    }

    public void fromAction(View v){
        db.addActivity( new ActivityObject(team, player, "goal ", x + " " + y + " " + "action"));
        Toast.makeText(this, "Action stored", Toast.LENGTH_LONG);
        finish();
    }

    public void fromAdvantage(View v){
        db.addActivity( new ActivityObject(team, player, "goal ", x + " " + y + " " + "advantage"));
        Toast.makeText(this, "Action stored", Toast.LENGTH_LONG);
        finish();
    }

    public void fromFreeBall(View v){
        db.addActivity( new ActivityObject(team, player, "goal ", x + " " + y + " " + "free"));
        Toast.makeText(this, "Action stored", Toast.LENGTH_LONG);
        finish();
    }

    public void fromZiccer(View v){
        db.addActivity( new ActivityObject(team, player, "goal ", x + " " + y + " " + "ziccer"));
        Toast.makeText(this, "Action stored", Toast.LENGTH_LONG);
        finish();
    }

    public void fromPenalty(View v){
        db.addActivity( new ActivityObject(team, player, "goal ", x + " " + y + " " + "penalty"));
        Toast.makeText(this, "Action stored", Toast.LENGTH_LONG);
        finish();
    }
}
