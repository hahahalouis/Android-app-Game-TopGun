package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    Button on1, on2,btn_shop;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        on1 = findViewById(R.id.on1);
        on2 = findViewById(R.id.on2);
        btn_shop = findViewById(R.id.tv1_shop);

        on1.setOnClickListener(this);
        on2.setOnClickListener(this);
        btn_shop.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1_shop:
                Intent ShopIntent = new Intent(getApplicationContext(), ShopActivity.class);
                startActivity(ShopIntent);
                break;

            case R.id.on1:
                Intent gameIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(gameIntent);
                break;

            case R.id.on2:
                Intent On2Intent = new Intent(getApplicationContext(), LevelActivity.class);
                startActivity(On2Intent);
                break;
        }
    }
}