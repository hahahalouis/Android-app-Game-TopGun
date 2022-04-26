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
            l90,l91,l92,l93,l94,l95,l96,l97,l98,l99,l100;
    TextView btn_shop,btn_custom;

    int lang_num;

    TextView[] levelsView=new TextView[101];

    SharedPreferences sp;

    public Integer level_Num;

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



    public void initObject(){
        levelsView[0]=null;
        levelsView[1]=findViewById(R.id.l1);
        levelsView[2]=findViewById(R.id.l2);
        levelsView[3]=findViewById(R.id.l3);
        levelsView[4]=findViewById(R.id.l4);
        levelsView[5]=findViewById(R.id.l5);
        levelsView[6]=findViewById(R.id.l6);
        levelsView[7]=findViewById(R.id.l7);
        levelsView[8]=findViewById(R.id.l8);
        levelsView[9]=findViewById(R.id.l9);
        levelsView[10]=findViewById(R.id.l10);
        levelsView[11]=findViewById(R.id.l11);
        levelsView[12]=findViewById(R.id.l12);
        levelsView[13]=findViewById(R.id.l13);
        levelsView[14]=findViewById(R.id.l14);
        levelsView[15]=findViewById(R.id.l15);
        levelsView[16]=findViewById(R.id.l16);
        levelsView[17]=findViewById(R.id.l17);
        levelsView[18]=findViewById(R.id.l18);
        levelsView[19]=findViewById(R.id.l19);
        levelsView[20]=findViewById(R.id.l20);
        levelsView[21]=findViewById(R.id.l21);
        levelsView[22]=findViewById(R.id.l22);
        levelsView[23]=findViewById(R.id.l23);
        levelsView[24]=findViewById(R.id.l24);
        levelsView[25]=findViewById(R.id.l25);
        levelsView[26]=findViewById(R.id.l26);
        levelsView[27]=findViewById(R.id.l27);
        levelsView[28]=findViewById(R.id.l28);
        levelsView[29]=findViewById(R.id.l29);
        levelsView[30]=findViewById(R.id.l30);
        levelsView[31]=findViewById(R.id.l31);
        levelsView[32]=findViewById(R.id.l32);
        levelsView[33]=findViewById(R.id.l33);
        levelsView[34]=findViewById(R.id.l34);
        levelsView[35]=findViewById(R.id.l35);
        levelsView[36]=findViewById(R.id.l36);
        levelsView[37]=findViewById(R.id.l37);
        levelsView[38]=findViewById(R.id.l38);
        levelsView[39]=findViewById(R.id.l39);
        levelsView[40]=findViewById(R.id.l40);
        levelsView[41]=findViewById(R.id.l41);
        levelsView[42]=findViewById(R.id.l42);
        levelsView[43]=findViewById(R.id.l43);
        levelsView[44]=findViewById(R.id.l44);
        levelsView[45]=findViewById(R.id.l45);
        levelsView[46]=findViewById(R.id.l46);
        levelsView[47]=findViewById(R.id.l47);
        levelsView[48]=findViewById(R.id.l48);
        levelsView[49]=findViewById(R.id.l49);
        levelsView[50]=findViewById(R.id.l50);
        levelsView[51]=findViewById(R.id.l51);
        levelsView[52]=findViewById(R.id.l52);
        levelsView[53]=findViewById(R.id.l53);
        levelsView[54]=findViewById(R.id.l54);
        levelsView[55]=findViewById(R.id.l55);
        levelsView[56]=findViewById(R.id.l56);
        levelsView[57]=findViewById(R.id.l57);
        levelsView[58]=findViewById(R.id.l58);
        levelsView[59]=findViewById(R.id.l59);
        levelsView[60]=findViewById(R.id.l60);
        levelsView[61]=findViewById(R.id.l61);
        levelsView[62]=findViewById(R.id.l62);
        levelsView[63]=findViewById(R.id.l63);
        levelsView[64]=findViewById(R.id.l64);
        levelsView[65]=findViewById(R.id.l65);
        levelsView[66]=findViewById(R.id.l66);
        levelsView[67]=findViewById(R.id.l67);
        levelsView[68]=findViewById(R.id.l68);
        levelsView[69]=findViewById(R.id.l69);
        levelsView[70]=findViewById(R.id.l70);
        levelsView[71]=findViewById(R.id.l71);
        levelsView[72]=findViewById(R.id.l72);
        levelsView[73]=findViewById(R.id.l73);
        levelsView[74]=findViewById(R.id.l74);
        levelsView[75]=findViewById(R.id.l75);
        levelsView[76]=findViewById(R.id.l76);
        levelsView[77]=findViewById(R.id.l77);
        levelsView[78]=findViewById(R.id.l78);
        levelsView[79]=findViewById(R.id.l79);
        levelsView[80]=findViewById(R.id.l80);
        levelsView[81]=findViewById(R.id.l81);
        levelsView[82]=findViewById(R.id.l82);
        levelsView[83]=findViewById(R.id.l83);
        levelsView[84]=findViewById(R.id.l84);
        levelsView[85]=findViewById(R.id.l85);
        levelsView[86]=findViewById(R.id.l86);
        levelsView[87]=findViewById(R.id.l87);
        levelsView[88]=findViewById(R.id.l88);
        levelsView[88]=findViewById(R.id.l88);
        levelsView[89]=findViewById(R.id.l89);
        levelsView[90]=findViewById(R.id.l90);
        levelsView[91]=findViewById(R.id.l91);
        levelsView[92]=findViewById(R.id.l92);
        levelsView[93]=findViewById(R.id.l93);
        levelsView[94]=findViewById(R.id.l94);
        levelsView[95]=findViewById(R.id.l95);
        levelsView[96]=findViewById(R.id.l96);
        levelsView[97]=findViewById(R.id.l97);
        levelsView[98]=findViewById(R.id.l98);
        levelsView[99]=findViewById(R.id.l99);
        levelsView[100]=findViewById(R.id.l100);
        btn_shop=findViewById(R.id.tv_shop);
        btn_custom=findViewById(R.id.tv_custom);

        levelsView[1].setOnClickListener(this);
        levelsView[2].setOnClickListener(this);
        levelsView[3].setOnClickListener(this);
        levelsView[4].setOnClickListener(this);
        levelsView[5].setOnClickListener(this);
        levelsView[6].setOnClickListener(this);
        levelsView[7].setOnClickListener(this);
        levelsView[8].setOnClickListener(this);
        levelsView[9].setOnClickListener(this);
        levelsView[10].setOnClickListener(this);
        levelsView[11].setOnClickListener(this);
        levelsView[12].setOnClickListener(this);
        levelsView[13].setOnClickListener(this);
        levelsView[14].setOnClickListener(this);
        levelsView[15].setOnClickListener(this);
        levelsView[16].setOnClickListener(this);
        levelsView[17].setOnClickListener(this);
        levelsView[18].setOnClickListener(this);
        levelsView[19].setOnClickListener(this);
        levelsView[20].setOnClickListener(this);
        levelsView[21].setOnClickListener(this);
        levelsView[22].setOnClickListener(this);
        levelsView[23].setOnClickListener(this);
        levelsView[24].setOnClickListener(this);
        levelsView[25].setOnClickListener(this);
        levelsView[26].setOnClickListener(this);
        levelsView[27].setOnClickListener(this);
        levelsView[28].setOnClickListener(this);
        levelsView[29].setOnClickListener(this);
        levelsView[30].setOnClickListener(this);
        levelsView[31].setOnClickListener(this);
        levelsView[32].setOnClickListener(this);
        levelsView[33].setOnClickListener(this);
        levelsView[34].setOnClickListener(this);
        levelsView[35].setOnClickListener(this);
        levelsView[36].setOnClickListener(this);
        levelsView[37].setOnClickListener(this);
        levelsView[38].setOnClickListener(this);
        levelsView[39].setOnClickListener(this);
        levelsView[40].setOnClickListener(this);
        levelsView[41].setOnClickListener(this);
        levelsView[42].setOnClickListener(this);
        levelsView[43].setOnClickListener(this);
        levelsView[44].setOnClickListener(this);
        levelsView[45].setOnClickListener(this);
        levelsView[46].setOnClickListener(this);
        levelsView[47].setOnClickListener(this);
        levelsView[48].setOnClickListener(this);
        levelsView[49].setOnClickListener(this);
        levelsView[50].setOnClickListener(this);
        levelsView[51].setOnClickListener(this);
        levelsView[52].setOnClickListener(this);
        levelsView[53].setOnClickListener(this);
        levelsView[54].setOnClickListener(this);
        levelsView[55].setOnClickListener(this);
        levelsView[56].setOnClickListener(this);
        levelsView[57].setOnClickListener(this);
        levelsView[58].setOnClickListener(this);
        levelsView[59].setOnClickListener(this);
        levelsView[60].setOnClickListener(this);
        levelsView[61].setOnClickListener(this);
        levelsView[62].setOnClickListener(this);
        levelsView[63].setOnClickListener(this);
        levelsView[64].setOnClickListener(this);
        levelsView[65].setOnClickListener(this);
        levelsView[66].setOnClickListener(this);
        levelsView[67].setOnClickListener(this);
        levelsView[68].setOnClickListener(this);
        levelsView[69].setOnClickListener(this);
        levelsView[70].setOnClickListener(this);
        levelsView[71].setOnClickListener(this);
        levelsView[72].setOnClickListener(this);
        levelsView[73].setOnClickListener(this);
        levelsView[74].setOnClickListener(this);
        levelsView[75].setOnClickListener(this);
        levelsView[76].setOnClickListener(this);
        levelsView[77].setOnClickListener(this);
        levelsView[78].setOnClickListener(this);
        levelsView[79].setOnClickListener(this);
        levelsView[80].setOnClickListener(this);
        levelsView[81].setOnClickListener(this);
        levelsView[82].setOnClickListener(this);
        levelsView[83].setOnClickListener(this);
        levelsView[84].setOnClickListener(this);
        levelsView[85].setOnClickListener(this);
        levelsView[86].setOnClickListener(this);
        levelsView[87].setOnClickListener(this);
        levelsView[88].setOnClickListener(this);
        levelsView[89].setOnClickListener(this);
        levelsView[90].setOnClickListener(this);
        levelsView[91].setOnClickListener(this);
        levelsView[92].setOnClickListener(this);
        levelsView[93].setOnClickListener(this);
        levelsView[94].setOnClickListener(this);
        levelsView[95].setOnClickListener(this);
        levelsView[96].setOnClickListener(this);
        levelsView[97].setOnClickListener(this);
        levelsView[98].setOnClickListener(this);
        levelsView[99].setOnClickListener(this);
        levelsView[100].setOnClickListener(this);
        btn_shop.setOnClickListener(this);
        btn_custom.setOnClickListener(this);

        //sp
        sp = getSharedPreferences("ShopSp", Context.MODE_PRIVATE);
        Integer unlocklevels=sp.getInt("levelNum",1);
        for(int i=100;i>unlocklevels;i--){
            levelsView[i].setBackgroundResource(R.drawable.level_locked_ic);
        }

        changeLang();
    }

    public void changeLang(){
        lang_num = sp.getInt("lang_num",0);
        if(lang_num == 0){
            btn_custom.setText("Draw");
            btn_shop.setText("Shop");
        }else{
            btn_custom.setText("繪畫");
            btn_shop.setText("商店");
        }
    }
}
