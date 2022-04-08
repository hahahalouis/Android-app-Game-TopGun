package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;

import java.nio.channels.InterruptedByTimeoutException;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        //temp
        Intent backtoLevel= new Intent(GameOverActivity.this, LevelActivity.class);
        startActivity(backtoLevel);
    }
}