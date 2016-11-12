package com.example.adam.polocoach;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    Canvas canvas;
    Paint paint;
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BitmapFactory.Options myOptions = new BitmapFactory.Options();
        myOptions.inScaled = false;
        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;


        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kapu, myOptions);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;


        Bitmap workingBitmap = Bitmap.createBitmap(bitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);


        canvas = new Canvas(mutableBitmap);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setAdjustViewBounds(true);
        imageView.setImageBitmap(mutableBitmap);



        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                canvas.drawCircle(motionEvent.getX(), motionEvent.getY(), 10, paint);
                imageView.invalidate();
                return false;
            }
        });
        canvas.drawCircle(720,30,10,paint);

        /*imageView = (ImageView) findViewById(R.id.imageView);
        Display display = getWindowManager().getDefaultDisplay();
        bitmap = Bitmap.createBitmap(display.getWidth(), display.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint(Color.BLACK);
        imageView.setImageBitmap(bitmap);
        imageView.setOnTouchListener(this);*/
    }


    /*@Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        canvas.drawCircle(motionEvent.getX(), motionEvent.getY(), 30, paint);
        imageView.invalidate();
        System.out.println("sd");
        return false;
    }*/
}
