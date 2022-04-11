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
    private double flying_speed_acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new FlyingPlaneView(this);
        try {
            Intent levelIntent=getIntent();
            flying_speed_acc=levelIntent.getDoubleExtra("Speed",0);
            gameView.setSpeed(flying_speed_acc);
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