package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_retry, btn_menu;
    TextView getScore_over;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
            btn_retry = findViewById(R.id.btn_retry);
            btn_menu = findViewById(R.id.btn_menu);

            btn_retry.setOnClickListener(this);
            btn_menu.setOnClickListener(this);

            getScore_over = findViewById(R.id.getScore_over);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("ShopSp", Context.MODE_PRIVATE);
        int score = sp.getInt("coinNum", 0);

        getScore_over.setText(String.valueOf(score));
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_retry:
                    Intent GameIntent = new Intent(getApplicationContext(), FlyingPlaneView.class);
                    getApplicationContext().startActivity(GameIntent);
                    break;

                case R.id.btn_menu:
                    Intent MenuIntent = new Intent(getApplicationContext(), LevelActivity.class);
                    startActivity(MenuIntent);
                    break;
            }
    }
}