package com.example.adam.polocoach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnTouchListener{

    ImageView ball;
    ImageView goal;

    boolean moving = false;
    float x, y = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ball = (ImageView) findViewById(R.id.ball);
        goal = (ImageView) findViewById(R.id.goal);

        ball.setOnTouchListener(this);
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
        Intent intent = getIntent();
        Player player = intent.getParcelableExtra("clickedPlayer");
        player.shoot(1,1);
        getParent().
    }
}
