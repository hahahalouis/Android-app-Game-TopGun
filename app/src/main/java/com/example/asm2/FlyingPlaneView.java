package com.example.asm2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class FlyingPlaneView extends View {

    private Bitmap plane, bg,resize_bg,resize_plane,rocket;

    private Paint scorePaint = new Paint();

    private int planeX = 50, planeY,planeSpeed;
    private int rocketX,rocketY,rocket2X,rocket2Y,rocket2Speed = 30,rocketSpeed =25;
    private int canvasWidth, canvasHeight;

    private boolean touchStatus = false;



    public FlyingPlaneView(Context context) {
        super(context);
        plane = BitmapFactory.decodeResource(getResources(), R.drawable.plane);
        resize_plane = Bitmap.createScaledBitmap(plane, 200, 200 ,false);


        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        planeY = 550;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingame_bg);
        resize_bg = Bitmap.createScaledBitmap(bg, canvas.getWidth(),canvas.getHeight(),false);

        canvas.drawBitmap(resize_bg,0,0,null);
        canvas.drawText("Score : ",30,70,scorePaint);

        int minPlaneY = resize_plane.getHeight();
        int maxPlaneY = canvasHeight - resize_plane.getHeight() * 2;
        planeY = planeY + planeSpeed;

        if(planeY < minPlaneY)
        {
            planeY = minPlaneY;
        }
        if(planeY > maxPlaneY)
        {
            planeY = maxPlaneY;
        }
        planeSpeed = planeSpeed + 2;
        if(touchStatus)
        {
            canvas.drawBitmap(resize_plane,planeX,planeY,null);
            touchStatus = false;
        }else
        {
            canvas.drawBitmap(resize_plane,planeX,planeY,null);
        }

        //Rocket
        rocketX = rocketX - rocketSpeed;
        rocket2X = rocket2X - rocket2Speed;
        if(rocketX < 0)
        {
            rocketX = canvasWidth + 21;
            rocket2X = canvasWidth +21;
            rocketY = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
            rocket2Y = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
            Double randomTest = Math.floor(Math.random());
            Log.d("rocket-Y",""+rocketY);
            Log.d("randomTest",""+randomTest);
        }
        canvas.drawBitmap(resize_plane,rocketX,rocketY,null);
        canvas.drawBitmap(resize_plane,rocket2X,rocket2Y,null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touchStatus = true;

            planeSpeed = -22;
        }

        return super.onTouchEvent(event);
    }
}
