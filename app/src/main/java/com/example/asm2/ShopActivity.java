package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton shopBackbtn,shopPlane1,shopPlane2;

    private TextView shopCoinNum;

    private View applyPlaneView;

    private Button shopPlanebtn1,shopPlanebtn2;

    private boolean buyStatus_1 = true , buyStatus_2 = false;
    private boolean applyStatus_1 = false, applyStatus_2 = true;

    private boolean tooglestutas_1 = true, tooglestutas_2 = true;

    private int coin_num;
    public int applyNum;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        init_();
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
                togglePlane1();
                break;
            case R.id.shopPlanebtn1:
                apply1();
                break;
            case R.id.shopPlane2:
                stuffStatus(buyStatus_2, applyStatus_2 ,shopPlanebtn2);
                togglePlane2();
                break;
            case R.id.shopPlanebtn2:
                if(!buyStatus_2){
                    buyPlane2();
                }
                apply2();
                break;
        }
    }


    public void init_(){
        shopBackbtn = findViewById(R.id.shopBackbtn);
        shopPlane1 = findViewById(R.id.shopPlane1);
        shopPlanebtn1 = findViewById(R.id.shopPlanebtn1);
        shopPlane2 = findViewById(R.id.shopPlane2);
        shopPlanebtn2 = findViewById(R.id.shopPlanebtn2);
        applyPlaneView = findViewById(R.id.applyPlaneView);
        shopCoinNum = findViewById(R.id.shopCoinNum);

        shopBackbtn.setOnClickListener(this);
        shopPlane1.setOnClickListener(this);
        shopPlanebtn1.setOnClickListener(this);
        shopPlane2.setOnClickListener(this);
        shopPlanebtn2.setOnClickListener(this);

        //SP
        sp = getSharedPreferences("ShopSp", Context.MODE_PRIVATE);
        coin_num = sp.getInt("coinNum",3);

//        setCoinNum();
    }

    public void stuffStatus(Boolean buyStatus, Boolean applyStatus, Button btn){
        SharedPreferences.Editor editor = sp.edit();
        boolean test = sp.getBoolean("buyStatus_2",false);

        if(buyStatus || test == true){
            btn.setText("Apply");
        }else{
            btn.setText("Buy");
        }
        btn.setVisibility(View.VISIBLE);
    }

    public void buyPlane2(){
        SharedPreferences.Editor editor = sp.edit();

            if(!buyStatus_2 && coin_num >= 100){
//                coin_num = coin_num - 100;
                buyStatus_2 = true;
                stuffStatus(buyStatus_2, applyStatus_2 ,shopPlanebtn2);
                //sp
                editor.putBoolean("buyStatus_2",buyStatus_2);
                editor.commit();

                Log.d("buy-stutas","Not sp");
                Log.d("coin",""+coin_num+" "+buyStatus_2);
            }else{
                Toast.makeText(getApplicationContext(),"Not enought menoy", Toast.LENGTH_SHORT).show();
            }
        }

    public void apply1(){
        SharedPreferences.Editor editor = sp.edit();
        if(buyStatus_1){
            applyPlaneView.setBackgroundResource(R.drawable.plane);
            editor.putString("applyNum","0");
            editor.commit();
            Toast.makeText(getApplicationContext(), "SP sf", Toast.LENGTH_SHORT).show();

            //testing
            Log.d("coinNum","From Fly "+ coin_num);

        }
    }

    public void apply2(){
        SharedPreferences.Editor editor = sp.edit();
        if(buyStatus_2){
            applyPlaneView.setBackgroundResource(R.drawable.plane2);
            editor.putString("applyNum","2");
            editor.commit();
        }
    }

    public void togglePlane1(){
        if(tooglestutas_1){
            tooglestutas_1 = false;
            shopPlanebtn1.setVisibility(View.VISIBLE);
        }else{
            tooglestutas_1 = true;
            shopPlanebtn1.setVisibility(View.INVISIBLE);
        }
    }

    public void togglePlane2(){
        if(tooglestutas_2){
            tooglestutas_2 = false;
            shopPlanebtn2.setVisibility(View.VISIBLE);
        }else{
            tooglestutas_2 = true;
            shopPlanebtn2.setVisibility(View.INVISIBLE);
        }
    }

    public void setCoinNum(){
        shopCoinNum.setText(coin_num);
    }


}