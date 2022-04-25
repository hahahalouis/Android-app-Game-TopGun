package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.UiAutomation;
import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Thread thread = new Thread()
        {
            public void run(){
                try{
                    sleep(1000);
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    Intent prepareIntent = new Intent(StartActivity.this,meun.class);
                    startActivity(prepareIntent);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
