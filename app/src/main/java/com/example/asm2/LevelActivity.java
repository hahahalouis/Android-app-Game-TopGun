package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.logging.Level;

public class LevelActivity extends AppCompatActivity implements View.OnClickListener {

    TextView l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,btn_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        initObject();

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.tv_shop:
                shopclicked();
                break;
                case R.id.textView:
            case R.id.textView2:
            case R.id.textView3:
            case R.id.textView4:
            case R.id.textView5:
            case R.id.textView6:
            case R.id.textView7:
            case R.id.textView8:
            case R.id.textView9:
            case R.id.textView10:
            case R.id.textView11:
            case R.id.textView12:
            case R.id.textView13:
            case R.id.textView14:
            case R.id.textView15:
            case R.id.textView16:
            case R.id.textView17:
            case R.id.textView18:
            case R.id.textView19:
            case R.id.textView20:
            case R.id.textView21:
            case R.id.textView22:
            case R.id.textView23:
            case R.id.textView24:
            case R.id.textView25:
                try {
                    TextView selected_level=findViewById(view.getId());
                    goLevel(Integer.parseInt(selected_level.getText().toString()));
                } catch (Exception e) {
                    Log.e("Oh no", e.getMessage(),e);
                }
            default:

        }
    }

    public void shopclicked(){
        Intent shopIntent = new Intent(LevelActivity.this, ShopActivity.class);
        startActivity(shopIntent);
    }

    public void goLevel(Integer level) {
        //setting speed
        Integer flying_speed=96+level*4; //倍率
        //put the values
        Intent gameIntent = new Intent(LevelActivity.this,MainActivity.class);
        gameIntent.putExtra("Speed",flying_speed);
        startActivity(gameIntent);
    }

    public void initObject(){
        l1=findViewById(R.id.textView);
        l2=findViewById(R.id.textView2);
        l3=findViewById(R.id.textView3);
        l4=findViewById(R.id.textView4);
        l5=findViewById(R.id.textView5);
        l6=findViewById(R.id.textView6);
        l7=findViewById(R.id.textView7);
        l8=findViewById(R.id.textView8);
        l9=findViewById(R.id.textView9);
        l10=findViewById(R.id.textView10);
        l11=findViewById(R.id.textView11);
        l12=findViewById(R.id.textView12);
        l13=findViewById(R.id.textView13);
        l14=findViewById(R.id.textView14);
        l15=findViewById(R.id.textView15);
        l16=findViewById(R.id.textView16);
        l17=findViewById(R.id.textView17);
        l18=findViewById(R.id.textView18);
        l19=findViewById(R.id.textView19);
        l20=findViewById(R.id.textView20);
        l21=findViewById(R.id.textView21);
        l22=findViewById(R.id.textView22);
        l23=findViewById(R.id.textView23);
        l24=findViewById(R.id.textView24);
        l25=findViewById(R.id.textView25);
        btn_shop=findViewById(R.id.tv_shop);

        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        l5.setOnClickListener(this);
        l6.setOnClickListener(this);
        l7.setOnClickListener(this);
        l8.setOnClickListener(this);
        l9.setOnClickListener(this);
        l10.setOnClickListener(this);
        l12.setOnClickListener(this);
        l13.setOnClickListener(this);
        l14.setOnClickListener(this);
        l15.setOnClickListener(this);
        l16.setOnClickListener(this);
        l17.setOnClickListener(this);
        l18.setOnClickListener(this);
        l19.setOnClickListener(this);
        l20.setOnClickListener(this);
        l21.setOnClickListener(this);
        l22.setOnClickListener(this);
        l23.setOnClickListener(this);
        l24.setOnClickListener(this);
        l25.setOnClickListener(this);
        btn_shop.setOnClickListener(this);
    }
}
