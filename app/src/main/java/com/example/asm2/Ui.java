package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Ui extends AppCompatActivity implements View.OnClickListener {
    Button on1, on2,btn_shop;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        initObject();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shop:
                shopclicked();
                break;
            case R.id.on1:
                on1clicked();
                break;
            case R.id.on2:
                on2clicked();
                break;
        }
    }

        public void shopclicked(){
            Intent shopIntent = new Intent(Ui.this, ShopActivity.class);
            startActivity(shopIntent);
        }
        public void on1clicked(){
            Intent on1Intent = new Intent(Ui.this, PrepareActivity.class);
            startActivity(on1Intent);
    }
        public void on2clicked(){
            Intent on2Intent = new Intent(Ui.this, LevelActivity.class);
            startActivity(on2Intent);
    }

        public void initObject(){
        on1 = findViewById(R.id.on1);
        on2 = findViewById(R.id.on2);
        btn_shop = findViewById(R.id.tv_shop);

        on1.setOnClickListener(this);
        on2.setOnClickListener(this);
        btn_shop.setOnClickListener(this);
        sp = getSharedPreferences("ShopSp", Context.MODE_PRIVATE);
    }
}









