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

    MainActivity mainActivity;

    private Bitmap bg,resize_bg,gas,resize_gas, rocket, pause, play;
    private Bitmap life[] = new Bitmap[2];
    private Bitmap plane[] = new Bitmap[8];

    private Paint scorePaint = new Paint();
    private Paint clickAlertPaint = new Paint();
    private Paint timerPaint = new Paint();
    private Paint tapPaint = new Paint();


    private int planeX = 50, planeY,planeSpeed;
    private int gasX,gasY,gasSpeed =18,gas2X,gas2Y,gas2Speed =18,gas3X,gas3Y,gas3Speed =18;
    private int speedForRocket=10;
    private int startX;
    private int canvasWidth, canvasHeight,score,minPlaneY,maxPlaneY;
    private int lifeCounter = 3;
    public int timerCLickStutas;
    private boolean cLickStutas=false;
    private int applyNum, level, targetScor;
    private int gamelevel=1;

    public int totalTime = 0;

    private boolean touchStatus = false;
    public boolean startTouchStatus = false;
    private boolean timerStutas =false;
    public boolean pauseStutas =true;
    private boolean playStutas =false;

    private String clickAlert = "Tap to start";
    private String clickTagAlert = "Score ";
    private String str_targetScor;
    private String levlNum,str_level = "Level ";
    public String timeText ="00.00";

    private SoundEffect sound;

    private double flying_speed_acc;

    SharedPreferences sp;

    private Rocket r1=new Rocket(),r2=new Rocket(),r3=new Rocket(),r4=new Rocket();
    private Gas g1=new Gas(),g2=new Gas(),g3=new Gas(),g4=new Gas();

    public class Rocket{
        public int rocketSpeed,rocketX,rocketY,startX;

        public Rocket(){
            this.rocketSpeed=speedForRocket;
        }

        public void fly(){
            this.rocketX = this.rocketX - this.rocketSpeed;

            if(hitRocketChecker(this.rocketX,this.rocketY))
            {
                this.rocketX = this.rocketX - 500;
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
            if(this.rocketX< 0)
            {   this.startX=(int) Math.floor((Math.random()*2000)+1);
                this.rocketX = canvasWidth + this.startX;
                this.rocketY = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
            }
        }
    }

    public class Gas{
        int gasX,gasY,gasSpeed=18,startX;

        public void fly(){
            this.gasX = this.gasX - this.gasSpeed;

            if(hitRocketChecker(this.gasX,this.gasY))
            {    winDetect();
                score = score + 10;
                this.gasX = this.gasX - 500;
                sound.playRewardSound();
            }
            if(this.gasX < 0)
            {   this.startX=(int) Math.floor((Math.random()*2000)+1);
                this.gasX = canvasWidth + this.startX;
                this.gasY = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
            }
        }

    }

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
        plane[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane3), 200, 200,false);
        plane[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane3_1), 200, 200,false);
        plane[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane4), 200, 200,false);
        plane[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane4_1), 200, 200,false);
        resize_gas = Bitmap.createScaledBitmap(gas, 100, 100 ,false);
        life[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.heart), 80, 80 ,false);
        life[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.greyheart), 80, 80 ,false);
        pause = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pause), 100, 100 ,false);
        play = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.play), 60, 60 ,false);

        // Paint style
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        tapPaint.setColor(Color.RED);
        tapPaint.setTextSize(100);
        tapPaint.setTypeface(Typeface.DEFAULT_BOLD);
        tapPaint.setAntiAlias(true);

        clickAlertPaint.setColor(Color.WHITE);
        clickAlertPaint.setTextSize(100);
        clickAlertPaint.setTypeface(Typeface.DEFAULT_BOLD);
        clickAlertPaint.setAntiAlias(true);

        planeY = 250;
        score = 0;
        targetScor = level*10+50;
        str_targetScor = Integer.toString(level*10+50);

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

        levlNum = Integer.toString(level);

    }

    public void setSpeed(double speed){
        flying_speed_acc = Math.floor(speed);
        speedForRocket=speedForRocket+ (int) flying_speed_acc;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        cLickStutas=false;
        countTar();
        targetScor = level*10+50;

        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        if(level<=5){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg);
        }else if(level<=10){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg2);
        }else if(level<=15){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingame9);
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

        //BG
        canvas.drawBitmap(resize_bg,0,0,null);
        //Score
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
        canvas.drawText(clickAlert,300,700,tapPaint);
        canvas.drawText(str_level + levlNum,370,800,clickAlertPaint);
        canvas.drawText(clickTagAlert + str_targetScor,340,900,clickAlertPaint);

        //start the game
        if(startTouchStatus && pauseStutas) {

            clickAlert = "";
            clickTagAlert = "";
            str_targetScor = "";
            str_level ="";
            levlNum = "";
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

            //Log.d("startX","startX:"+startX+" "+startX2+" "+startX3);

            switch(level){
                case 35:
                case 34:
                case 33:
                case 32:
                case 31:
                case 30:
                    g4.fly();
                    canvas.drawBitmap(resize_gas, g4.gasX, g4.gasY, null);
                    r4.fly();
                    canvas.drawBitmap(rocket, r4.rocketX, r4.rocketY, null);
                case 29:
                case 28:
                case 27:
                case 26:
                case 25:
                case 24:
                case 23:
                case 22:
                case 21:
                case 20:
                    g3.fly();
                    canvas.drawBitmap(resize_gas, g3.gasX, g3.gasY, null);
                    r3.fly();
                    canvas.drawBitmap(rocket, r3.rocketX, r3.rocketY, null);
                case 19:
                case 18:
                case 17:
                case 16:
                case 15:
                case 14:
                case 13:
                case 12:
                case 11:
                case 10:
                    g2.fly();
                    canvas.drawBitmap(resize_gas, g2.gasX, g2.gasY, null);
                    r2.fly();
                    canvas.drawBitmap(rocket, r2.rocketX, r2.rocketY, null);
                case 9:
                case 8:
                case 7:
                case 6:
                case 5:
                case 4:
                case 3:
                case 2:
                case 1:
                    g1.fly();
                    canvas.drawBitmap(resize_gas, g1.gasX, g1.gasY, null);
                    r1.fly();
                    canvas.drawBitmap(rocket, r1.rocketX, r1.rocketY, null);
                    break;
            }

            if(pauseStutas){
                canvas.drawBitmap(pause,860,130,null);
            }else{
                canvas.drawBitmap(play,890,150,null);
            }
        }else{
            canvas.drawBitmap(play,890,150,null);
        }
    }
    public void startTimer(){
        Timer timer = new Timer();
        if(timerCLickStutas == 1 && pauseStutas){
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    totalTime++;
                    timeText = getTimerText(totalTime);
                    Log.d("timerCancecl"," s: "+ totalTime);
                }
            };
            timer.schedule(timerTask,0,1000);
        }else if(pauseStutas == false && timerCLickStutas == 0){
            timer.cancel();
            Log.d("timerCancel","cancel");
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

    public void pauseChecker(float eventX, float eventY){
        if (eventX > 890 && eventX < 860 + 100 && eventY > 150 && eventY < 150 + 100 ){
            if(pauseStutas){
                pauseStutas = false;
            }else{
                pauseStutas = true;
//                timerCLickStutas = 1;
            }
        }
    }

    public String countTar(){
        if(timerCLickStutas== 0){
            str_targetScor = Integer.toString(targetScor);
        }
        return str_targetScor;
    }

    public void winDetect(){
        if(score >=targetScor){
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("Heart",lifeCounter);
            editor.putInt("coinNum",score);
            editor.commit();
            Intent winIntent = new Intent(getContext(), NextLevelActivity.class);
            getContext().startActivity(winIntent);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventX = (int)event.getX();
        int eventY = (int)event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touchStatus = true;
            startTouchStatus = true;
            planeSpeed = -22;
            timerCLickStutas++;
            cLickStutas=true;
            startTimer();
            pauseChecker(eventX,eventY);
            winDetect();
            Log.d("speed222","s"+speedForRocket);
            Log.d("through", ""+pauseStutas);
        }

        return super.onTouchEvent(event);
    }
}
