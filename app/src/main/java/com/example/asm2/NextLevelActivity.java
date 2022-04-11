package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NextLevelActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_again, btn_next, btn_menu2;
    ImageView star1, star2, star3;
    TextView getScore;
    private int level_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level);
        btn_again = findViewById(R.id.btn_again);
        btn_next = findViewById(R.id.btn_next);
        btn_menu2 = findViewById(R.id.btn_menu2);
        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        btn_next.setOnClickListener(this);
        btn_again.setOnClickListener(this);
        btn_menu2.setOnClickListener(this);
        getScore = findViewById(R.id.getScore);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("ShopSp", Context.MODE_PRIVATE);
        int score2 = sp.getInt("coinNum", 0);

        getScore.setText(String.valueOf(score2));

        level_next = sp.getInt("levelNum",0);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_again:
                Double flying_speed_acc=level_next*0.7; //min:100% max:280%
                //put the values
                Intent gameIntent = new Intent(getApplicationContext(),MainActivity.class);
                gameIntent.putExtra("Speed",flying_speed_acc);
                startActivity(gameIntent);
                break;
            case R.id.btn_next:
                Double flying_speed_acc2=(level_next+1)*0.7; //min:100% max:280%
                //put the values
                Intent nextIntent = new Intent(getApplicationContext(),MainActivity.class);
                nextIntent.putExtra("Speed",flying_speed_acc2);
                startActivity(nextIntent);
                break;
            case R.id.btn_menu2:
                Intent MenuIntent = new Intent(getApplicationContext(), LevelActivity.class);
                startActivity(MenuIntent);
                break;
        }
    }
}