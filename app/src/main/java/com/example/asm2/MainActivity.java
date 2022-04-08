package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private FlyingPlaneView gameView;
    private Handler handler = new Handler();
    private final static long Interval = 30;
    private Integer gameSpeed= 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new FlyingPlaneView(this);
        try {
            Intent levelIntent=getIntent();
            gameSpeed=levelIntent.getIntExtra("Speed",100);
            gameView.setSpeed(gameSpeed);
        }
        catch (Exception e){}
        setContentView(gameView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        },0,Interval);


    }
}