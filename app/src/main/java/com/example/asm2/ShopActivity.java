package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton shopBackbtn,shopPlane1,shopPlane2;

    private View applyPlaneView;

    private Button shopPlanebtn1,shopPlanebtn2;

    private boolean buyStatus_1 = false , buyStatus_2 = true;
    private boolean applyStatus_1 = false, applyStatus_2 = true;
    private boolean toggleStutas_1;

    private int coin_num = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        shopBackbtn = findViewById(R.id.shopBackbtn);
        shopPlane1 = findViewById(R.id.shopPlane1);
        shopPlanebtn1 = findViewById(R.id.shopPlanebtn1);
        shopPlane2 = findViewById(R.id.shopPlane2);
        shopPlanebtn2 = findViewById(R.id.shopPlanebtn2);
        applyPlaneView = findViewById(R.id.applyPlaneView);

        shopBackbtn.setOnClickListener(this);
        shopPlane1.setOnClickListener(this);
        shopPlanebtn1.setOnClickListener(this);
        shopPlane2.setOnClickListener(this);
        shopPlanebtn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shopBackbtn:
                Intent shopintent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(shopintent);
                break;
            case R.id.shopPlane1:
                stuffStatus(buyStatus_1, applyStatus_1 ,shopPlanebtn1);
                break;
            case R.id.shopPlanebtn1:
                if(!buyStatus_1){
                    buy(buyStatus_1, coin_num,applyStatus_1, shopPlanebtn1);
                }
                Log.d("buystutas",""+buyStatus_1);
                apply1();
                break;
        }
    }


    public void stuffStatus(Boolean buyStatus, Boolean applyStatus, Button btn){
        if(buyStatus){
            btn.setText("Apply");
        }else{
            btn.setText("Buy");
        }
        btn.setVisibility(View.VISIBLE);
    }

    public void buy(Boolean buyStatus, int coin_num, Boolean applyStatus, Button btn){
        if(!buyStatus && coin_num >= 100){
            coin_num = coin_num - 100;
            buyStatus = true;
            stuffStatus(buyStatus, applyStatus ,btn);
            Log.d("coin",""+coin_num+" "+buyStatus);
        }else{
            Toast.makeText(getApplicationContext(),"Not enought menoy", Toast.LENGTH_SHORT).show();
        }
    }

    public void apply1(){
        if(buyStatus_1){
            applyPlaneView.setBackgroundResource(R.drawable.planeup);
        }
    }

    public void apply2(){
        if(buyStatus_2){
            applyPlaneView.setBackgroundResource(R.drawable.plane);
        }
    }


}