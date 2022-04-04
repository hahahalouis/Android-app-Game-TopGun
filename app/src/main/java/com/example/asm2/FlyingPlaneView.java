package com.example.asm2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class FlyingPlaneView extends View {

    private Bitmap bg,resize_bg,resize_plane,rocket,resize_rocket;
    private Bitmap life[] = new Bitmap[2];
    private Bitmap plane[] = new Bitmap[2];

    private Paint scorePaint = new Paint();

    private int planeX = 50, planeY,planeSpeed;
    private int rocketX,rocketY,rocketSpeed =20;
    private int canvasWidth, canvasHeight,score;

    private boolean touchStatus = false;



    public FlyingPlaneView(Context context) {
        super(context);
        //Declare Bitmap
        rocket = BitmapFactory.decodeResource(getResources(), R.drawable.rocket);

        //resize Bitmap
        plane[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane), 200, 200 ,false);
        plane[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.planeup), 200, 200 ,false);
        resize_rocket = Bitmap.createScaledBitmap(rocket, 200, 200 ,false);
        life[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.heart), 100, 100 ,false);
        life[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.greyheart), 100, 100 ,false);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        planeY = 550;
        score = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingame_bg);
        resize_bg = Bitmap.createScaledBitmap(bg, canvas.getWidth(),canvas.getHeight(),false);

        canvas.drawBitmap(resize_bg,0,0,null);

        int minPlaneY = plane[0].getHeight();
        int maxPlaneY = canvasHeight - plane[0].getHeight() * 2;
        planeY = planeY + planeSpeed;

        // plane position
        if(planeY < minPlaneY)
        {
            planeY = minPlaneY;
        }
        if(planeY > maxPlaneY)
        {
            planeY = maxPlaneY;
        }

        planeSpeed = planeSpeed + 2;

        if(touchStatus)
        {
            canvas.drawBitmap(plane[1],planeX,planeY,null);
            touchStatus = false;
        }else
        {
            canvas.drawBitmap(plane[0],planeX,planeY,null);
        }

        //heart drawing
        canvas.drawBitmap(life[0],780,30,null);
        canvas.drawBitmap(life[0],880,30,null);
        canvas.drawBitmap(life[0],990,30,null);

        //Rocket
        rocketX = rocketX - rocketSpeed;
        if(rocketX < 0)
        {
            rocketX = canvasWidth + 21;
            rocketY = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
        }
        canvas.drawBitmap(resize_rocket,rocketX,rocketY,null);

        //plane hit detect
        if(hitRocketChecker(rocketX,rocketY))
        {
            score = score + 10;
            rocketX = rocketX + 100;
        }
        canvas.drawText("Score : "+score,70,100,scorePaint);

    }

    public boolean hitRocketChecker(int x, int y)
    {
        if((planeX < x && x < (planeX + plane[0].getWidth()) && planeY < y && y < (planeY + plane[0].getHeight())) || (planeX < x && x < (planeX + plane[1].getWidth()) && planeY < y && y < (planeY + plane[1].getHeight())))
        {
            return true;
        }else
        {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touchStatus = true;

            planeSpeed = -22;
        }

        return super.onTouchEvent(event);
    }
}
