package com.example.asm2;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

public class FlyingPlaneView extends View {

    private Bitmap bg,resize_bg,gas,resize_gas, rocket;
    private Bitmap life[] = new Bitmap[2];
    private Bitmap plane[] = new Bitmap[2];

    private Paint scorePaint = new Paint();
    private Paint clickAlertPaint = new Paint();

    private int planeX = 50, planeY,planeSpeed;
    private int gasX,gasY,gasSpeed =20;
    private int rocketX, rocketY, rocketSpeed = 12;
    private int canvasWidth, canvasHeight,score,minPlaneY,maxPlaneY;
    private int lifeCounter = 3;

    private boolean touchStatus = false;
    private boolean startTouchStatus = false;

    private String clickAlert = "Tap to start";



    public FlyingPlaneView(Context context) {
        super(context);
        //Declare Bitmap
        gas = BitmapFactory.decodeResource(getResources(), R.drawable.oil);
        rocket = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rocket), 200, 200 ,false);

        //resize Bitmap
        plane[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane), 250, 250 ,false);
        plane[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.planeup), 250, 250 ,false);
        resize_gas = Bitmap.createScaledBitmap(gas, 150, 150 ,false);
        life[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.heart), 100, 100 ,false);
        life[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.greyheart), 100, 100 ,false);

        // Paint style
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        clickAlertPaint.setColor(Color.WHITE);
        clickAlertPaint.setTextSize(100);
        clickAlertPaint.setTypeface(Typeface.DEFAULT_BOLD);
        clickAlertPaint.setAntiAlias(true);

        planeY = 250;
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
        canvas.drawText("Score: "+score,70,100,scorePaint);

       //Display heart
       for( int j = 0 ; j <3 ; j++)
       {
           int heartX = (int) (780 + j*100);
           int heartY = 30;

           if(j < lifeCounter)
           {
               canvas.drawBitmap(life[0], heartX, heartY,null);
           }else
           {
               canvas.drawBitmap(life[1], heartX, heartY,null);
           }
       }

        //press to start
        if(startTouchStatus) {

            minPlaneY = plane[0].getHeight();
            maxPlaneY = canvasHeight - plane[0].getHeight() * 2;
            planeY = planeY + planeSpeed;

            planeSpeed = planeSpeed + 2;

            // plane position
            if(planeY < minPlaneY)
            {
                planeY = minPlaneY;
            }
            if(planeY > maxPlaneY)
            {
                planeY = maxPlaneY;
            }

            if (touchStatus) {
                canvas.drawBitmap(plane[1], planeX, planeY, null);
                touchStatus = false;
            } else {
                canvas.drawBitmap(plane[0], planeX, planeY, null);
            }
            //gas
            gas();
            canvas.drawBitmap(resize_gas, gasX, gasY, null);

            //rocket
            rocket();
            canvas.drawBitmap(rocket, rocketX, rocketY, null);

            clickAlert = "";
        }
        //Display alert
        canvas.drawText(clickAlert,450,1050,clickAlertPaint);
    }

    public void gas(){
        gasX = gasX - gasSpeed;

        if(hitRocketChecker(gasX,gasY))
        {
            score = score + 10;
            gasX = gasX - 500;
        }
        if(gasX < 0)
        {
            gasX = canvasWidth + 21;
            gasY = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
        }
    }

    public void rocket(){
        rocketX = rocketX - rocketSpeed;
        if(hitRocketChecker(rocketX,rocketY))
        {
            rocketX = rocketX - 500;
            lifeCounter --;
            if(lifeCounter == 0)
            {
                Toast.makeText(getContext(),"Game Over ", Toast.LENGTH_SHORT).show();
                Intent gameoverIntent = new Intent(getContext(), GameOverActivity.class);
                getContext().startActivity(gameoverIntent);
            }
        }
        if(rocketX< 0)
        {
            rocketX = canvasWidth + 21;
            rocketY = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
        }

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
            startTouchStatus = true;
            planeSpeed = -22;
        }

        return super.onTouchEvent(event);
    }
}
