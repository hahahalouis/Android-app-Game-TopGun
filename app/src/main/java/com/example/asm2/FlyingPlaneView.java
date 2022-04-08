package com.example.asm2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import android.os.Handler;

public class FlyingPlaneView extends View{

    private Bitmap bg,resize_bg,gas,resize_gas, rocket;
    private Bitmap life[] = new Bitmap[2];
    private Bitmap plane[] = new Bitmap[2];

    private Paint scorePaint = new Paint();
    private Paint clickAlertPaint = new Paint();
    private Paint timerPaint = new Paint();


    private int planeX = 50, planeY,planeSpeed;
    private int gasX,gasY,gasSpeed =18;
    private int rocketX, rocketY, rocketSpeed = 10;
    private int canvasWidth, canvasHeight,score,minPlaneY,maxPlaneY;
    private int lifeCounter = 3;
    private int timerCLickStutas;
    private boolean cLickStutas=false;
    private int applyNum;

    private int totalTime = 0;

    private boolean touchStatus = false;
    private boolean startTouchStatus = false;
    private boolean timerStutas =false;

    private String clickAlert = "Tap to start";
    private String timeText ="00.00";

    private SoundEffect sound;

    private Integer gameSpeed=100;

    public FlyingPlaneView(Context context) {
        super(context);

        //sound
        sound = new SoundEffect(context);
        //Declare Bitmap
        gas = BitmapFactory.decodeResource(getResources(), R.drawable.oil);
        rocket = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rocket), 150, 150 ,false);

        //resize Bitmap
        plane[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane), 200, 200 ,false);
        plane[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane1_1), 200, 200,false);
        resize_gas = Bitmap.createScaledBitmap(gas, 100, 100 ,false);
        life[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.heart), 80, 80 ,false);
        life[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.greyheart), 80, 80 ,false);

        // Paint style
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        clickAlertPaint.setColor(Color.WHITE);
        clickAlertPaint.setTextSize(100);
        clickAlertPaint.setTypeface(Typeface.DEFAULT_BOLD);
        clickAlertPaint.setAntiAlias(true);

        timerPaint.setColor(Color.WHITE);
        timerPaint.setTextSize(50);

        planeY = 250;
        score = 0;

        //bottom
        Timer timer2 = new Timer();
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                bottom();
            }
        };
        timer2.schedule(timerTask2,0,250);

        //SP
        SharedPreferences sp = context.getSharedPreferences("ShopSp",Context.MODE_PRIVATE);
        applyNum = Integer.parseInt(sp.getString("applyNum",""));


    }

    public void setSpeed(Integer speed){
        gameSpeed = speed;
        rocketSpeed=(rocketSpeed*gameSpeed)/100;
        gasSpeed=(gasSpeed*gameSpeed)/100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        cLickStutas=false;

        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg);
        resize_bg = Bitmap.createScaledBitmap(bg, canvasWidth,canvasHeight,false);

        canvas.drawBitmap(resize_bg,0,0,null);
        canvas.drawText("Score: "+score,70,100,scorePaint);

       //Display heart
       for( int j = 0 ; j <3 ; j++)
       {
           int heartX = (int) (700 + j*100);
           int heartY = 50;

           if(j < lifeCounter)
           {
               canvas.drawBitmap(life[0], heartX, heartY,null);
           }else
           {
               canvas.drawBitmap(life[1], heartX, heartY,null);
           }
       }

       //Display timer
        canvas.drawText(timeText,450,200,scorePaint);

        //Display alert
        canvas.drawText(clickAlert,300,900,clickAlertPaint);

        //start the game
        if(startTouchStatus) {

            clickAlert = "";
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

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        touchStatus = false;
                    }
                }, 200);


            } else {

              canvas.drawBitmap(plane[0], planeX, planeY, null);

            }

            //gas
            gas();
            canvas.drawBitmap(resize_gas, gasX, gasY, null);

            //rocket
            rocket();
            canvas.drawBitmap(rocket, rocketX, rocketY, null);

        }
    }

    public void gas(){
        gasX = gasX - gasSpeed;

        if(hitRocketChecker(gasX,gasY))
        {
            score = score + 10;
            gasX = gasX - 500;
            sound.playRewardSound();
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
            sound.playHitSound();
            if(lifeCounter == 0)
            {
                sound.playOverSound();
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

    public void bottom(){

        if(planeY==maxPlaneY && score>=10 && cLickStutas==false)
        {
          score = score - 10;

          sound.playBottomSound();
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

    public void startTimer(){
       Timer timer = new Timer();
       if(timerCLickStutas == 1){
           TimerTask timerTask = new TimerTask() {
               @Override
               public void run() {
                   totalTime++;
                   timeText = getTimerText(totalTime);
                   Log.d("timer"," s: "+ totalTime);
               }
           };
           timer.schedule(timerTask,0,1000);
       }
    }

    public String getTimerText(int time){


        int seconds = time %  60;
        int minutes = (time % 3600) / 60;

        Log.d("timer2"," m: "+ minutes +" s: "+ seconds);


        return  formatTime(seconds,minutes);
    }

    public String formatTime(int seconds,int minutes){
        return String.format("%02d",minutes) +" : "+ String.format("%02d",seconds);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touchStatus = true;
            startTouchStatus = true;
            planeSpeed = -(22*gameSpeed)/100;
            timerCLickStutas++;
            cLickStutas=true;
            startTimer();
            Log.d("time4",""+timerCLickStutas);
        }

        return super.onTouchEvent(event);
    }
}
