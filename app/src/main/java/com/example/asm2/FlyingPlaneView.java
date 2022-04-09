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
import android.os.Handler;

public class FlyingPlaneView extends View{

    private Bitmap bg,resize_bg,gas,resize_gas, rocket;
    private Bitmap life[] = new Bitmap[2];
    private Bitmap plane[] = new Bitmap[4];

    private Paint scorePaint = new Paint();
    private Paint clickAlertPaint = new Paint();
    private Paint timerPaint = new Paint();


    private int planeX = 50, planeY,planeSpeed;
    private int gasX,gasY,gasSpeed =18,gas2X,gas2Y,gas2Speed =18,gas3X,gas3Y,gas3Speed =18;
    private int rocketX, rocketY, rocketSpeed = 10,rocket2X, rocket2Y, rocket2Speed = 10,rocket3X, rocket3Y, rocket3Speed = 10;
    private int canvasWidth, canvasHeight,score,minPlaneY,maxPlaneY;
    private int lifeCounter = 3;
    private int timerCLickStutas;
    private boolean cLickStutas=false;
    private int applyNum, level;
    private int gamelevel=1;

    private int totalTime = 0;

    private boolean touchStatus = false;
    private boolean startTouchStatus = false;
    private boolean timerStutas =false;

    private String clickAlert = "Tap to start";
    private String timeText ="00.00";

    private SoundEffect sound;

    private Integer gameSpeed=100;

    SharedPreferences sp;

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
        plane[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane2), 200, 200 ,false);
        plane[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane2_1), 200, 200,false);
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
        sp = context.getSharedPreferences("ShopSp",Context.MODE_PRIVATE);

        //GetSap
        applyNum = sp.getInt("applyNum",0);
        level = sp.getInt("levelNum",0);
    }

    public void setSpeed(Integer speed){
        gameSpeed = speed;
        rocketSpeed=(rocketSpeed*gameSpeed)/100;
        gasSpeed=(gasSpeed*gameSpeed)/100;
        gamelevel=gameSpeed/8;
        if(level>5){
            if(level<=10){
                //set background2
            }else if(level<=15){
                //set background3
            }
            else if(level<=16){
                //set background4
            }
            else if(level<=20){
                //set background5
            }
            else if(level<=25){
                //set background6
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        cLickStutas=false;

        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        if(level<=5){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg);
        }else if(level<=10){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg2);
        }else if(level<=15){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg3);
        }else if(level<=20){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg4);
        }else if(level<=25){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg5);
        }else if(level<=30){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg6);
        }else if(level<=35){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg7);
        }
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
            minPlaneY = plane[applyNum].getHeight();
            maxPlaneY = canvasHeight - plane[applyNum].getHeight() * 2;
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
                canvas.drawBitmap(plane[applyNum+1], planeX, planeY, null);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        touchStatus = false;
                    }
                }, 200);
            } else {
                canvas.drawBitmap(plane[applyNum], planeX, planeY, null);
            }

            //gas
            gas();
            canvas.drawBitmap(resize_gas, gasX, gasY, null);

            //rocket
            rocket();
            canvas.drawBitmap(rocket, rocketX, rocketY, null);

            if(gamelevel>=15) {
                if(gamelevel<25) {
                    gas2();
                    canvas.drawBitmap(resize_gas, gas2X, gas2Y, null);
                    rocket2();
                    canvas.drawBitmap(rocket, rocket2X, rocket2Y, null);
                }
                else{
                    gas2();
                    canvas.drawBitmap(resize_gas, gas2X, gas2Y, null);
                    gas3();
                    canvas.drawBitmap(resize_gas, gas3X, gas3Y, null);
                    rocket2();
                    canvas.drawBitmap(rocket, rocket2X, rocket2Y, null);
                    rocket3();
                    canvas.drawBitmap(rocket, rocket3X, rocket3Y, null);
                }
            }
        }
    }

    public void winDetect(){
        if(score >=40){
            Intent winIntent = new Intent(getContext(), NextLevelActivity.class);
            getContext().startActivity(winIntent);
        }
    }



    public void gas(){
        gasX = gasX - gasSpeed;

        if(hitRocketChecker(gasX,gasY))
        {    winDetect();
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
                SharedPreferences.Editor editor = sp.edit();
                sound.playOverSound();
                Toast.makeText(getContext(),"Game Over ", Toast.LENGTH_SHORT).show();
                //sp
                String str_coinNum = Integer.toString(score);
                editor.putInt("coinNum",score);
                editor.commit();
                Log.d("Flysp","though rocket");

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

    public void gas2(){
        gas2X = gas2X - gas2Speed;

        if(hitRocketChecker(gas2X,gas2Y))
        {
            score = score + 10;
            gas2X = gas2X - 500;
            sound.playRewardSound();
        }
        if(gas2X < 0)
        {
            gas2X = canvasWidth + 21;
            gas2Y = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
        }
    }

    public void rocket2(){
        rocket2X = rocket2X - rocket2Speed;
        if(hitRocketChecker(rocket2X,rocket2Y))
        {
            rocket2X = rocket2X - 500;
            lifeCounter --;
            sound.playHitSound();
            if(lifeCounter == 0)
            {
                SharedPreferences.Editor editor = sp.edit();
                sound.playOverSound();
                Toast.makeText(getContext(),"Game Over ", Toast.LENGTH_SHORT).show();
                //sp
                String str_coinNum = Integer.toString(score);
                editor.putInt("coinNum",score);
                editor.commit();
                Log.d("Flysp","though rocket");

                Intent gameoverIntent = new Intent(getContext(), GameOverActivity.class);
                getContext().startActivity(gameoverIntent);
            }
        }
        if(rocket2X< 0)
        {
            rocket2X = canvasWidth + 21;
            rocket2Y = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
        }

    }

    public void gas3(){
        gas3X = gas3X - gas3Speed;

        if(hitRocketChecker(gas3X,gas3Y))
        {
            score = score + 10;
            gas3X = gas3X - 500;
            sound.playRewardSound();
        }
        if(gas3X < 0)
        {
            gas3X = canvasWidth + 21;
            gas3Y = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
        }
    }

    public void rocket3(){
        rocket3X = rocket3X - rocket3Speed;
        if(hitRocketChecker(rocket3X,rocket3Y))
        {
            rocket3X = rocket3X - 500;
            lifeCounter --;
            sound.playHitSound();
            if(lifeCounter == 0)
            {
                SharedPreferences.Editor editor = sp.edit();
                sound.playOverSound();
                Toast.makeText(getContext(),"Game Over ", Toast.LENGTH_SHORT).show();
                //sp
                String str_coinNum = Integer.toString(score);
                editor.putInt("coinNum",score);
                editor.commit();
                Log.d("Flysp","though rocket");

                Intent gameoverIntent = new Intent(getContext(), GameOverActivity.class);
                getContext().startActivity(gameoverIntent);
            }
        }
        if(rocket3X< 0)
        {
            rocket3X = canvasWidth + 21;
            rocket3Y = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
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


        if((planeX < x && x < (planeX + plane[applyNum].getWidth()) && planeY < y && y < (planeY + plane[applyNum].getHeight())) || (planeX < x && x < (planeX + plane[applyNum+1].getWidth()) && planeY < y && y < (planeY + plane[applyNum+1].getHeight())))
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
            planeSpeed = -22;
            timerCLickStutas++;
            cLickStutas=true;
            startTimer();
        }

        return super.onTouchEvent(event);
    }
}
