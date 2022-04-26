package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LevelActivity extends AppCompatActivity implements View.OnClickListener {

    TextView l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29,l30,l31,
            l32,l33,l34,l35,l36,l37,l38,l39,l40,l41,l42,l43,l44,l45,l46,l47,l48,l49,l50,l51,l52,l53,l54,l55,l56,l57,l58,l59,l60,
            l61,l62,l63,l64,l65,l66,l67,l68,l69,l70,l71,l72,l73,l74,l75,l76,l77,l78,l79,l80,l81,l82,l83,l84,l85,l86,l87,l88,l89,
            l90,l91,l92,l93,l94,l95,l96,l97,l98,l99,l100 ;
    TextView btn_shop,btn_custom;

    int lang_num;

    SharedPreferences sp;

    public int level_Num;

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
            case R.id.tv_custom:
                goDraw();
                break;
            case R.id.l1:
            case R.id.l2:
            case R.id.l3:
            case R.id.l4:
            case R.id.l5:
            case R.id.l6:
            case R.id.l7:
            case R.id.l8:
            case R.id.l9:
            case R.id.l10:
            case R.id.l11:
            case R.id.l12:
            case R.id.l13:
            case R.id.l14:
            case R.id.l15:
            case R.id.l16:
            case R.id.l17:
            case R.id.l18:
            case R.id.l19:
            case R.id.l20:
            case R.id.l21:
            case R.id.l22:
            case R.id.l23:
            case R.id.l24:
            case R.id.l25:
            case R.id.l26:
            case R.id.l27:
            case R.id.l28:
            case R.id.l29:
            case R.id.l30:
            case R.id.l31:
            case R.id.l32:
            case R.id.l33:
            case R.id.l34:
            case R.id.l35:
            case R.id.l36:
            case R.id.l37:
            case R.id.l38:
            case R.id.l39:
            case R.id.l40:
            case R.id.l41:
            case R.id.l42:
            case R.id.l43:
            case R.id.l44:
            case R.id.l45:
            case R.id.l46:
            case R.id.l47:
            case R.id.l48:
            case R.id.l49:
            case R.id.l50:
            case R.id.l51:
            case R.id.l52:
            case R.id.l53:
            case R.id.l54:
            case R.id.l55:
            case R.id.l56:
            case R.id.l57:
            case R.id.l58:
            case R.id.l59:
            case R.id.l60:
            case R.id.l61:
            case R.id.l62:
            case R.id.l63:
            case R.id.l64:
            case R.id.l65:
            case R.id.l66:
            case R.id.l67:
            case R.id.l68:
            case R.id.l69:
            case R.id.l70:
            case R.id.l71:
            case R.id.l72:
            case R.id.l73:
            case R.id.l74:
            case R.id.l75:
            case R.id.l76:
            case R.id.l77:
            case R.id.l78:
            case R.id.l79:
            case R.id.l80:
            case R.id.l81:
            case R.id.l82:
            case R.id.l83:
            case R.id.l84:
            case R.id.l85:
            case R.id.l86:
            case R.id.l87:
            case R.id.l88:
            case R.id.l89:
            case R.id.l90:
            case R.id.l91:
            case R.id.l92:
            case R.id.l93:
            case R.id.l94:
            case R.id.l95:
            case R.id.l96:
            case R.id.l97:
            case R.id.l98:
            case R.id.l99:
            case R.id.l100:
                try {
                    SharedPreferences.Editor editor = sp.edit();

                    TextView selected_level=findViewById(view.getId());
                    level_Num = Integer.parseInt(selected_level.getText().toString());
                    goLevel(level_Num);

                    editor.putInt("levelNum",level_Num);
                    editor.commit();
                    Log.d("level_num",":"+level_Num);

                } catch (Exception e) {
                    Log.e("Oh no", e.getMessage(),e);
                }
        }
    }

    public void shopclicked(){
        Intent shopIntent = new Intent(LevelActivity.this, ShopActivity.class);
        startActivity(shopIntent);
    }

    public void goLevel(Integer level) {
        //setting speed

        Double flying_speed_acc=level*0.7; // 1lv+ 0.7 speed
        //put the values
        Intent gameIntent = new Intent(LevelActivity.this,MainActivity.class);
        gameIntent.putExtra("Speed",flying_speed_acc);
        startActivity(gameIntent);
    }

    public void goDraw() {
        Intent DrawIntent = new Intent(LevelActivity.this, canvasActivity.class);
        startActivity(DrawIntent);
    }

    public void changeLang(){
        lang_num = sp.getInt("lang_num",0);
        if(lang_num == 0){
            btn_shop.setText("Shop");
            btn_custom.setText("Draw");
        }else{
            btn_shop.setText("商店");
            btn_custom.setText("繪畫");
        }
    }

    public void initObject(){
        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        l3=findViewById(R.id.l3);
        l4=findViewById(R.id.l4);
        l5=findViewById(R.id.l5);
        l6=findViewById(R.id.l6);
        l7=findViewById(R.id.l7);
        l8=findViewById(R.id.l8);
        l9=findViewById(R.id.l9);
        l10=findViewById(R.id.l10);
        l11=findViewById(R.id.l11);
        l12=findViewById(R.id.l12);
        l13=findViewById(R.id.l13);
        l14=findViewById(R.id.l14);
        l15=findViewById(R.id.l15);
        l16=findViewById(R.id.l16);
        l17=findViewById(R.id.l17);
        l18=findViewById(R.id.l18);
        l19=findViewById(R.id.l19);
        l20=findViewById(R.id.l20);
        l21=findViewById(R.id.l21);
        l22=findViewById(R.id.l22);
        l23=findViewById(R.id.l23);
        l24=findViewById(R.id.l24);
        l25=findViewById(R.id.l25);
        l26=findViewById(R.id.l26);
        l27=findViewById(R.id.l27);
        l28=findViewById(R.id.l28);
        l29=findViewById(R.id.l29);
        l30=findViewById(R.id.l30);
        l31=findViewById(R.id.l31);
        l32=findViewById(R.id.l32);
        l33=findViewById(R.id.l33);
        l34=findViewById(R.id.l34);
        l35=findViewById(R.id.l35);
        l36=findViewById(R.id.l36);
        l37=findViewById(R.id.l37);
        l38=findViewById(R.id.l38);
        l39=findViewById(R.id.l39);
        l40=findViewById(R.id.l40);
        l41=findViewById(R.id.l41);
        l42=findViewById(R.id.l42);
        l43=findViewById(R.id.l43);
        l44=findViewById(R.id.l44);
        l45=findViewById(R.id.l45);
        l46=findViewById(R.id.l46);
        l47=findViewById(R.id.l47);
        l48=findViewById(R.id.l48);
        l49=findViewById(R.id.l49);
        l50=findViewById(R.id.l50);
        l51=findViewById(R.id.l51);
        l52=findViewById(R.id.l52);
        l53=findViewById(R.id.l53);
        l54=findViewById(R.id.l54);
        l55=findViewById(R.id.l55);
        l56=findViewById(R.id.l56);
        l57=findViewById(R.id.l57);
        l58=findViewById(R.id.l58);
        l59=findViewById(R.id.l59);
        l60=findViewById(R.id.l60);
        l61=findViewById(R.id.l61);
        l62=findViewById(R.id.l62);
        l63=findViewById(R.id.l63);
        l64=findViewById(R.id.l64);
        l65=findViewById(R.id.l65);
        l66=findViewById(R.id.l66);
        l67=findViewById(R.id.l67);
        l68=findViewById(R.id.l68);
        l69=findViewById(R.id.l69);
        l70=findViewById(R.id.l70);
        l71=findViewById(R.id.l71);
        l72=findViewById(R.id.l72);
        l73=findViewById(R.id.l73);
        l74=findViewById(R.id.l74);
        l75=findViewById(R.id.l75);
        l76=findViewById(R.id.l76);
        l77=findViewById(R.id.l77);
        l78=findViewById(R.id.l78);
        l79=findViewById(R.id.l79);
        l80=findViewById(R.id.l80);
        l81=findViewById(R.id.l81);
        l82=findViewById(R.id.l82);
        l83=findViewById(R.id.l83);
        l84=findViewById(R.id.l84);
        l85=findViewById(R.id.l85);
        l86=findViewById(R.id.l86);
        l87=findViewById(R.id.l87);
        l88=findViewById(R.id.l88);
        l88=findViewById(R.id.l88);
        l89=findViewById(R.id.l89);
        l90=findViewById(R.id.l90);
        l91=findViewById(R.id.l91);
        l92=findViewById(R.id.l92);
        l93=findViewById(R.id.l93);
        l94=findViewById(R.id.l94);
        l95=findViewById(R.id.l95);
        l96=findViewById(R.id.l96);
        l97=findViewById(R.id.l97);
        l98=findViewById(R.id.l98);
        l99=findViewById(R.id.l99);
        l100=findViewById(R.id.l100);
        btn_shop=findViewById(R.id.tv_shop);
        btn_custom=findViewById(R.id.tv_custom);
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
        l11.setOnClickListener(this);
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
        l26.setOnClickListener(this);
        l27.setOnClickListener(this);
        l28.setOnClickListener(this);
        l29.setOnClickListener(this);
        l30.setOnClickListener(this);
        l31.setOnClickListener(this);
        l32.setOnClickListener(this);
        l33.setOnClickListener(this);
        l34.setOnClickListener(this);
        l35.setOnClickListener(this);
        l36.setOnClickListener(this);
        l37.setOnClickListener(this);
        l38.setOnClickListener(this);
        l39.setOnClickListener(this);
        l40.setOnClickListener(this);
        l41.setOnClickListener(this);
        l42.setOnClickListener(this);
        l43.setOnClickListener(this);
        l44.setOnClickListener(this);
        l45.setOnClickListener(this);
        l46.setOnClickListener(this);
        l47.setOnClickListener(this);
        l48.setOnClickListener(this);
        l49.setOnClickListener(this);
        l50.setOnClickListener(this);
        l51.setOnClickListener(this);
        l52.setOnClickListener(this);
        l53.setOnClickListener(this);
        l54.setOnClickListener(this);
        l55.setOnClickListener(this);
        l56.setOnClickListener(this);
        l57.setOnClickListener(this);
        l58.setOnClickListener(this);
        l59.setOnClickListener(this);
        l60.setOnClickListener(this);
        l61.setOnClickListener(this);
        l62.setOnClickListener(this);
        l63.setOnClickListener(this);
        l64.setOnClickListener(this);
        l65.setOnClickListener(this);
        l66.setOnClickListener(this);
        l67.setOnClickListener(this);
        l68.setOnClickListener(this);
        l69.setOnClickListener(this);
        l70.setOnClickListener(this);
        l71.setOnClickListener(this);
        l72.setOnClickListener(this);
        l73.setOnClickListener(this);
        l74.setOnClickListener(this);
        l75.setOnClickListener(this);
        l76.setOnClickListener(this);
        l77.setOnClickListener(this);
        l78.setOnClickListener(this);
        l79.setOnClickListener(this);
        l80.setOnClickListener(this);
        l81.setOnClickListener(this);
        l82.setOnClickListener(this);
        l83.setOnClickListener(this);
        l84.setOnClickListener(this);
        l85.setOnClickListener(this);
        l86.setOnClickListener(this);
        l87.setOnClickListener(this);
        l88.setOnClickListener(this);
        l89.setOnClickListener(this);
        l90.setOnClickListener(this);
        l91.setOnClickListener(this);
        l92.setOnClickListener(this);
        l93.setOnClickListener(this);
        l94.setOnClickListener(this);
        l95.setOnClickListener(this);
        l96.setOnClickListener(this);
        l97.setOnClickListener(this);
        l98.setOnClickListener(this);
        l99.setOnClickListener(this);
        l100.setOnClickListener(this);
        btn_shop.setOnClickListener(this);
        btn_custom.setOnClickListener(this);
        //sp
        sp = getSharedPreferences("ShopSp", Context.MODE_PRIVATE);


        changeLang();
    }
}
