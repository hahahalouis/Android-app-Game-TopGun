package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NextLevelActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_again, btn_next, btn_menu2;
    ImageView star1, star2, star3;
    TextView getScore;

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
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_again:
                Intent GameIntent = new Intent(getApplicationContext(), FlyingPlaneView.class);
                getApplicationContext().startActivity(GameIntent);
                break;
            case R.id.btn_next:
                //Intent NextIntent = new Intent(getApplicationContext(), FlyingPlaneView.class);
                //getApplicationContext().startActivity(NextIntent);
                break;
            case R.id.btn_menu2:
                Intent MenuIntent = new Intent(getApplicationContext(), LevelActivity.class);
                getApplicationContext().startActivity(MenuIntent);
                break;
        }
    }
}